package cz.lifecode.openaiclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;
import cz.lifecode.openaiclient.Engine.Chat.Message;
import cz.lifecode.openaiclient.Engine.Chat.Role;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OpenAiApplication.getInstance().getChatManager().createNewThread();
        OpenAiApplication.getInstance().getChatManager().getCurrentThread().addMessage(new Message(Role.USER, "TEST"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}