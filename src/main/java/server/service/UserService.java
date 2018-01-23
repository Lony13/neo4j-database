package server.service;

import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Chat;
import neo4j.to2.domain.Topic;
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

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
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

    public User loginUser(String login, String password) {
        return userRepository.loginUser(login, password);
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

    public List<Topic> getUserTopics(long userID, int number) {
        Optional<User> optUser = userRepository.findById(userID);
        if(!optUser.isPresent())
            return null;

        if(optUser.get().getTopics().size() < number)
            number = optUser.get().getTopics().size();

        return optUser.get().getTopics().subList(0, number);
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
        if(optUser.get().getPlusedAnswers() == null)
            return -1;
        return optUser.map(user -> user.getPlusedAnswers().size()).orElse(-1);
    }

    public List<User> findSpecificUsers(User user) {
        List<User> resultList = null;
        List<User> listFirstName, listLastName, listCountry, listCity,
                listAddress, listExperience ,listLanguage, listProgrammingLanguage;

        if(user.getFirstName() != null){
            listFirstName = userRepository.getUserFromFirstName(user.getFirstName());
            resultList = listFirstName;
        }
        if(user.getLastName() != null){
            listLastName = userRepository.getUserFromLastName(user.getLastName());
            if(resultList != null)
                resultList.retainAll(listLastName);
            else
                resultList = listLastName;
        }
        if(user.getCountry() != null){
            listCountry = userRepository.getUserFromCountry(user.getCountry());
            if(resultList != null)
                resultList.retainAll(listCountry);
            else
                resultList = listCountry;
        }
        if(user.getCity() != null){
            listCity = userRepository.getUserFromCity(user.getCity());
            if(resultList != null)
                resultList.retainAll(listCity);
            else
                resultList = listCity;
        }
        if(user.getAddress() != null){
            listAddress = userRepository.getUserFromAddress(user.getAddress());
            if(resultList != null)
                resultList.retainAll(listAddress);
            else
                resultList = listAddress;
        }
        if(user.getExperience() != null){
            listExperience = userRepository.getUserFromExperience(user.getExperience());
            if(resultList != null)
                resultList.retainAll(listExperience);
            else
                resultList = listExperience;
        }
        if(user.getLanguages() != null){
            List<String> languages = user.getLanguages();
            for(String l : languages){
                listLanguage = userRepository.getUserFromLanguage(l);
                if(resultList != null)
                    resultList.retainAll(listLanguage);
                else
                    resultList = listLanguage;
            }
        }
        if(user.getProgrammingLanguages() != null){
            List<String> programmingLanguages = user.getProgrammingLanguages();
            for(String l : programmingLanguages){
                listProgrammingLanguage = userRepository.getUserFromProgrammingLanguage(l);
                if(resultList != null)
                    resultList.retainAll(listProgrammingLanguage);
                else
                    resultList = listProgrammingLanguage;
            }
        }

        return resultList;
    }

    public List<User> findSimilarUsers(long userID, int maxUsers){
        return userRepository.findSimilarUsers(userID, maxUsers);
    }
}
