package server.DAOs;

import server.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IForumDAOImplMock implements IForumDAO {

    List<Section> mockSections ;
    List<Section> mockCategories  ;
    List<Topic> mockTopics ;
    List<Post> mockPosts ;
    List<User> mockUsers ;
    List<PostPlus> mockPostPluses ;
    List<TopicPlus> mockTopicPluses ;

    private int currentSectionid = 0;
    private int currentCategoryId = 0;
    private int currentTopicId = 0;
    private int currentPostId = 0;
    private int currentUserId = 0;
    private int currentPostPlusId = 0;
    private int currentTopicPlusId = 0;
    private Random r = new Random();

    public IForumDAOImplMock() {
        initializeMockData();
    }

    @Override
    public void initializeMockData() {

//        mockSections = new ArrayList<>();
        mockCategories = new ArrayList<>();
        mockTopics = new ArrayList<>();
        mockPosts = new ArrayList<>();
        mockUsers = new ArrayList<>();
        mockPostPluses = new ArrayList<>();
        mockTopicPluses = new ArrayList<>();

//        currentSectionid = 0;
        currentCategoryId = 0;
        currentTopicId = 0;
        currentPostId = 0;
        currentUserId = 0;
        currentPostPlusId = 0;
        currentTopicPlusId = 0;

        for (int i = 0; i < 10; i++) {
            User mockUser = new User(++currentUserId);
            mockUsers.add(mockUser);
        }


//        for (int i = 0; i < 3; i++) {
//            Section mock = new Section(++currentSectionid, "Section " + currentSectionid + " mock name\n", "Section " + currentSectionid + " mock desc\n", Collections.EMPTY_LIST);
//            mockSections.add(mock);
//
//            ArrayList<Integer> objects = new ArrayList<>();
//            objects.add(1);
//            objects.add(2);

            for (int j = 0; j < 3; j++) {
                Section mockCategory = new Section(++currentCategoryId, 3, "Section " + currentCategoryId + " mock name\n", "Section " + currentCategoryId + " mock desc\n", Collections.EMPTY_LIST);
                mockCategories.add(mockCategory);

                List<Integer> topics = new ArrayList<>();

                for (int k = 0; k < 3; k++) {
                    int randPlusNumb = r.nextInt(currentUserId) + 1;

                    ++currentTopicId;
                    for (int m = 0; m < randPlusNumb; m++) {
                        TopicPlus mockTopicPlus = new TopicPlus(++currentTopicPlusId, currentTopicId, r.nextInt(currentUserId) + 1);
                        mockTopicPluses.add(mockTopicPlus);
                    }

                    Topic mockTopic = new Topic(currentTopicId, currentCategoryId, 3, k + 1, "Topic " + currentTopicId + " mock name\n", "Topic " + currentTopicId + " mock desc\n", Collections.EMPTY_LIST, Collections.EMPTY_SET, randPlusNumb);
                    mockTopics.add(mockTopic);

                    topics.add(currentTopicId);


                    for (int l = 0; l < 3; l++) {

                        randPlusNumb = r.nextInt(currentUserId) + 1;

                        ++currentPostId;
                        for (int m = 0; m < randPlusNumb; m++) {
                            PostPlus mockPostPlus = new PostPlus(++currentPostPlusId, currentPostId, r.nextInt(currentUserId) + 1);
                            mockPostPluses.add(mockPostPlus);
                        }

                        Post mockPost = new Post(currentPostId, currentTopicId, l + 1, "Sample content of " + currentPostId + "post\n", r.nextInt(currentUserId) + 1, Collections.EMPTY_SET, randPlusNumb);
                        mockPosts.add(mockPost);
                    }
                }
                mockCategory.setTopics(topics);
            }
//        }

    }

    @Override
    public int createCategory(Section category) {
        category.setId(++currentCategoryId);
        mockCategories.add(category);
        return currentCategoryId;
    }

    @Override
    public Section getCategory(int categoryID) {
        return mockCategories.stream().filter(e -> e.getId() == categoryID).collect(Collectors.toList()).get(0);
    }

    @Override
    public void updateCategory(Section category) {
        Section mockCategory = mockCategories.stream().filter(e -> e.getId() == category.getId()).collect(Collectors.toList()).get(0);
        mockCategories.remove(mockCategory);
        mockCategories.add(category);
    }

    @Override
    public boolean deleteCategory(int categoryID) {
        Section mockCategory = mockCategories.stream().filter(e -> e.getId() == categoryID).collect(Collectors.toList()).get(0);

        if (mockCategory != null) {
            mockCategories.remove(mockCategory);
            return true;
        }
        return false;
    }

    @Override
    public List<Section> getCategoriesForSection(int sectionId) {
        return mockCategories.stream().filter(e -> e.getSectionId() == sectionId).collect(Collectors.toList());
    }

    @Override
    public List<Section> getSections() {
        return mockCategories;
    }
//
//    @Override
//    public List<Section> getSections() {
//        return mockSections;
//    }

    @Override
    public int createTopic(Topic topic) {
        topic.setId(++currentTopicId);
        Section category = getCategory(topic.getCategoryId());
        category.increaseTopicCount();
        Integer numberInCategory = category.getTopicCount();
        topic.setNumberInCategory(numberInCategory);
        mockTopics.add(topic);
        return currentTopicId;
    }

    @Override
    public void updateTopic(Topic topic) {
        Topic mockTopic = mockTopics.stream().filter(e -> e.getId() == topic.getId()).collect(Collectors.toList()).get(0);
        mockTopics.remove(mockTopic);
        mockTopics.add(topic);
    }

    @Override
    public Topic getTopic(int topicID) {
        return mockTopics.stream().filter(e -> e.getId() == topicID).collect(Collectors.toList()).get(0);
    }

    @Override
    public boolean deleteTopic(int topicID) {
        Topic mockTopic = mockTopics.stream().filter(e -> e.getId() == topicID).collect(Collectors.toList()).get(0);
        if (mockTopic != null) {
            mockTopics.remove(mockTopic);
            getCategory(mockTopic.getCategoryId()).decreaseTopicCount();
            return true;
        }

        return false;
    }


    @Override
    public List<Post> getPostsFromTopic(int topicID) {
        return (List<Post>) mockPosts.stream().filter(e -> e.getTopicId() == topicID).collect(Collectors.toList());
    }

    @Override
    public int createPostInTopic(Post post) {
        Topic topic = getTopic(post.getTopicId());
        topic.incresePostCount();
        Integer postCount = topic.getPostCount();
        post.setId(++currentPostId);
        post.setNumberInTopic(postCount);
        return currentPostId;
    }

    @Override
    public Post getPost(int postID) {
        return mockPosts.stream().filter(e -> e.getId() == postID).collect(Collectors.toList()).get(0);
    }

    @Override
    public void updatePostInTopic(Post post) {
        Post mockPost = mockPosts.stream().filter(e -> e.getId() == post.getId()).collect(Collectors.toList()).get(0);
        mockPosts.remove(mockPost);
        mockPosts.add(post);
    }

    @Override
    public boolean deletePost(int postID) {
        Post mockPost = mockPosts.stream().filter(e -> e.getId() == postID).collect(Collectors.toList()).get(0);

        if (mockPost != null) {
            Topic topic = getTopic(mockPost.getTopicId());
            topic.decreasePostCount();
            mockPosts.remove(mockPost);
            return true;
        }
        return false;
    }

    @Override
    public boolean addTopicPlus(int userID, int topicID) {
        TopicPlus mockTopicPlus = new TopicPlus(++currentTopicPlusId, topicID, userID);
        getTopic(topicID).increasePlusCount();
        return true;
    }

    @Override
    public boolean removeTopicPlus(int userID, int topicID) {
        TopicPlus topicPlus = mockTopicPluses.stream().filter(e -> e.getUserId() == userID && e.getTopicId() == topicID).collect(Collectors.toList()).get(0);
        getTopic(topicID).decreasePlusCount();

        if (topicPlus != null) {
            mockTopicPluses.remove(topicPlus);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPostPlus(int userID, int postID) {
        PostPlus postPlus = new PostPlus(++currentPostPlusId, postID, userID);
        mockPostPluses.add(postPlus);
        getPost(postID).increasePlusesCount();
        return true;
    }

    @Override
    public boolean removePostPlus(int userID, int postId) {
        PostPlus postPlus = mockPostPluses.stream().filter(e -> e.getUserId() == userID && e.getPostId() == postId).collect(Collectors.toList()).get(0);

        if (postPlus != null) {
            mockPostPluses.remove(postPlus);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getPlusesTopic(int topicID) {
        List<Integer> userIds = mockTopicPluses.stream().filter(plus -> plus.getTopicId() == topicID).map(TopicPlus::getUserId).collect(Collectors.toList());
        return mockUsers.stream().filter(user -> userIds.contains(user.getId())).collect(Collectors.toList());
    }

    @Override
    public List<User> getPlusesPost(int postID) {
        List<Integer> userIds = mockPostPluses.stream().filter(plus -> plus.getPostId() == postID).map(PostPlus::getUserId).collect(Collectors.toList());
        return mockUsers.stream().filter(user -> userIds.contains(user.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Topic> getTopicsFromCategory(Integer categoryId, Integer from, Integer to) {
        // todo posortować topici, trzymać w treesecie

        List<Topic> res = new ArrayList<>();

        for (int i = from - 1; i < to; i++) {
            res.add(mockTopics.get(i));
        }

        return res;
    }


}
