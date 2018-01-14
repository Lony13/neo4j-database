package neo4j.to2;

import neo4j.to2.domain.*;
import neo4j.to2.repository.*;
import neo4j.to2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DBTest {
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

    @Autowired
    private MessageService messageService;

    @Autowired
    private SectionService sectionService;

    public void generateDataAndTest(){
        User user = new User("TestUser");
        User friend = new User("Friend", "Best");
        User friend2 = new User("Friend2");
        User friend3 = new User("NotFriend");
        User hacker = new User("supaHaka", "totallyNotHaka", "1234");
        Chat chat = new Chat("FirstChat");
        ProfilePost profilePost = new ProfilePost();
        Topic f1 = new Topic();
        Topic f2 = new Topic();
        Topic f3 = new Topic();
        Answer a1 = new Answer();
        Answer a2 = new Answer();
        Answer a3 = new Answer();
        Message message1 = new Message("Hej");
        Message message2 = new Message("Siemka");
        Section section = new Section("Java");


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
        userService.createUser(friend3);
        userService.createUser(hacker);
        chatService.createChat(chat);
        profilePostService.createProfilePost(profilePost);
        messageService.createMessage(message1);
        messageService.createMessage(message2);
        sectionService.createSection(section);

        long userID = user.getUserID();
        long friendID = friend.getUserID();
        long friend2ID = friend2.getUserID();
        long friend3ID = friend3.getUserID();
        long hackerID = hacker.getUserID();
        long profilePostID = profilePost.getProfilePostID();
        long f1ID = f1.getTopicID();
        long f2ID = f2.getTopicID();
        long f3ID = f3.getTopicID();
        long a1ID = a1.getAnswerID();
        long a2ID = a2.getAnswerID();
        long a3ID = a3.getAnswerID();
        long chatID = chat.getChatID();
        long message1ID = message1.getMessageID();
        long message2ID = message2.getMessageID();
        long sectionID = section.getSectionID();

        userService.createRelationshipFriend(userID,friendID);
        userService.createRelationshipFriend(userID,friend2ID);
//        userService.createRelationshipFriend(friend3ID,friend2ID);
        chatService.addUserToChat(chatID,userID);
        topicService.setCreator(f1ID, userID);
        topicService.setCreator(f2ID, friendID);
        topicService.setCreator(f3ID, friend2ID);
        answerService.setCreator(a1ID, userID);
        answerService.setCreator(a2ID, userID);
        answerService.setCreator(a3ID, userID);
        answerService.addPlus(a1ID, friendID);
        answerService.addPlus(a2ID, friendID);
        answerService.addPlus(a3ID, friend3ID);
        answerService.setTopic(f1ID, a1ID);
        answerService.setTopic(f1ID, a2ID);
        answerService.setTopic(f2ID, a3ID);
        chatService.sendMessage(chatID, message1ID, friendID);
        chatService.sendMessage(chatID, message2ID, friend2ID);
        topicService.setSection(f1ID, sectionID);
        topicService.setSection(f2ID, sectionID);
        topicService.setSection(f3ID, sectionID);

        topicService.updateTopic(f1);
        topicService.updateTopic(f2);
        topicService.updateTopic(f3);
        answerService.updateAnswer(a1);
        answerService.updateAnswer(a2);
        answerService.updateAnswer(a3);
        userService.updateUser(friend2);
        userService.updateUser(user);
        userService.updateUser(friend);
        chatService.updateChat(chat);
        messageService.updateMessage(message1);
        messageService.updateMessage(message2);
        sectionService.updateSection(section);

        Optional<User> optUser = userRepository.findById(userID);
        System.out.println(optUser.get().toString());

        List<Topic> topics = userService.getUserTopics(userID, 2);
        for(Topic f : topics)
            System.out.println(f.toString());

        List<User> similarUsers = userService.findSimilarUsers(userID, 10);
        for(User u : similarUsers)
            System.out.println(u.getFirstName());

        List<ProfilePost> profilePostsList = profilePostService.getFriendsProfilePosts(userID, 0, 2);
        for(ProfilePost p : profilePostsList)
            System.out.println(p.getTimestamp());

        User sUser = new User();
        sUser.setFirstName("Friend");
        sUser.setLastName("Best");
        List<User> usersList = userService.findSpecificUsers(sUser);
        for(User u : usersList)
            System.out.println(u.getFirstName());

        User hck = userService.loginUser("totallyNotHaka", "1234");
        System.out.println(hck.getFirstName());
    }
}
