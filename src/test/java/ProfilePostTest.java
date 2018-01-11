import neo4j.to2.domain.ProfilePost;
import neo4j.to2.domain.User;
import neo4j.to2.repository.ProfilePostRepository;
import neo4j.to2.repository.UserRepository;
import neo4j.to2.service.ProfilePostService;
import neo4j.to2.service.UserService;
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
public class ProfilePostTest {
    @Autowired
    private ProfilePostService profilePostService;

    @Autowired
    private ProfilePostRepository profilePostRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void prepareData(){
        User user1 = new User((long) 1, "TestUser");
        User user2 = new User((long) 2, "Friend");
        ProfilePost profilePost1 = new ProfilePost((long) 3);
        ProfilePost profilePost2 = new ProfilePost((long) 4);
        ProfilePost profilePost3 = new ProfilePost((long) 5);

        userService.create(user1);
        userService.create(user2);
        profilePostService.create(profilePost1);
        profilePostService.create(profilePost2);
        profilePostService.create(profilePost3);
        profilePostService.addCreatorToProfilePost(1, 3);
        profilePostService.addOwnerToProfilePost(2, 3);
        profilePostService.addOwnerToProfilePost(2, 4);
        profilePostService.addOwnerToProfilePost(2, 5);
    }

    @Test
    public void getUserProfilePostsTest(){
        Optional<User> optUser = userRepository.findById((long) 2);
        List<ProfilePost> profilePosts = profilePostService.getUserProfilePosts(2,2);

        Assert.assertTrue(optUser.get().getProfilePosts().containsAll(profilePosts));
    }

    @Test
    public void addOwnerToProfilePostTest(){
        Optional<User> optUser1 = userRepository.findById((long) 2);
        Optional<ProfilePost> optProfilePost1 = profilePostRepository.findById((long) 3);

        Assert.assertEquals(optUser1.get().getFirstName(), optProfilePost1.get().getOwner().getFirstName());
        Assert.assertTrue(optUser1.get().getProfilePosts().contains(optProfilePost1.get()));
    }

    @Test
    public void addCreatorToProfilePostTest(){
        Optional<User> optUser1 = userRepository.findById((long) 1);
        Optional<ProfilePost> optProfilePost1 = profilePostRepository.findById((long) 3);

        Assert.assertEquals(optUser1.get().getFirstName(), optProfilePost1.get().getCreator().getFirstName());
        Assert.assertTrue(optUser1.get().getWrittenProfilePosts().contains(optProfilePost1.get()));
    }

    @Test
    public void findByIdProfilePostTest(){
        Optional<ProfilePost> optProfilePost = profilePostRepository.findById((long) 3);
        long id = optProfilePost.get().getProfilePostID();
        Assert.assertEquals(3, id);
    }

    @Test
    public void deleteProfilePostTest(){
        Optional<ProfilePost> profilePost = profilePostRepository.findById((long) 3);
        Assert.assertTrue(profilePost.isPresent());

        profilePostRepository.deleteById((long) 3);

        profilePost = profilePostRepository.findById((long) 3);
        Assert.assertFalse(profilePost.isPresent());
    }
}
