package server.DAOs;

import server.Models.*;

import java.util.List;

public interface IUserDAO {
    int createUser(User user);

    public int createUser(Profile profile);

    public User getUser(int userID);

    public User updateUser(User user);

    public boolean deleteUser(int UserID);

    public int logUser(String login, String password);

    public List<User> getFriends(int userID);


    public int createProfilePost(int userID, ProfilePost post);

    public ProfilePost editProfilePost(ProfilePost post);

    public boolean deleteProfilePost(int ProfilePostID);

    public List<ProfilePost> getUserProfilePosts(int userID, int number);

//    public List<ForumThread> getUserForumThreads(int userID, int number);

//    public List<Answer> getUserForumAnswers(int userID, int number);

    public List<User> findSimilarUsers(int userID, int correlationFactor);

    public int getUserPluses(int userID);

    public List<User> findSpecificUsers(User user);
}
//
//import entity.*;
//
//import java.util.List;
//
//public interface IUserDAO {
//    public int createUser(User user);
//
//    public User getUser(int userID);
//
//    public User updateUser(User user);
//
//    public boolean deleteUser(int UserID);
//
//    public int logUser(String login, String password);
//
//    public List<User> getFriends(int userID);
//
//    public List<Chat> getCurrentChatSessions(int userID);
//
//    public int createProfilePost(int userID, ProfilePost post);
//
//    public ProfilePost editProfilePost(ProfilePost post);
//
//    public boolean deleteProfilePost(int ProfilePostID);
//
//    public List<ProfilePost> getUserProfilePosts(int userID, int number);
//
//    public List<ForumThread> getUserForumThreads(int userID, int number);
//
//    public List<Answer> getUserForumAnswers(int userID, int number);
//
//    public List<User> findSimilarUsers(int userID, int correlationFactor);
//
//    public int getUserPluses(int userID);
//
//    public List<User> findSpecificUsers(User user);
//}
