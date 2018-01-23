package server.Models;

import java.util.Set;

@Deprecated
public class Post {
    Integer id;    Integer topicId;
    Integer numberInTopic;
    String content;
    Integer authorId;
    Set<Integer> pluses;
    Integer plusCount;

    public Post(Integer id, Integer topicId, Integer numberInTopic, String content, Integer authorId, Set<Integer> pluses, Integer plusCount) {
        this.id = id;
        this.topicId = topicId;
        this.numberInTopic = numberInTopic;
        this.content = content;
        this.authorId = authorId;
        this.pluses = pluses;
        this.plusCount = plusCount;
    }


    public Integer getTopicId() {
        return topicId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberInTopic(Integer integer) {
        numberInTopic = integer;
    }

    public Integer getId() {
        return id;
    }

    public void increasePlusesCount() {
        plusCount++;
    }
}
