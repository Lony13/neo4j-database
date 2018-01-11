package neo4j.to2.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class ProfilePost {
    @Id
    @GeneratedValue
    private Long profilePostID;

    @Relationship(type = "WRITTEN_BY", direction = Relationship.UNDIRECTED)
    private User creator;

    @Relationship(type = "IS_ABOUT", direction = Relationship.UNDIRECTED)
    private User owner;

    public ProfilePost() {
    }

    public ProfilePost(Long profilePostID) {
        this.profilePostID = profilePostID;
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
}
