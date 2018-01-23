package server.Models;

import java.util.List;
import java.util.Set;

@Deprecated
public class Topic {
    private Integer id;
    private Integer categoryId;
    private Integer postCount;
    private Integer numberInCategory;
    private String name;
    private String desc;
    private List<Post> posts;

    public Topic(Integer id, Integer categoryId, Integer postCount, Integer numberInCategory, String name, String desc, List<Post> posts, Set<Integer> usersWhoGavePlusIds, int plusCount) {
        this.id = id;
        this.categoryId = categoryId;
        this.postCount = postCount;
        this.numberInCategory = numberInCategory;
        this.name = name;
        this.desc = desc;
        this.posts = posts;
        this.usersWhoGavePlusIds = usersWhoGavePlusIds;
        this.plusCount = plusCount;
    }

    private Set<Integer> usersWhoGavePlusIds;
    private int plusCount;




    public Integer getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNumberInCategory(Integer numberInCategory) {
        this.numberInCategory = numberInCategory;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void incresePostCount() {
        postCount++;
    }

    public void decreasePostCount() {
        postCount--;
    }

    public void increasePlusCount() {
        plusCount ++;
    }

    public void decreasePlusCount() {
        plusCount--;
    }
}
