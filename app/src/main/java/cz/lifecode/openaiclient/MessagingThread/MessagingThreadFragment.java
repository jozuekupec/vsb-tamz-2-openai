package cz.lifecode.openaiclient.MessagingThread;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.jar.Attributes;

import cz.lifecode.openaiclient.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessagingThreadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagingThreadFragment extends Fragment {
    public MessagingThreadFragment() {
        // Required empty public constructor
    }

    public static MessagingThreadFragment newInstance() {
        return new MessagingThreadFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messaging_thread, container, false);
    }
}