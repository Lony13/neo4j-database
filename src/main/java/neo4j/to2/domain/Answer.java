package neo4j.to2.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Answer {
    @Id
    @GeneratedValue
    private Long answerID;

    @Relationship(type = "BELONGS_TO", direction = Relationship.UNDIRECTED)
    private ForumThread forumThread;

    @Relationship(type = "WRITTEN_BY", direction = Relationship.UNDIRECTED)
    private User creator;

    @Relationship(type = "PLUSED_BY", direction = Relationship.UNDIRECTED)
    private List<User> usersPlus;

    public Answer(){}

    public Answer(Long answerID) {
        this.answerID = answerID;
    }

    public Long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Long answerID) {
        this.answerID = answerID;
    }

    public ForumThread getForumThread() {
        return forumThread;
    }

    public void setForumThread(ForumThread forumThread) {
        this.forumThread = forumThread;
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
