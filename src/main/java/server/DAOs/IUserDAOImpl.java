package server.DAOs;

import server.Models.*;

import java.util.List;

public class IUserDAOImpl implements IUserDAO{
    @Override
    public int createUser(User user) {
        return 0;
    }

    @Override
    public int createUser(Profile profile) {
        return 0;
    }

    @Override
    public User getUser(int userID) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(int UserID) {
        return false;
    }

    @Override
    public int logUser(String login, String password) {
        return 0;
    }

    @Override
    public List<User> getFriends(int userID) {
        return null;
    }

    @Override
    public int createProfilePost(int userID, ProfilePost post) {
        return 0;
    }

    @Override
    public ProfilePost editProfilePost(ProfilePost post) {
        return null;
    }

    @Override
    public boolean deleteProfilePost(int ProfilePostID) {
        return false;
    }

    @Override
    public List<ProfilePost> getUserProfilePosts(int userID, int number) {
        return null;
    }

//    @Override
//    public List<ForumThread> getUserForumThreads(int userID, int number) {
//        return null;
//    }
//
//    @Override
//    public List<Answer> getUserForumAnswers(int userID, int number) {
//        return null;
//    }

    @Override
    public List<User> findSimilarUsers(int userID, int correlationFactor) {
        return null;
    }

    @Override
    public int getUserPluses(int userID) {
        return 0;
    }

    @Override
    public List<User> findSpecificUsers(User user) {
        return null;
    }
}
