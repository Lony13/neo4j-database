import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Topic;
import neo4j.to2.domain.User;
import neo4j.to2.repository.AnswerRepository;
import neo4j.to2.repository.TopicRepository;
import neo4j.to2.repository.UserRepository;
import neo4j.to2.service.AnswerService;
import neo4j.to2.service.TopicService;
import neo4j.to2.service.UserService;
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
public class AnswerTest {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void prepareData(){
        Answer answer = new Answer((long) 1);
        Topic topic = new Topic((long) 2);
        User u = new User((long) 3, "koc");
        User u2 = new User((long) 4, "loy");
        userService.createUser(u);
        userService.createUser(u2);
        topicService.createTopic(topic);
        answerService.createAnswer(answer);
        answerService.setTopic(2, 1);
        answerService.addPlus(1,3);
        answerService.setCreator(1,3);
        answerService.addMinus(1,4);
    }

    @Test
    public void setCreator(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        Assert.assertTrue(optUser.get().getAnswers().contains(optAnswer.get()));
        Assert.assertEquals(optAnswer.get().getCreator(), optUser.get());
    }

    @Test
    public void addPlus(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        Assert.assertTrue(optUser.get().getPlusedAnswers().contains(optAnswer.get()));
        Assert.assertTrue(optAnswer.get().getUsersPlus().contains(optUser.get()));
    }

    @Test
    public void addMinusTest(){
        Optional<User> optUser = userRepository.findById((long) 4);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        Assert.assertTrue(optUser.get().getMinusedAnswers().contains(optAnswer.get()));
        Assert.assertTrue(optAnswer.get().getUsersMinus().contains(optUser.get()));
    }

    @Test
    public void setTopicTest(){
        Optional<Topic> optTopic = topicRepository.findById((long) 2);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        Assert.assertTrue(optTopic.get().getAnswers().contains(optAnswer.get()));
        Assert.assertEquals(optAnswer.get().getTopic(), optTopic.get());
    }

    @Test
    public void findByIdAnswerTest(){
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);
        long id = optAnswer.get().getAnswerID();
        Assert.assertEquals(1, id);
    }

    @Test
    public void removeAnswerPlusTest(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        boolean result = answerService.removeAnswerPlus(3, 1);

        Assert.assertTrue(result);
        Assert.assertFalse(optUser.get().getPlusedAnswers().contains(optAnswer.get()));
        Assert.assertFalse(optAnswer.get().getUsersPlus().contains(optUser.get()));
    }

    @Test
    public void getPlusesAnswerTest(){
        Optional<User> optUser = userRepository.findById((long) 3);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        List<User> usersPlus = answerService.getPlusesAnswer(1);

        Assert.assertTrue(optAnswer.get().getUsersPlus().containsAll(usersPlus));
    }

    @Test
    public void getPlusesCountAnswer(){
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);
        int count = answerService.getPlusesCountAnswer(1);

        Assert.assertEquals(optAnswer.get().getUsersPlus().size(), count);
        Assert.assertEquals(1, count);
    }

    @Test
    public void removeAnswerMinusTest(){
        Optional<User> optUser = userRepository.findById((long) 4);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        boolean result = answerService.removeAnswerMinus(4, 1);

        Assert.assertTrue(result);
        Assert.assertFalse(optUser.get().getMinusedAnswers().contains(optAnswer.get()));
        Assert.assertFalse(optAnswer.get().getUsersMinus().contains(optUser.get()));
    }

    @Test
    public void getMinusesAnswerTest(){
        Optional<User> optUser = userRepository.findById((long) 4);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        List<User> usersPlus = answerService.getMinusesAnswer(1);

        Assert.assertTrue(optAnswer.get().getUsersMinus().containsAll(usersPlus));
    }

    @Test
    public void getMinusesCountAnswer(){
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);
        int count = answerService.getMinusesCountAnswer(1);

        Assert.assertEquals(optAnswer.get().getUsersMinus().size(), count);
        Assert.assertEquals(1, count);
    }
}
