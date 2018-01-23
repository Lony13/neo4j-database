package neo4j.to2.service;

import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Section;
import neo4j.to2.domain.Topic;
import neo4j.to2.domain.User;
import neo4j.to2.repository.SectionRepository;
import neo4j.to2.repository.TopicRepository;
import neo4j.to2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    public Topic createTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public void deleteTopic(Topic topic){
        topicRepository.delete(topic);
    }

    public Topic getTopic(long topicID){
        Optional<Topic> optAnswer = topicRepository.findById(topicID);
        return optAnswer.orElse(null);
    }

    public boolean setCreator(long topicID, long creatorID){
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        Optional<User> optCreator = userRepository.findById(creatorID);
        if(!optTopic.isPresent() || !optCreator.isPresent())
            return false;

        optCreator.get().addTopic(optTopic.get());
        optTopic.get().setCreator(optCreator.get());
        return true;
    }

    public boolean setSection(long topicID, long sectionID){
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        Optional<Section> optSection = sectionRepository.findById(sectionID);
        if(!optTopic.isPresent() || !optSection.isPresent())
            return false;

        optSection.get().addTopic(optTopic.get());
        optTopic.get().setSection(optSection.get());
        return true;
    }

    public List<Answer> getAnswersFromTopic(long topicID) {
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optTopic.isPresent())
            return null;

//        if(optTopic.get().getAnswers().size() < number)
//            number = optTopic.get().getAnswers().size();

        return optTopic.get().getAnswers();
//        return optTopic.get().getAnswers().subList(0, number);
    }

    public boolean addTopicPlus(long userID, long topicID) {
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optUser.isPresent() || !optTopic.isPresent())
            return false;

        optTopic.get().addUserPlus(optUser.get());
        optUser.get().addPlusedTopic(optTopic.get());
        return true;
    }

    public boolean removeTopicPlus(long userID, long topicID) {
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optUser.isPresent() || !optTopic.isPresent())
            return false;

        optTopic.get().removeUserPlus(optUser.get());
        optUser.get().removePlusedTopic(optTopic.get());
        return true;
    }

    public List<User> getPlusesTopic(long topicID) {
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optTopic.isPresent())
            return null;

        return optTopic.get().getUsersPlus();
    }

    public int getPlusesCountTopic(long topicID) {
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optTopic.isPresent())
            return -1;

        return optTopic.map(answer -> answer.getUsersPlus().size()).orElse(-1);
    }

    public boolean addTopicMinus(long userID, long topicID) {
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optUser.isPresent() || !optTopic.isPresent())
            return false;

        optTopic.get().addUserMinus(optUser.get());
        optUser.get().addMinussedTopic(optTopic.get());
        return true;
    }

    public boolean removeTopicMinus(long userID, long topicID) {
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optUser.isPresent() || !optTopic.isPresent())
            return false;

        optTopic.get().removeUserMinus(optUser.get());
        optUser.get().removeMinussedTopic(optTopic.get());
        return true;
    }

    public List<User> getMinusesTopic(long topicID) {
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optTopic.isPresent())
            return null;

        return optTopic.get().getUsersMinus();
    }

    public int getMinusesCountTopic(long topicID) {
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        if(!optTopic.isPresent())
            return -1;

        return optTopic.map(answer -> answer.getUsersMinus().size()).orElse(-1);
    }
}
