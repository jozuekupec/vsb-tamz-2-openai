package cz.lifecode.openaiclient.MessagingThread;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import cz.lifecode.openaiclient.Engine.Adapter.ChatRecyclerAdapter;
import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;
import cz.lifecode.openaiclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagingThreadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagingThreadFragment extends Fragment {
    public ChatRecyclerAdapter chatRecyclerAdapter;
    public RecyclerView recyclerView;

    public MessagingThreadFragment() {
        // Required empty public constructor
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

        setupChatRecyclerView();
    }

    protected void setupChatRecyclerView() {
        chatRecyclerAdapter = new ChatRecyclerAdapter(requireContext(), OpenAiApplication.getInstance().getChatManager());
        LinearLayoutManager manager = new LinearLayoutManager(requireContext());

        recyclerView = requireView().findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatRecyclerAdapter);
    }
}