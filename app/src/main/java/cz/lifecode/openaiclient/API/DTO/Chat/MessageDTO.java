package cz.lifecode.openaiclient.API.DTO.Chat;

public class MessageDTO {
    private String role;
    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoleUser()
    {
        return "user";
    }

    public String getRoleOpenAi()
    {
        return "assistant";
    }

    public void setRoleUser() {
        this.role = getRoleUser();
    }

    public void setRoleOpenAi() {
        this.role = getRoleOpenAi();
    }
}
