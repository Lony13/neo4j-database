package neo4j.to2.service;

import neo4j.to2.domain.Message;
import neo4j.to2.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public Message updateMessage(Message message){
        return messageRepository.save(message);
    }

    public void deleteMessage(Message message){
        messageRepository.delete(message);
    }

    public Message getMessage(long messageID) {
        Optional<Message> optMessage = messageRepository.findById(messageID);
        return optMessage.orElse(null);
    }

    public String getMessageText(long messageID) {
        Optional<Message> optMessage = messageRepository.findById(messageID);
        return  optMessage.get().getText();
    }
}
