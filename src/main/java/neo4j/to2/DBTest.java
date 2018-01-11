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
    private ForumThreadService forumThreadService;

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CategoryService categoryService;

    public void generateDataAndTest(){
        User user = new User("TestUser");
        User friend = new User("Friend");
        User friend2 = new User("Friend2");
        User friend3 = new User("NotFriend");
        Chat chat = new Chat("FirstChat");
        ProfilePost profilePost = new ProfilePost();
        ForumThread f1 = new ForumThread();
        ForumThread f2 = new ForumThread();
        ForumThread f3 = new ForumThread();
        Answer a1 = new Answer();
        Answer a2 = new Answer();
        Answer a3 = new Answer();
        Message message1 = new Message("Hej");
        Message message2 = new Message("Siemka");
        Category category = new Category("Java");


        profilePost.setCreator(friend);
        friend.addWrittenProfilePost(profilePost);
        profilePost.setOwner(user);
        user.addProfilePost(profilePost);

        forumThreadService.create(f1);
        forumThreadService.create(f2);
        forumThreadService.create(f3);
        answerService.create(a1);
        answerService.create(a2);
        answerService.create(a3);
        userService.create(friend2);
        userService.create(user);
        userService.create(friend);
        userService.create(friend3);
        chatService.create(chat);
        profilePostService.create(profilePost);
        messageService.create(message1);
        messageService.create(message2);
        categoryService.create(category);

        long userID = user.getUserID();
        long friendID = friend.getUserID();
        long friend2ID = friend2.getUserID();
        long friend3ID = friend3.getUserID();
        long profilePostID = profilePost.getProfilePostID();
        long f1ID = f1.getForumThreadID();
        long f2ID = f2.getForumThreadID();
        long f3ID = f3.getForumThreadID();
        long a1ID = a1.getAnswerID();
        long a2ID = a2.getAnswerID();
        long a3ID = a3.getAnswerID();
        long chatID = chat.getChatID();
        long message1ID = message1.getMessageID();
        long message2ID = message2.getMessageID();
        long categoryID = category.getCategoryID();

        userService.createRelationshipFriend(userID,friendID);
        userService.createRelationshipFriend(userID,friend2ID);
//        userService.createRelationshipFriend(friend3ID,friend2ID);
        chatService.addUserToChat(chatID,userID);
        forumThreadService.setCreator(f1ID, userID);
        forumThreadService.setCreator(f2ID, friendID);
        forumThreadService.setCreator(f3ID, friend2ID);
        answerService.setCreator(a1ID, userID);
        answerService.setCreator(a2ID, userID);
        answerService.setCreator(a3ID, userID);
        answerService.addPlus(a1ID, friendID);
        answerService.addPlus(a2ID, friendID);
        answerService.addPlus(a3ID, friend3ID);
        answerService.setForumThread(f1ID, a1ID);
        answerService.setForumThread(f1ID, a2ID);
        answerService.setForumThread(f2ID, a3ID);
        chatService.sendMessage(chatID, message1ID, friendID);
        chatService.sendMessage(chatID, message2ID, friend2ID);
        forumThreadService.setCategory(f1ID, categoryID);
        forumThreadService.setCategory(f2ID, categoryID);
        forumThreadService.setCategory(f3ID, categoryID);

        forumThreadService.update(f1);
        forumThreadService.update(f2);
        forumThreadService.update(f3);
        answerService.update(a1);
        answerService.update(a2);
        answerService.update(a3);
        userService.update(friend2);
        userService.update(user);
        userService.update(friend);
        chatService.update(chat);
        messageService.update(message1);
        messageService.update(message2);
        categoryService.update(category);

        Optional<User> optUser = userRepository.findById(userID);
        System.out.println(optUser.get().toString());

        List<ForumThread> forumThreads = userService.getUserForumThreads(userID, 2);
        for(ForumThread f : forumThreads)
            System.out.println(f.toString());

        List<User> similarUsers = userService.findSimilarUsers(userID, 10);
        for(User u : similarUsers)
            System.out.println(u.getFirstName());

        List<ProfilePost> profilePostsList = profilePostService.getFriendsProfilePosts(userID, 0, 2);
        for(ProfilePost p : profilePostsList)
            System.out.println(p.getTimestamp());
    }
}
