package cz.lifecode.openaiclient.Engine.Chat;

import java.util.ArrayList;

public class ChatManager {
    private CurrentThread currentThread = null;

    public ChatManager() {
    }

    public CurrentThread getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(CurrentThread currentThread) {
        this.currentThread = currentThread;
    }

    public void createNewThread() {
        setCurrentThread(new CurrentThread(
                (int) System.currentTimeMillis(),
                new ArrayList<>()
        ));
    }
}
