package neo4j.to2.service;

import neo4j.to2.domain.Category;
import neo4j.to2.domain.ForumThread;
import neo4j.to2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category){
        return categoryRepository.save(category);
    }

    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public void delete(Category category){
        categoryRepository.delete(category);
    }

    public Category getCategory(long categoryID){
        Optional<Category> optAnswer = categoryRepository.findById(categoryID);
        return optAnswer.orElse(null);
    }

    public List<ForumThread> getForumThreadsForCategory(long categoryID, int from, int to) {
        Optional<Category> optCategory = categoryRepository.findById(categoryID);
        if(!optCategory.isPresent())
            return null;

        List<ForumThread> forumThreads = optCategory.get().getForumThreads();

        if(forumThreads.size() < to)
            return null;
        return forumThreads.subList(from, to);
    }

    public List<Category> getCategories(int number) {
        return categoryRepository.getCategories();
    }
}
