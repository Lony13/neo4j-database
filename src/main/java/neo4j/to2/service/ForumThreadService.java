package neo4j.to2.service;

import neo4j.to2.domain.Answer;
import neo4j.to2.domain.Category;
import neo4j.to2.domain.ForumThread;
import neo4j.to2.domain.User;
import neo4j.to2.repository.CategoryRepository;
import neo4j.to2.repository.ForumThreadRepository;
import neo4j.to2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ForumThreadService {

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public ForumThread create(ForumThread forumThread){
        return forumThreadRepository.save(forumThread);
    }

    public ForumThread update(ForumThread forumThread){
        return forumThreadRepository.save(forumThread);
    }

    public void delete(ForumThread forumThread){
        forumThreadRepository.delete(forumThread);
    }

    public ForumThread getForumThread(long forumThreadID){
        Optional<ForumThread> optAnswer = forumThreadRepository.findById(forumThreadID);
        return optAnswer.orElse(null);
    }

    public boolean setCreator(long forumThreadID, long creatorID){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById(forumThreadID);
        Optional<User> optCreator = userRepository.findById(creatorID);
        if(!optForumThread.isPresent() || !optCreator.isPresent())
            return false;

        optCreator.get().addForumThread(optForumThread.get());
        optForumThread.get().setCreator(optCreator.get());
        return true;
    }

    public boolean setCategory(long forumThreadID, long categoryID){
        Optional<ForumThread> optForumThread = forumThreadRepository.findById(forumThreadID);
        Optional<Category> optCategory = categoryRepository.findById(categoryID);
        if(!optForumThread.isPresent() || !optCategory.isPresent())
            return false;

        optCategory.get().addForumThread(optForumThread.get());
        optForumThread.get().setCategory(optCategory.get());
        return true;
    }

    public List<Answer> getAnswersFromForumThread(long forumThreadID, int number) {
        Optional<ForumThread> optForumThread = forumThreadRepository.findById(forumThreadID);
        if(!optForumThread.isPresent())
            return null;

        if(optForumThread.get().getAnswers().size() < number)
            number = optForumThread.get().getAnswers().size();

        return optForumThread.get().getAnswers().subList(0, number);
    }

    public boolean addForumThreadPlus(long userID, long forumThreadID) {
        //TODO
        return false;
    }

    public boolean removeForumThreadPlus(long userID, long forumThreadID) {
        //TODO
        return false;
    }

    public List<User> getPlusesForumThread(long forumThreadID) {
        //TODO
        return null;
    }

    public int getPlusesCountForumThread(long forumThreadID) {
        //TODO
        return 0;
    }
}
