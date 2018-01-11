import neo4j.to2.domain.Chat;
import neo4j.to2.domain.Message;
import neo4j.to2.domain.User;
import neo4j.to2.repository.ChatRepository;
import neo4j.to2.repository.MessageRepository;
import neo4j.to2.repository.UserRepository;
import neo4j.to2.service.ChatService;
import neo4j.to2.service.MessageService;
import neo4j.to2.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class ChatTest {
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void prepareData(){
        Chat chat = new Chat((long) 1);
        Message m1 = new Message((long) 2);
        Message m2 = new Message((long) 3);
        Message m3 = new Message((long) 6);
        User u1 = new User((long) 4, "abba");
        User u2 = new User((long) 5, "ja");
        m1.setChat(chat);
        m2.setChat(chat);
        chat.addMessage(m1);
        chat.addMessage(m2);
        u1.addChat(chat);
        chat.addParticipant(u1);
        chatService.createChat(chat);
        messageService.createMessage(m1);
        messageService.createMessage(m2);
        messageService.createMessage(m3);
        userService.createUser(u1);
        userService.createUser(u2);
        chatService.addUserToChat(1,5);
    }

    @Test
    public void sendMessageTest(){
        chatService.sendMessage(1,6, 4);
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        Optional<Message> optMessage = messageRepository.findById((long) 6);
        Optional<User> optSender = userRepository.findById((long) 4);

        Assert.assertEquals(optChat.get(), optMessage.get().getChat());
        Assert.assertEquals(optSender.get(), optMessage.get().getCreator());
    }

    @Test
    public void addUserToChatTest(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        Optional<User> optUser = userRepository.findById((long) 5);

        Assert.assertTrue(optChat.get().getParticipants().contains(optUser.get()));
        Assert.assertTrue(optUser.get().getChats().contains(optChat.get()));
    }

    @Test
    public void createRelationShipParticipatesAndParticipants(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        Optional<User> optUser = userRepository.findById((long) 4);

        Assert.assertTrue(optChat.get().getParticipants().contains(optUser.get()));
        Assert.assertTrue(optUser.get().getChats().contains(optChat.get()));
    }

    @Test
    public void createRelationShipHaveAndBelongsToTest(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        Optional<Message> optMessage = messageRepository.findById((long) 3);

        Assert.assertEquals(optChat.get(), optMessage.get().getChat());
    }

    @Test
    public void findByIdChatTest(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        long id = optChat.get().getChatID();
        Assert.assertEquals(1, id);
    }

    @Test
    public void getUsersFromChatTest(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        List<User> usersList = chatService.getUsersFromChat(1);
        Assert.assertTrue(optChat.get().getParticipants().containsAll(usersList));
    }

    @Test
    public void removeUserFromChatTest(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        Optional<User> optUser = userRepository.findById((long) 4);

        chatService.removeUserFromChat(1,4);
        Assert.assertFalse(optChat.get().getParticipants().contains(optUser.get()));
    }

    @Test
    public void getMessagesTest(){
        Optional<Chat> optChat = chatRepository.findById((long) 1);
        List<Message> messagesList = chatService.getMessages(1, 2);
        Assert.assertTrue(optChat.get().getMessages().containsAll(messagesList));
    }
}
