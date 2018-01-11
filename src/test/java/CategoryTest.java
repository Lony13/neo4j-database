import neo4j.to2.domain.Category;
import neo4j.to2.domain.ForumThread;
import neo4j.to2.repository.CategoryRepository;
import neo4j.to2.repository.ForumThreadRepository;
import neo4j.to2.service.CategoryService;
import neo4j.to2.service.ForumThreadService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class CategoryTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ForumThreadService forumThreadService;

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Before
    public void prepareData(){
        Category category = new Category((long) 1);
        categoryService.create(category);
        ForumThread forumThread = new ForumThread((long) 2);
        forumThreadService.create(forumThread);
        forumThreadService.setCategory(2, 1);
    }

    @Test
    public void findByIdCategoryTest(){
        Optional<Category> optCategory = categoryRepository.findById((long) 1);
        long id = optCategory.get().getCategoryID();
        Assert.assertEquals(1, id);
    }

    @Test
    public void getForumThreadsForCategoryTest(){
        Optional<Category> optCategory = categoryRepository.findById((long) 1);

        List<ForumThread> forumThreadList = categoryService.getForumThreadsForCategory(1, 0, 1);
        Assert.assertTrue(optCategory.get().getForumThreads().containsAll(forumThreadList));
        Assert.assertNull(categoryService.getForumThreadsForCategory(1, 2, 5));
    }

    @Test
    public void getCategoriesTest(){
        Category category1 = new Category("Java");
        Category category2 = new Category("Scala");
        Category category3 = new Category("Kotlin");

        categoryService.create(category1);
        categoryService.create(category2);
        categoryService.create(category3);

        List<Category> categories = categoryService.getCategories(3);

        Assert.assertTrue(categories.size() >= 3);
    }
}
