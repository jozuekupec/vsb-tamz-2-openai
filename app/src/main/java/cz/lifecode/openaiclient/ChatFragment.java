package cz.lifecode.openaiclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.Calendar;
import java.util.Date;

import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;
import cz.lifecode.openaiclient.Engine.Chat.Message;
import cz.lifecode.openaiclient.Engine.Chat.Role;
import cz.lifecode.openaiclient.MessagingThread.MessagingThreadFragment;

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

        Calendar dayBefore = Calendar.getInstance();
        Calendar todayNight = Calendar.getInstance();
        dayBefore.set(Calendar.DAY_OF_MONTH, 27);
        todayNight.set(Calendar.HOUR_OF_DAY, 0);
        todayNight.set(Calendar.MINUTE, 0);
        todayNight.set(Calendar.SECOND, 0);

        OpenAiApplication.getInstance().getChatManager().createNewThread();
        OpenAiApplication.getInstance().getChatManager().getCurrentThread().addMessage(new Message(Role.USER, "Hii", dayBefore.getTime()));
        OpenAiApplication.getInstance().getChatManager().getCurrentThread().addMessage(new Message(Role.OPENAI, "Go sleep", todayNight.getTime()));
        OpenAiApplication.getInstance().getChatManager().getCurrentThread().addMessage(new Message(Role.USER, "Hello world"));
        OpenAiApplication.getInstance().getChatManager().getCurrentThread().addMessage(new Message(Role.OPENAI, "Hello there"));
        OpenAiApplication.getInstance().getChatManager().getCurrentThread().addMessage(new Message(Role.USER, "Nice to meet you"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}