package neo4j.to2.service;

import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Topic;
import neo4j.to2.domain.User;
import neo4j.to2.repository.AnswerRepository;
import neo4j.to2.repository.TopicRepository;
import neo4j.to2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Answer createAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Answer answer){
        answerRepository.delete(answer);
    }

    public Answer getAnswer(long answerID){
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        return optAnswer.orElse(null);
    }

    public boolean setTopic(long topicID, long answerID){
        Optional<Topic> optTopic = topicRepository.findById(topicID);
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        if(!optTopic.isPresent() || !optAnswer.isPresent())
            return false;

        optAnswer.get().setTopic(optTopic.get());
        optTopic.get().addAnswer(optAnswer.get());

        return true;
    }

    public boolean addPlus(long answerID, long userID){
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        if(!optUser.isPresent() || !optAnswer.isPresent())
            return false;

        optAnswer.get().addUserPlus(optUser.get());
        optUser.get().addPlus(optAnswer.get());
        return true;
    }

    public boolean setCreator(long answerID, long userID){
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        if(!optUser.isPresent() || !optAnswer.isPresent())
            return false;

        optAnswer.get().setCreator(optUser.get());
        optUser.get().addAnswer(optAnswer.get());
        return true;
    }

    public boolean removeAnswerPlus(long userID, long answerID) {
        Optional<User> optUser = userRepository.findById(userID);
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        if(!optUser.isPresent() || !optAnswer.isPresent())
            return false;

        optAnswer.get().removeUserPlus(optUser.get());
        optUser.get().removePlus(optAnswer.get());
        return true;
    }

    public List<User> getPlusesAnswer(long answerID) {
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        if(!optAnswer.isPresent())
            return null;

        return optAnswer.get().getUsersPlus();
    }

    public int getPlusesCountAnswer(long answerID) {
        Optional<Answer> optAnswer = answerRepository.findById(answerID);
        if(!optAnswer.isPresent())
            return -1;

        return optAnswer.map(answer -> answer.getUsersPlus().size()).orElse(-1);
    }
}
