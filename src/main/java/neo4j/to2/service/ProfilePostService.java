package neo4j.to2.service;

import neo4j.to2.domain.ProfilePost;
import neo4j.to2.domain.User;
import neo4j.to2.repository.ProfilePostRepository;
import neo4j.to2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfilePostService {

    @Autowired
    private ProfilePostRepository profilePostRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public ProfilePost create(ProfilePost profilePost) {
        return profilePostRepository.save(profilePost);
    }

    public ProfilePost update(ProfilePost profilePost) {
        return profilePostRepository.save(profilePost);
    }

    public void delete(ProfilePost profilePost){
        profilePostRepository.delete(profilePost);
    }

    public ProfilePost getProfilePost(long profilePostID) {
        Optional<ProfilePost> optMessage = profilePostRepository.findById(profilePostID);
        return optMessage.orElse(null);
    }

    public boolean addCreatorToProfilePost(long creatorID, long profilePostID) {
        Optional<User> optUser1 = userRepository.findById(creatorID);
        Optional<ProfilePost> optProfilePost1 = profilePostRepository.findById(profilePostID);

        if(!optUser1.isPresent() || !optProfilePost1.isPresent())
            return false;

        optUser1.get().addWrittenProfilePost(optProfilePost1.get());
        optProfilePost1.get().setCreator(optUser1.get());
        return true;
    }

    public boolean addOwnerToProfilePost(long ownerID, long profilePostID) {
        Optional<User> optUser1 = userRepository.findById(ownerID);
        Optional<ProfilePost> optProfilePost1 = profilePostRepository.findById(profilePostID);

        if(!optUser1.isPresent() || !optProfilePost1.isPresent())
            return false;

        optUser1.get().addProfilePost(optProfilePost1.get());
        optProfilePost1.get().setOwner(optUser1.get());
        return true;
    }

    public List<ProfilePost> getUserProfilePosts(long userID, int number) {
        Optional<User> optUser = userRepository.findById(userID);
        if(!optUser.isPresent())
            return null;

        if(optUser.get().getProfilePosts().size() < number)
            number = optUser.get().getProfilePosts().size();

        return optUser.get().getProfilePosts().subList(0, number);
    }

    public List<ProfilePost> getFriendsProfilePosts(long userID, int from, int to){
        User user = userService.getUser(userID);
        List<ProfilePost> profilePostsList = new ArrayList<>();
        if(user.getProfilePosts() != null)
            profilePostsList.addAll(user.getProfilePosts());
        List<User> friends = user.getFriends();

        if(friends != null) {
            for (User friend : friends) {
                if (friend.getProfilePosts() != null)
                    profilePostsList.addAll(friend.getProfilePosts());
            }
        }

        Collections.sort(profilePostsList);

        if(profilePostsList.size() < from)
            return Collections.emptyList();
        else if(profilePostsList.size() < to)
            return profilePostsList.subList(from, profilePostsList.size());
        return profilePostsList.subList(from, to);
    }
}
