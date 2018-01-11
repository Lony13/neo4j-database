import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Section;
import neo4j.to2.domain.Topic;
import neo4j.to2.domain.User;
import neo4j.to2.repository.*;
import neo4j.to2.service.*;
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
public class TopicTest {
    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @Before
    public void prepareData(){
        Topic topic = new Topic((long) 1);
        Section sec = new Section((long) 2);
        User u = new User((long) 3, "loy");
        Answer a1 = new Answer((long) 4);
        Answer a2 = new Answer((long) 5);
        Answer a3 = new Answer((long) 6);

        answerService.createAnswer(a1);
        answerService.createAnswer(a2);
        answerService.createAnswer(a3);
        userService.createUser(u);
        topicService.createTopic(topic);
        sectionService.createSection(sec);
        topicService.setSection(1,2);
        topicService.setCreator(1, 3);
        answerService.setTopic(1,4);
        answerService.setTopic(1,5);
        answerService.setTopic(1,6);
    }

    @Test
    public void getAnswersFromTopicTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 1);
        List<Answer> answers = topicService.getAnswersFromTopic(1, 2);

        Assert.assertTrue(optTopic.get().getAnswers().containsAll(answers));
    }

    @Test
    public void setCategoryTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 1);
        Optional<Section> optCategory = sectionRepository.findById((long) 2);

        Assert.assertEquals(optTopic.get().getSection(), optCategory.get());
        Assert.assertTrue(optCategory.get().getTopics().contains(optTopic.get()));
    }

    @Test
    public void setCreatorTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 1);
        Optional<User> optCreator = userRepository.findById((long) 3);

        Assert.assertEquals(optTopic.get().getCreator(), optCreator.get());
        Assert.assertTrue(optCreator.get().getTopics().contains(optTopic.get()));
    }

    @Test
    public void findByIdTopicTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 1);
        long id = optTopic.get().getTopicID();
        Assert.assertEquals(1, id);
    }
}
