package cz.lifecode.openaiclient.API.DTO.Chat;

public class ChatRequestDTO {
    private String model;
    private MessageDTO[] messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public MessageDTO[] getMessages() {
        return messages;
    }

    public void setMessages(MessageDTO[] messages) {
        this.messages = messages;
    }
}
