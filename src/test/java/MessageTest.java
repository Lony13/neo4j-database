import neo4j.to2.domain.Message;
import neo4j.to2.repository.MessageRepository;
import neo4j.to2.service.MessageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class MessageTest {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void prepareData(){
        Message message = new Message((long) 1, "Rakoczy ma oczy");
        messageService.create(message);
    }

    @Test
    public void findByIdMessageTest(){
        Optional<Message> optMessage = messageRepository.findById((long) 1);
        long id = optMessage.get().getMessageID();
        Assert.assertEquals(1, id);
    }

    @Test
    public void getMessageTest(){
        Optional<Message> optMessage = messageRepository.findById((long) 1);
        Message message = messageService.getMessage(1);

        Assert.assertEquals(optMessage.get(), message);
    }

    @Test
    public void getMessageTextTest(){
        Optional<Message> optMessage = messageRepository.findById((long) 1);
        String messageText = messageService.getMessageText(1);

        Assert.assertEquals(optMessage.get().getText(), messageText);
    }
}
