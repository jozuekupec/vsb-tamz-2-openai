package cz.lifecode.openaiclient.Engine.Chat;

import java.util.Date;

public class Message {
    protected Role role;
    protected String content;
    protected Date date;

    public Message(Role role, String content) {
        this.role = role;
        this.content = content;
        this.date = new Date();
    }

    public Message(Role role, String content, Date date) {
        this.role = role;
        this.content = content;
        this.date = date;
    }

    public Role getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
