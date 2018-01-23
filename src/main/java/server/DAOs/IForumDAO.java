package server.DAOs;

import server.Models.*;

import java.util.List;

public interface IForumDAO {
    public int createCategory(Section category);

    public Section getCategory(int CategoryID);

    public void updateCategory(Section category);

    public boolean deleteCategory(int categoryID);

    public List<Section> getCategoriesForSection(int number);

    List<Section> getSections();

    public int createTopic(Topic Topic);

    public void updateTopic(Topic Topic);

    public Topic getTopic(int TopicID);

    public boolean deleteTopic(int TopicID);

    public int createPostInTopic(Post Post);

    public void updatePostInTopic(Post Post);

    public List<Post> getPostsFromTopic(int TopicID);

    public Post getPost(int PostID);

    public boolean deletePost(int PostID);

    public boolean addTopicPlus(int userID, int TopicID);

    public boolean removeTopicPlus(int userID, int TopicID);

    public boolean addPostPlus(int userID, int PostID);

    public boolean removePostPlus(int userID, int PostID);

    public List<User> getPlusesTopic(int TopicID);

    public List<User> getPlusesPost(int PostID);

    List<Topic> getTopicsFromCategory(Integer categoryId, Integer from, Integer to);

    void initializeMockData();

}
