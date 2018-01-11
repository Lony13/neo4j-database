import neo4j.to2.domain.*;
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
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfilePostRepository profilePostRepository;

    @Autowired
    private ProfilePostService profilePostService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @Before
    public void prepareData(){
        User user = new User((long)1, "TestUser");
        User friend = new User((long)2, "Friend");
        User friend2 = new User((long)4, "Friend2");
        User notFriend = new User((long)12, "NotFriend");
        Chat chat = new Chat((long) 5);
        ProfilePost profilePost = new ProfilePost((long) 3);
        Topic f1 = new Topic((long) 6);
        Topic f2 = new Topic((long) 7);
        Topic f3 = new Topic((long) 8);
        Answer a1 = new Answer((long) 9);
        Answer a2 = new Answer((long) 10);
        Answer a3 = new Answer((long) 11);

        profilePost.setCreator(friend);
        friend.addWrittenProfilePost(profilePost);
        profilePost.setOwner(user);
        user.addProfilePost(profilePost);

        topicService.createTopic(f1);
        topicService.createTopic(f2);
        topicService.createTopic(f3);
        answerService.createAnswer(a1);
        answerService.createAnswer(a2);
        answerService.createAnswer(a3);
        userService.createUser(friend2);
        userService.createUser(user);
        userService.createUser(friend);
        userService.createUser(notFriend);
        userService.createRelationshipFriend(1,2);
        userService.createRelationshipFriend(1,4);
        userService.createRelationshipFriend(12,4);
        profilePostService.createProfilePost(profilePost);
        chatService.addUserToChat(5,1);
        topicService.setCreator(6, 1);
        topicService.setCreator(6, 2);
        topicService.setCreator(6, 3);
        answerService.setCreator(9, 1);
        answerService.setCreator(10, 1);
        answerService.setCreator(11, 1);
        answerService.addPlus(9, 2);
        answerService.addPlus(10, 2);
    }

    @Test
    public void getUserPlusesTest(){
        Optional<User> optUser = userRepository.findById((long) 2);
        int num = userService.getUserPluses((long) 2);
        Assert.assertEquals(optUser.get().getPluses().size(), num);
    }

    @Test
    public void getUserPlusesQueryTest(){
        User user = new User("Lony");
        userService.createUser(user);
        Answer answer = new Answer();
        answerService.createAnswer(answer);
        answerService.addPlus(answer.getAnswerID(), user.getUserID());

        User optUser = userRepository.getUserFromId(user.getUserID());
        int num = userService.getUserPluses((user.getUserID()));

        Assert.assertEquals(optUser.getPluses().size(), num);
    }

    @Test
    public void getUserTopicsTest(){
        Optional<User> optUser = userRepository.findById((long) 1);
        List<Topic> topics = userService.getUserTopics((long)1, 2);

        Assert.assertTrue(optUser.get().getTopics().containsAll(topics));
    }

    @Test
    public void getUserForumAnswersTest(){
        Optional<User> optUser = userRepository.findById((long) 1);
        List<Answer> answerList = userService.getUserForumAnswers((long)1, 2);

        Assert.assertTrue(optUser.get().getAnswers().containsAll(answerList));
    }

    @Test
    public void getChatsTest(){
        Optional<User> optUser = userRepository.findById((long) 1);
        List<Chat> chats = userService.getCurrentChatSessions(1);

        Assert.assertEquals(chats, optUser.get().getChats());
    }

    @Test
    public void getFriendsTest(){
        Optional<User> optUser1 = userRepository.findById((long) 1);
        List<User> friends = userService.getFriends(1);

        Assert.assertEquals(friends, optUser1.get().getFriends());
    }

    @Test
    public void createRelationShipHaveAndIsAboutTest(){
        Optional<User> optUser1 = userRepository.findById((long) 1);
        Optional<ProfilePost> optProfilePost1 = profilePostRepository.findById((long) 3);

        Assert.assertEquals(optUser1.get().getFirstName(), optProfilePost1.get().getOwner().getFirstName());
        Assert.assertTrue(optUser1.get().getProfilePosts().contains(optProfilePost1.get()));

    }

    @Test
    public void createRelationShipWroteAndWrittenByTest(){
        Optional<User> optUser1 = userRepository.findById((long) 2);
        Optional<ProfilePost> optProfilePost1 = profilePostRepository.findById((long) 3);

        Assert.assertEquals(optUser1.get().getFirstName(), optProfilePost1.get().getCreator().getFirstName());
        Assert.assertTrue(optUser1.get().getWrittenProfilePosts().contains(optProfilePost1.get()));
    }


    @Test
    public void createRelationshipFriendTest(){
        Optional<User> optUser1 = userRepository.findById((long) 1);
        Optional<User> optUser2 = userRepository.findById((long) 2);

        Assert.assertTrue(optUser1.get().getFriends().contains(optUser2.get()));
        Assert.assertTrue(optUser2.get().getFriends().contains(optUser1.get()));
    }

    @Test
    public void findByIdUserTest(){
        Optional<User> optUser = userRepository.findById((long) 1);
        Assert.assertEquals("TestUser", optUser.get().getFirstName());
    }

    @Test
    public void deleteUserTest(){
        Optional<User> optUser = userRepository.findById((long) 1);
        Assert.assertTrue(optUser.isPresent());

        userRepository.deleteById((long) 1);

        optUser = userRepository.findById((long) 1);
        Assert.assertFalse(optUser.isPresent());
    }

//    @Test
//    public void findSimilarUsersTest(){
//        List<User> usersList = userService.findSimilarUsers(252, 10);
//
//        Assert.assertEquals(usersList.get(0).getFirstName(), "NotFriend");
//    }
}
