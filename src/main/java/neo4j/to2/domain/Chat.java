package neo4j.to2.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Chat {
    @Id
    @GeneratedValue
    private Long chatID;

    @Property
    private String chatName;

    @Relationship(type = "HAVE", direction = Relationship.UNDIRECTED)
    private List<Message> messages;

    @Relationship(type = "PARTICIPANTS", direction = Relationship.UNDIRECTED)
    private List<User> participants;

    public Chat() {}

    public Chat(String chatName) {
        this.chatName = chatName;
    }

    public Chat(long chatID){
        this.chatID = chatID;
    }

    public Long getChatID() {
        return chatID;
    }

    public void setChatID(Long chatID) {
        this.chatID = chatID;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void addMessage(Message message){
        if(messages == null){
            messages = new ArrayList<>();
        }

        messages.add(message);
    }

    public void addParticipant(User participant){
        if(participants == null){
            participants = new ArrayList<>();
        }

        participants.add(participant);
    }
}
