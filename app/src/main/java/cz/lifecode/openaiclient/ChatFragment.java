package cz.lifecode.openaiclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}