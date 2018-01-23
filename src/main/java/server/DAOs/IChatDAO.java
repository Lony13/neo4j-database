package server.DAOs;


import server.Models.Message;
import server.Models.User;

import java.util.List;

public interface IChatDAO {
    //dostaje liste czlonkow chatu, zwraca chatID
    public int createChat(List<Integer> usersId);

    public List<Integer> getChatsId(String userId);

    //wysyla wiadomosc do chatu, zwraca messageID
    public int sendMessage(int chatID, int senderID, String message);

    //zwraca wiadomosc o podanym ID
    public Message getMessage(int messageID);

    //zwraca tresc wiadomosci o podanyhm id
    public String getMessageText(int messageID);

    //zwraca liste czlonkow podanego chatu
    public List<User> getUsersFromChat(int chatID);

    //dodaj uzytkownika do chatu
    public boolean addUserToChat(int chatID, int userID);

    //usun uzywkonwika z chatu
    public boolean removeUserFromChat(int chatID, int userID);

    //usun chat
    public boolean deleteChat(int chatID);

    //pobierz ostatnie x wiadomosci z chatu
    public List<Message> getMessages(int chatID, int number);
}
//
//import entity.Message;
//import entity.User;
//
//import java.util.List;
//
//public interface IChatDAO {
//    //dostaje liste czlonkow chatu, zwraca chatID
//    public int createChat(List<User> users);
//
//    //wysyla wiadomosc do chatu, zwraca messageID
//    public int sendMessage(int chatID, int senderID, String message);
//
//    //zwraca wiadomosc o podanym ID
//    public Message getMessage(int messageID);
//
//    //zwraca tresc wiadomosci o podanyhm id
//    public String getMessageText(int messageID);
//
//    //zwraca liste czlonkow podanego chatu
//    public List<User> getUsersFromChat(int chatID);
//
//    //dodaj uzytkownika do chatu
//    public boolean addUserToChat(int chatID, int userID);
//
//    //usun uzywkonwika z chatu
//    public boolean removeUserFromChat(int chatID, int userID);
//
//    //usun chat
//    public boolean deleteChat(int chatID);
//
//    //pobierz ostatnie x wiadomosci z chatu
//    public List<Message> getMessages(int chatID, int number);
//}
