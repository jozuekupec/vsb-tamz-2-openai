package cz.lifecode.openaiclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.Engine.Application.OpenAiApplication;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String openAiToken = loadOpenAiToken();
                if (openAiToken == null) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    OpenAiApplication.getInstance().setOpenAiAuthorization(new Authorization(openAiToken));
                }
                finish();
            }
        }, 1500);
    }


    private String loadOpenAiToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(Authorization.SHARED_PREFERENCES_REPOSITORY, MODE_PRIVATE);
        return sharedPreferences.getString(Authorization.API_TOKEN_SHARED_PREFERENCES_KEY, null);
    }

}