package cz.lifecode.openaiclient.MessagingThread;

import static android.app.Activity.RESULT_OK;
import static android.media.MediaRecorder.AudioSource.VOICE_RECOGNITION;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cz.lifecode.openaiclient.API.Chat.ChatCompletion;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatRequestDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatResponseDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.MessageDTO;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.Engine.Adapter.ChatRecyclerAdapter;
import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;
import cz.lifecode.openaiclient.Engine.Chat.ChatManager;
import cz.lifecode.openaiclient.Engine.Chat.Message;
import cz.lifecode.openaiclient.Engine.Chat.Role;
import cz.lifecode.openaiclient.R;

public class MessagingThreadFragment extends Fragment {
    public ChatRecyclerAdapter chatRecyclerAdapter;
    public RecyclerView recyclerView;
    protected ChatManager chatManager;
    public static String gptModel = "gpt-4";

    public MessagingThreadFragment() {
        this.chatManager = OpenAiApplication.getInstance().getChatManager();
    }

    public static MessagingThreadFragment newInstance() {
        return new MessagingThreadFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messaging_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton sendMessageButton = view.findViewById(R.id.chatSendMessageButton);
        ImageButton sendMessageMicrophoneButton = view.findViewById(R.id.chatSendMessageMicrophoneButton);
        EditText input = view.findViewById(R.id.chatSendMessageInput);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message newMessage = new Message(Role.USER, input.getText().toString(), Calendar.getInstance().getTime());
                input.setText("");
                addMessageToChatAndNotify(newMessage);

                SendMessageToOpenAiThread sendMessageThread = new SendMessageToOpenAiThread(chatManager.getCurrentThread().getMessages());
                sendMessageThread.start();
            }
        });

        sendMessageMicrophoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.chat_speech_recognition_command));
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

                startActivityForResult(intent, VOICE_RECOGNITION);
            }
        });

        setupChatRecyclerView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != VOICE_RECOGNITION) {
            return;
        }

        if (resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String recognizedText = result.get(0);

                EditText input = requireView().findViewById(R.id.chatSendMessageInput);
                input.setText(recognizedText);
            }
        } else {
            Toast.makeText(getContext(), R.string.chat_speech_recognition_something_went_wrong, Toast.LENGTH_SHORT).show();
        }
    }

    public void addMessageToChatAndNotify(Message message) {
        chatManager.getCurrentThread().addMessage(message);
        chatRecyclerAdapter.notifyItemInserted(chatManager.getCurrentThread().getMessages().size() - 1);
    }

    public class SendMessageToOpenAiThread extends Thread {
        ChatCompletion chatCompletion;
        List<Message> messages;
        ArrayList<MessageDTO> messagesDTO;

        public SendMessageToOpenAiThread(List<Message> messages) {
            this.messages = messages;
        }

        public void run() {
            chatCompletion = new ChatCompletion(OpenAiApplication.getInstance().getOpenAiAuthorization());
            messagesDTO = new ArrayList<>();

            for (Message message : messages) {
                MessageDTO messageDTO = new MessageDTO();
                if (message.getRole() == Role.USER) {
                    messageDTO.setRoleUser();
                } else {
                    messageDTO.setRoleOpenAi();
                }
                messageDTO.setContent(message.getContent());
                messagesDTO.add(messageDTO);
            }

            ChatRequestDTO chatRequest = new ChatRequestDTO();
            chatRequest.setModel(gptModel);
            chatRequest.setMessages(messagesDTO.toArray(new MessageDTO[0]));

            try {
                ChatResponseDTO response = chatCompletion.sendMessage(chatRequest);
                addMessageToChat(response);
            } catch (OpenAiException exception) {
                Logger.w(Objects.requireNonNull(exception.getMessage()));
            }
        }

        private void addMessageToChat(ChatResponseDTO chatResponseDTO) {
            Message newMessage = new Message(Role.OPENAI, chatResponseDTO.getChoices()[0].getMessage().getContent());
            addMessageToChatAndNotify(newMessage);
        }
    }

    protected void setupChatRecyclerView() {
        chatRecyclerAdapter = new ChatRecyclerAdapter(requireContext(), OpenAiApplication.getInstance().getChatManager());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());

        recyclerView = requireView().findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatRecyclerAdapter);
        recyclerView.post(() -> {
            recyclerView.scrollToPosition(chatRecyclerAdapter.getItemCount() - 1);
        });
    }
}