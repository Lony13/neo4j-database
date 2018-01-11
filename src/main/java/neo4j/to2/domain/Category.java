package neo4j.to2.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Category {
    @Id
    @GeneratedValue
    private Long categoryID;

    @Property
    private String categoryName;

    @Relationship(type = "HAVE", direction = Relationship.UNDIRECTED)
    private List<ForumThread> forumThreads;

    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public List<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public void setForumThreads(List<ForumThread> forumThreads) {
        this.forumThreads = forumThreads;
    }

    public void addForumThread(ForumThread forumThread){
        if(forumThreads == null){
            forumThreads = new ArrayList<>();
        }
        forumThreads.add(forumThread);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
