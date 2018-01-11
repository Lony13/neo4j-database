package neo4j.to2.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Answer {
    @Id
    @GeneratedValue
    private Long answerID;

    @Property
    private String text;

    @Relationship(type = "BELONGS_TO", direction = Relationship.UNDIRECTED)
    private Topic topic;

    @Relationship(type = "WRITTEN_BY", direction = Relationship.UNDIRECTED)
    private User creator;

    @Relationship(type = "PLUSED_BY", direction = Relationship.UNDIRECTED)
    private List<User> usersPlus;

    public Answer(){}

    public Answer(Long answerID) {
        this.answerID = answerID;
    }

    public Answer(String text) {
        this.text = text;
    }

    public Long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Long answerID) {
        this.answerID = answerID;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getUsersPlus() {
        return usersPlus;
    }

    public void setUsersPlus(List<User> usersPlus) {
        this.usersPlus = usersPlus;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addUserPlus(User userPlus){
        if(usersPlus == null){
            usersPlus = new ArrayList<>();
        }
        usersPlus.add(userPlus);
    }

    public void removeUserPlus(User userPlus){
        if(usersPlus != null){
            usersPlus.remove(userPlus);
        }
    }
}
