package neo4j.to2.domain;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class Message {
    @Id
    @GeneratedValue
    private Long messageID;

    @Property
    private String text;

    @Property
    private long timestamp;

    @Relationship(type = "BELONGS_TO", direction = Relationship.UNDIRECTED)
    private Chat chat;

    @Relationship(type = "WRITTEN_BY", direction = Relationship.UNDIRECTED)
    private User creator;

    public Message() {
    }

    public Message(Long messageID) {
        this.messageID = messageID;
    }

    public Message(Long messageID, String text) {
        this.messageID = messageID;
        this.text = text;
    }

    public Message(String text, long timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(String text) {
        this.text = text;
    }

    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
