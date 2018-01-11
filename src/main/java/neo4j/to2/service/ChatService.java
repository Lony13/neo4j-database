package neo4j.to2.service;

import neo4j.to2.domain.Chat;
import neo4j.to2.domain.Message;
import neo4j.to2.domain.User;
import neo4j.to2.repository.ChatRepository;
import neo4j.to2.repository.MessageRepository;
import neo4j.to2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Chat createChat(Chat chat){
        return chatRepository.save(chat);
    }

    public Chat updateChat(Chat chat){
        return chatRepository.save(chat);
    }

    public void deleteChat(Chat chat){
        chatRepository.delete(chat);
    }

    public Chat getChat(long chatID){
        Optional<Chat> optAnswer = chatRepository.findById(chatID);
        return optAnswer.orElse(null);
    }

    public boolean addUserToChat(long chatID, long userID){
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Chat> optChat = chatRepository.findById(chatID);

        if(!optUser.isPresent() || !optChat.isPresent())
            return false;

        optChat.get().addParticipant(optUser.get());
        optUser.get().addChat(optChat.get());
        return true;
    }

    public boolean sendMessage(long chatID, long messageID, long senderID){
        Optional<User> optSender = userRepository.findById(senderID);
        Optional<Message> optMessage = messageRepository.findById(messageID);
        Optional<Chat> optChat = chatRepository.findById((chatID));

        if(!optMessage.isPresent() || !optChat.isPresent() || !optSender.isPresent())
            return false;

        optChat.get().addMessage(optMessage.get());
        optMessage.get().setChat(optChat.get());

        optMessage.get().setCreator(optSender.get());
        optSender.get().addMessage(optMessage.get());
        return true;
    }

    public List<User> getUsersFromChat(long chatID) {
        Optional<Chat> optChat = chatRepository.findById(chatID);

        if(!optChat.isPresent())
            return null;

        return optChat.get().getParticipants();
    }

    public boolean removeUserFromChat(long chatID, long userID) {
        Optional<Chat> optChat = chatRepository.findById(chatID);
        Optional<User> optUser = userRepository.findById(userID);

        if(!optChat.isPresent() || !optUser.isPresent())
            return false;

        optChat.get().getParticipants().remove(optUser.get());
        optUser.get().getChats().remove(optChat.get());
        return true;
    }

    public List<Message> getMessages(long chatID, int number) {
        Optional<Chat> optChat = chatRepository.findById(chatID);

        if(!optChat.isPresent())
            return null;

        if(optChat.get().getMessages().size() < number)
            return optChat.get().getMessages();

        return optChat.get().getMessages().subList(0, number);
    }
}
