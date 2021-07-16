package model;

public class Message {
    public String text;
    public User sender;

    public Message(String text, User sender) {
        this.text = text;
        this.sender = sender;
    }
}
