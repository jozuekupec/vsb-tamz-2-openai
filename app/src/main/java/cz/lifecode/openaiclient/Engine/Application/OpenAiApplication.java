package cz.lifecode.openaiclient.Engine.Application;

import android.app.Application;
import android.content.Intent;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.Engine.Chat.ChatManager;
import cz.lifecode.openaiclient.MainActivity;
import cz.lifecode.openaiclient.SplashActivity;

public class OpenAiApplication extends Application {
    private static OpenAiApplication instance = null;
    private final ChatManager chatManager = new ChatManager();
    private Authorization openAiAuthorization;


    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        instance = this;

        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public static OpenAiApplication getInstance() {
        return instance;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }

    public Authorization getOpenAiAuthorization() {
        return openAiAuthorization;
    }

    public void setOpenAiAuthorization(Authorization openAiAuthorization) {
        this.openAiAuthorization = openAiAuthorization;
    }
}
