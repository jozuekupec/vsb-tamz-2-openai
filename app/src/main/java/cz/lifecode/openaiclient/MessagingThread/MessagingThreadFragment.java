package cz.lifecode.openaiclient.MessagingThread;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Objects;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.Chat.ChatCompletion;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatRequestDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatResponseDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.MessageDTO;
import cz.lifecode.openaiclient.API.Exceptions.InvalidAccessTokenOpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.Engine.Adapter.ChatRecyclerAdapter;
import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;
import cz.lifecode.openaiclient.Engine.Chat.ChatManager;
import cz.lifecode.openaiclient.Engine.Chat.Message;
import cz.lifecode.openaiclient.Engine.Chat.Role;
import cz.lifecode.openaiclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagingThreadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagingThreadFragment extends Fragment {
    public ChatRecyclerAdapter chatRecyclerAdapter;
    public RecyclerView recyclerView;
    protected ChatManager chatManager;
    protected Authorization openAiAuthorization;

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
        EditText input = view.findViewById(R.id.chatSendMessageInput);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message newMessage = new Message(Role.USER, input.getText().toString(), Calendar.getInstance().getTime());
                input.setText("");
                addMessageToChatAndNotify(newMessage);

                loadOpenAiToken();
                SendMessageToOpenAiThread sendMessageThread = new SendMessageToOpenAiThread(chatManager.getCurrentThread().getMessages());
                sendMessageThread.start();
            }
        });

        setupChatRecyclerView();
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
            chatCompletion = new ChatCompletion(openAiAuthorization);
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
            chatRequest.setModel("gpt-4");
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

    private void loadOpenAiToken() {
        try {
            this.openAiAuthorization = new Authorization(requireContext());
        } catch (InvalidAccessTokenOpenAiException e) {
            this.openAiAuthorization = new Authorization("");
            Toast.makeText(requireContext(), R.string.invalid_access_token, Toast.LENGTH_LONG).show();
        }
    }
}