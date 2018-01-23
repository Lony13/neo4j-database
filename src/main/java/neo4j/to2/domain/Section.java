package neo4j.to2.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Section {
    @Id
    @GeneratedValue
    private Long sectionID;

    @Property
    private String sectionName;

    @Relationship(type = "HAVE", direction = Relationship.UNDIRECTED)
    private List<Topic> topics;
    private Integer id;

    public Section() {}

    public Section(String sectionName) {
        this.sectionName = sectionName;
    }

    public Section(Long sectionID) {
        this.sectionID = sectionID;
    }

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long sectionID) {
        this.sectionID = sectionID;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void addTopic(Topic topic){
        if(topics == null){
            topics = new ArrayList<>();
        }
        topics.add(topic);
    }

    public Integer getId() {
        return id;
    }
}
