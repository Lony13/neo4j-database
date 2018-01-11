import neo4j.to2.domain.Section;
import neo4j.to2.domain.Topic;
import neo4j.to2.repository.SectionRepository;
import neo4j.to2.repository.TopicRepository;
import neo4j.to2.service.SectionService;
import neo4j.to2.service.TopicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class SectionTest {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @Before
    public void prepareData(){
        Section section = new Section((long) 1);
        sectionService.createSection(section);
        Topic topic = new Topic((long) 2);
        topicService.createTopic(topic);
        topicService.setSection(2, 1);
    }

    @Test
    public void findByIdSectionTest(){
        Optional<Section> optSection = sectionRepository.findById((long) 1);
        long id = optSection.get().getSectionID();
        Assert.assertEquals(1, id);
    }

    @Test
    public void getTopicsForSectionTest(){
        Optional<Section> optSection = sectionRepository.findById((long) 1);

        List<Topic> topicList = sectionService.getTopicsForSection(1, 0, 1);
        Assert.assertTrue(optSection.get().getTopics().containsAll(topicList));
        Assert.assertNull(sectionService.getTopicsForSection(1, 2, 5));
    }

    @Test
    public void getSectionsTest(){
        Section section1 = new Section("Java");
        Section section2 = new Section("Scala");
        Section section3 = new Section("Kotlin");

        sectionService.createSection(section1);
        sectionService.createSection(section2);
        sectionService.createSection(section3);

        List<Section> sections = sectionService.getSections(3);

        Assert.assertTrue(sections.size() >= 3);
    }
}
