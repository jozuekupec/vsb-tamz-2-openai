package cz.lifecode.openaiclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.orhanobut.logger.Logger;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        AppCompatButton saveButton = findViewById(R.id.loginSaveButton);
        AppCompatButton resetButton = findViewById(R.id.loginResetButton);
        EditText apiTokenEditText = findViewById(R.id.loginApiKeyEditText);

        SharedPreferences sharedPreferences = getSharedPreferences(Authorization.SHARED_PREFERENCES_REPOSITORY, MODE_PRIVATE);
        saveButton.setOnClickListener(view -> {
            login(sharedPreferences, apiTokenEditText.getText().toString());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });

        resetButton.setOnClickListener(view -> {
            apiTokenEditText.setText("");
        });
    }

    protected void login(SharedPreferences sharedPreferences, String apiToken) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Authorization.API_TOKEN_SHARED_PREFERENCES_KEY, apiToken);
        editor.apply();

        OpenAiApplication.getInstance().setOpenAiAuthorization(new Authorization(apiToken));
    }
}