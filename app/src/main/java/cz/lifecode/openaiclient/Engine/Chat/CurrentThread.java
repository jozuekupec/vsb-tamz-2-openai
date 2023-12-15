package cz.lifecode.openaiclient.Engine.Chat;

import java.util.List;

public class CurrentThread {
    private final int threadId;

    private final List<Message> messages;

    public CurrentThread(int threadId, List<Message> messages) {
        this.threadId = threadId;
        this.messages = messages;
    }

    public int getThreadId() {
        return threadId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
