package model;

public class Message {
    private static int IdCounter = 0;
    public int Id;
    public String text;
    public User sender;

    public Message(String text, User sender) {
        this.text = text;
        this.sender = sender;
        this.Id = IdCounter;
        IdCounter++;
    }

    /*public static Message getMessageById(int id){

    }*/
}
