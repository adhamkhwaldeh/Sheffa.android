package com.joyBox.shefaa.entities;

public class MessageResult {
    public Boolean success;
    public MessageState messages;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public MessageState getMessages() {
        return messages;
    }

    public void setMessages(MessageState messages) {
        this.messages = messages;
    }
}