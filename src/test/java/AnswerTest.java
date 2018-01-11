import neo4j.to2.domain.Answer;
import neo4j.to2.domain.ForumThread;
import neo4j.to2.domain.User;
import neo4j.to2.repository.AnswerRepository;
import neo4j.to2.repository.ForumThreadRepository;
import neo4j.to2.repository.UserRepository;
import neo4j.to2.service.AnswerService;
import neo4j.to2.service.ForumThreadService;
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
    private ForumThreadService forumThreadService;

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void prepareData(){
        Answer answer = new Answer((long) 1);
        ForumThread forumThread = new ForumThread((long) 2);
        User u = new User((long) 3, "koc");
        userService.create(u);
        forumThreadService.create(forumThread);
        answerService.create(answer);
        answerService.setForumThread(2, 1);
        answerService.addPlus(1,3);
        answerService.setCreator(1,3);
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

        Assert.assertTrue(optUser.get().getPluses().contains(optAnswer.get()));
        Assert.assertTrue(optAnswer.get().getUsersPlus().contains(optUser.get()));
    }

    @Test
    public void setForumThreadTest(){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById((long) 2);
        Optional<Answer> optAnswer = answerRepository.findById((long) 1);

        Assert.assertTrue(optForumThread.get().getAnswers().contains(optAnswer.get()));
        Assert.assertEquals(optAnswer.get().getForumThread(), optForumThread.get());
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
        Assert.assertFalse(optUser.get().getPluses().contains(optAnswer.get()));
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
}
