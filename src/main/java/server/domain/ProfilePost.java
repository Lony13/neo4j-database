package server.domain;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class ProfilePost implements Comparable<ProfilePost> {
    @Id
    @GeneratedValue
    private Long profilePostID;

    @Property
    private String text;

    @Property
    private long timestamp;

    @Relationship(type = "WRITTEN_BY", direction = Relationship.UNDIRECTED)
    private User creator;

    @Relationship(type = "IS_ABOUT", direction = Relationship.UNDIRECTED)
    private User owner;

    public ProfilePost() {
    }

    public ProfilePost(Long profilePostID) {
        this.profilePostID = profilePostID;
    }

    public ProfilePost(long timestamp, String text){
        this.timestamp = timestamp;
        this.text = text;
    }

    public Long getProfilePostID() {
        return profilePostID;
    }

    public void setProfilePostID(Long profilePostID) {
        this.profilePostID = profilePostID;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(ProfilePost o) {
        if(this.timestamp > o.timestamp)
            return 1;
        return -1;
    }
}
