import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Category;
import neo4j.to2.domain.ForumThread;
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
public class ForumThreadTest {
    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

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
        ForumThread forumThread = new ForumThread((long) 1);
        Category cat = new Category((long) 2);
        User u = new User((long) 3, "loy");
        Answer a1 = new Answer((long) 4);
        Answer a2 = new Answer((long) 5);
        Answer a3 = new Answer((long) 6);

        answerService.create(a1);
        answerService.create(a2);
        answerService.create(a3);
        userService.create(u);
        forumThreadService.create(forumThread);
        categoryService.create(cat);
        forumThreadService.setCategory(1,2);
        forumThreadService.setCreator(1, 3);
        answerService.setForumThread(1,4);
        answerService.setForumThread(1,5);
        answerService.setForumThread(1,6);
    }

    @Test
    public void getAnswersFromForumThreadTest(){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById((long) 1);
        List<Answer> answers = forumThreadService.getAnswersFromForumThread(1, 2);

        Assert.assertTrue(optForumThread.get().getAnswers().containsAll(answers));
    }

    @Test
    public void setCategoryTest(){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById((long) 1);
        Optional<Category> optCategory = categoryRepository.findById((long) 2);

        Assert.assertEquals(optForumThread.get().getCategory(), optCategory.get());
        Assert.assertTrue(optCategory.get().getForumThreads().contains(optForumThread.get()));
    }

    @Test
    public void setCreatorTest(){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById((long) 1);
        Optional<User> optCreator = userRepository.findById((long) 3);

        Assert.assertEquals(optForumThread.get().getCreator(), optCreator.get());
        Assert.assertTrue(optCreator.get().getForumThreads().contains(optForumThread.get()));
    }

    @Test
    public void findByIdForumThreadTest(){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById((long) 1);
        long id = optForumThread.get().getForumThreadID();
        Assert.assertEquals(1, id);
    }
}
