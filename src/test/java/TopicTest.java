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
        User u2 = new User((long) 4, "koc");
        Answer a1 = new Answer((long) 4);
        Answer a2 = new Answer((long) 5);
        Answer a3 = new Answer((long) 6);

        answerService.createAnswer(a1);
        answerService.createAnswer(a2);
        answerService.createAnswer(a3);
        userService.createUser(u);
        userService.createUser(u2);
        topicService.createTopic(topic);
        sectionService.createSection(sec);
        topicService.setSection(1,2);
        topicService.setCreator(1, 3);
        answerService.setTopic(1,4);
        answerService.setTopic(1,5);
        answerService.setTopic(1,6);
        topicService.addTopicPlus(3, 1);
        topicService.addTopicMinus(4,1);
    }

    @Test
    public void addMinusTest(){
        Optional<User> optUser = userRepository.findById((long) 4);
        Optional<Topic> optTopic = topicRepository.findById((long) 1);

        Assert.assertTrue(optUser.get().getMinusedTopics().contains(optTopic.get()));
        Assert.assertTrue(optTopic.get().getUsersMinus().contains(optUser.get()));
    }

    @Test
    public void removeTopicMinusTest(){
        Optional<User> optUser = userRepository.findById((long) 4);
        Optional<Topic> optTopic = topicRepository.findById((long) 1);

        boolean result = topicService.removeTopicMinus(4, 1);

        Assert.assertTrue(result);
        Assert.assertFalse(optUser.get().getMinusedTopics().contains(optTopic.get()));
        Assert.assertFalse(optTopic.get().getUsersMinus().contains(optUser.get()));
    }

    @Test
    public void getMinusesTopicTest(){
        Optional<User> optUser = userRepository.findById((long) 4);
        Optional<Topic> optTopic = topicRepository.findById((long) 1);

        List<User> usersPlus = topicService.getMinusesTopic(1);

        Assert.assertTrue(optTopic.get().getUsersMinus().containsAll(usersPlus));
    }

    @Test
    public void getMinusesCountTopicTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 1);
        int count = topicService.getMinusesCountTopic(1);

        Assert.assertEquals(optTopic.get().getUsersMinus().size(), count);
        Assert.assertEquals(1, count);
    }

    @Test
    public void addPlus(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Topic> optTopic = topicRepository.findById((long) 1);

        Assert.assertTrue(optUser.get().getPlusedTopics().contains(optTopic.get()));
        Assert.assertTrue(optTopic.get().getUsersPlus().contains(optUser.get()));
    }

    @Test
    public void removeTopicPlusTest(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Topic> optTopic = topicRepository.findById((long) 1);

        boolean result = topicService.removeTopicPlus(3, 1);

        Assert.assertTrue(result);
        Assert.assertFalse(optUser.get().getPlusedTopics().contains(optTopic.get()));
        Assert.assertFalse(optTopic.get().getUsersPlus().contains(optUser.get()));
    }

    @Test
    public void getPlusesTopicTest(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Topic> optTopic = topicRepository.findById((long) 1);

        List<User> usersPlus = topicService.getPlusesTopic(1);

        Assert.assertTrue(optTopic.get().getUsersPlus().containsAll(usersPlus));
    }

    @Test
    public void getPlusesCountTopicTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 1);
        int count = topicService.getPlusesCountTopic(1);

        Assert.assertEquals(optTopic.get().getUsersPlus().size(), count);
        Assert.assertEquals(1, count);
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
