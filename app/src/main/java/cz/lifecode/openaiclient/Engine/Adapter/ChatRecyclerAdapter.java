package cz.lifecode.openaiclient.Engine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cz.lifecode.openaiclient.Engine.Chat.ChatManager;
import cz.lifecode.openaiclient.Engine.Chat.DateTimeFormatter;
import cz.lifecode.openaiclient.Engine.Chat.Message;
import cz.lifecode.openaiclient.Engine.Chat.Role;
import cz.lifecode.openaiclient.R;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ChatModelViewHolder> {
    protected Context context;
    protected ChatManager chatManager;
    protected DateTimeFormatter dateTimeFormatter;

    public ChatRecyclerAdapter(Context context, ChatManager chatManager) {
        super();
        this.context = context;
        this.chatManager = chatManager;
        this.dateTimeFormatter = new DateTimeFormatter();
    }

    protected void onBindViewHolder(@NonNull ChatModelViewHolder chatModelViewHolder, @NonNull Message message) {
        if (message.getRole() == Role.USER) {
            chatModelViewHolder.openAiMessageLayout.setVisibility(View.GONE);
            chatModelViewHolder.userMessageLayout.setVisibility(View.VISIBLE);
            chatModelViewHolder.userMessageContentTextView.setText(message.getContent());
            chatModelViewHolder.userMessageTimeTextView.setText(dateTimeFormatter.formatForChatMessage(message.getDate()));
            return;
        }

        if (message.getRole() == Role.OPENAI) {
            chatModelViewHolder.userMessageLayout.setVisibility(View.GONE);
            chatModelViewHolder.openAiMessageLayout.setVisibility(View.VISIBLE);
            chatModelViewHolder.openAiMessageContentTextView.setText(message.getContent());
            chatModelViewHolder.openAiMessageTimeTextView.setText(dateTimeFormatter.formatForChatMessage(message.getDate()));
            return;
        }

        throw new RuntimeException("Invalid message Role!");
    }

    @NonNull
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row, parent, false);
        return new ChatModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatModelViewHolder chatModelViewHolder, int i) {
        onBindViewHolder(chatModelViewHolder, chatManager.getCurrentThread().getMessages().get(i));
    }

    @Override
    public int getItemCount() {
        return chatManager.getCurrentThread().getMessages().size();
    }

    public static class ChatModelViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout openAiMessageLayout, userMessageLayout, openAiMessageContentLayout, userMessageContentLayout;
        public TextView openAiMessageContentTextView, openAiMessageTimeTextView, userMessageContentTextView, userMessageTimeTextView;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);

            openAiMessageLayout = itemView.findViewById(R.id.chatMessageOpenAiLayout);
            openAiMessageContentLayout = itemView.findViewById(R.id.chatMessageOpenAiMessageLayout);
            userMessageLayout = itemView.findViewById(R.id.chatMessageUserLayout);
            userMessageContentLayout = itemView.findViewById(R.id.chatMessageUserMessageLayout);

            openAiMessageContentTextView = itemView.findViewById(R.id.chatMessageOpenAiContent);
            openAiMessageTimeTextView = itemView.findViewById(R.id.chatMessageOpenAiTime);
            userMessageContentTextView = itemView.findViewById(R.id.chatMessageUserContent);
            userMessageTimeTextView = itemView.findViewById(R.id.chatMessageUserTime);
        }
    }
}
