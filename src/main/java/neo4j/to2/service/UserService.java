package neo4j.to2.service;

import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Chat;
import neo4j.to2.domain.ForumThread;
import neo4j.to2.domain.User;
import neo4j.to2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User getUser(long userID) {
        Optional<User> optMessage = userRepository.findById(userID);
        return optMessage.orElse(null);
    }

    public User getUserFromId(Long userID){
        return userRepository.getUserFromId(userID);
    }

    public boolean createRelationshipFriend(long userID1, long userID2){
        Optional<User> optUser1 = userRepository.findById(userID1);
        Optional<User> optUser2 = userRepository.findById(userID2);
        if(!optUser1.isPresent() || !optUser2.isPresent())
            return false;

        optUser1.get().addFriend(optUser2.get());
        optUser2.get().addFriend(optUser1.get());
        return true;
    }

    public int loginUser(String login, String password) {
        //TODO
        return 0;
    }

    public List<User> getFriends(long userID) {
        Optional<User> optUser = userRepository.findById(userID);
        if(!optUser.isPresent())
            return null;

        return optUser.get().getFriends();
    }

    public List<Chat> getCurrentChatSessions(long userID) {
        Optional<User> optUser = userRepository.findById(userID);
        if(!optUser.isPresent())
            return null;

        return optUser.get().getChats();
    }

    public List<ForumThread> getUserForumThreads(long userID, int number) {
        Optional<User> optUser = userRepository.findById(userID);
        if(!optUser.isPresent())
            return null;

        if(optUser.get().getForumThreads().size() < number)
            number = optUser.get().getForumThreads().size();

        return optUser.get().getForumThreads().subList(0, number);
    }

    public List<Answer> getUserForumAnswers(long userID, int number) {
        Optional<User> optUser = userRepository.findById(userID);
        if(!optUser.isPresent())
            return null;

        if(optUser.get().getAnswers().size() < number)
            number = optUser.get().getAnswers().size();

        return optUser.get().getAnswers().subList(0, number);
    }

    public int getUserPluses(long userID) {
        Optional<User> optUser = userRepository.findById(userID);
        if(optUser.get().getPluses() == null)
            return -1;
        return optUser.map(user -> user.getPluses().size()).orElse(-1);
    }

    public List<User> findSpecificUsers(User user) {
        //TODO
        return null;
    }

    public List<User> findSimilarUsers(long userID, int maxUsers){
        return userRepository.findSimilarUsers(userID, maxUsers);
    }
}
