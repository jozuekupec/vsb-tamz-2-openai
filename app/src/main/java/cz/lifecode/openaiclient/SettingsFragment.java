package cz.lifecode.openaiclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Authorization.SHARED_PREFERENCES_REPOSITORY, Context.MODE_PRIVATE);

        EditText apiKeyEditText = view.findViewById(R.id.settingsApiKeyEditText);
        AppCompatButton resetButton = view.findViewById(R.id.settingsResetButton);

        apiKeyEditText.setText(sharedPreferences.getString(Authorization.API_TOKEN_SHARED_PREFERENCES_KEY, null));

        resetButton.setOnClickListener(view1 -> {
            apiKeyEditText.setText("");
            OpenAiApplication.getInstance().setOpenAiAuthorization(null);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(Authorization.API_TOKEN_SHARED_PREFERENCES_KEY);
            editor.apply();

            OpenAiApplication.getInstance().getChatManager().setCurrentThread(null);

            startActivity(new Intent(requireActivity(), LoginActivity.class));
            requireActivity().finish();
        });
    }
}