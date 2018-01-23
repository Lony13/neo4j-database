package server.Models;

import java.io.Serializable;
import java.util.List;

@Deprecated
public class Section implements Serializable {
    Integer id;
    Integer sectionId;
    Integer topicCount;
    String name;
    String decription;
    List<Integer> topics;

    public Section(){}

    public Section(int id, int topicCountt, String name, String decription, List<Integer> topics) {
        this.id=id;
        this.topicCount =topicCountt;
        this.name=name;
        this.decription=decription;
        this.topics=topics;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public void setTopics(List<Integer> topics) {
        this.topics = topics;
    }

    public List<Integer> getTopics() {
        return topics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void updateCategory(Section category){ }

    public void decreaseTopicCount() {
        topicCount--;
    }

    public void increaseTopicCount() {
        topicCount++;
    }

}
