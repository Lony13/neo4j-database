package server.DAOs;

import server.Models.Message;
import server.Models.User;

import java.util.List;

public class IChatDaoImpl implements IChatDAO{
    @Override
    public int createChat(List<Integer> usersId) {
        return 0;
    }

    @Override
    public List<Integer> getChatsId(String userId) {
        return null;
    }

    @Override
    public int sendMessage(int chatID, int senderID, String message) {
        return 0;
    }

    @Override
    public Message getMessage(int messageID) {
        return null;
    }

    @Override
    public String getMessageText(int messageID) {
        return null;
    }

    @Override
    public List<User> getUsersFromChat(int chatID) {
        return null;
    }

    @Override
    public boolean addUserToChat(int chatID, int userID) {
        return false;
    }

    @Override
    public boolean removeUserFromChat(int chatID, int userID) {
        return false;
    }

    @Override
    public boolean deleteChat(int chatID) {
        return false;
    }

    @Override
    public List<Message> getMessages(int chatID, int number) {
        return null;
    }
}
