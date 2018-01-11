package neo4j.to2.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class ForumThread {
    @Id
    @GeneratedValue
    private Long forumThreadID;

    @Relationship(type = "HAS_CATEGORY", direction = Relationship.UNDIRECTED)
    private Category category;

    @Relationship(type = "HAVE", direction = Relationship.UNDIRECTED)
    private List<Answer> answers;

    @Relationship(type = "CREATED_BY", direction = Relationship.UNDIRECTED)
    private User creator;

    public ForumThread() {
    }

    public ForumThread(Long forumThreadID) {
        this.forumThreadID = forumThreadID;
    }

    public Long getForumThreadID() {
        return forumThreadID;
    }

    public void setForumThreadID(Long forumThreadID) {
        this.forumThreadID = forumThreadID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer){
        if(answers == null){
            answers = new ArrayList<>();
        }
        answers.add(answer);
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

}
