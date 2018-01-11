package neo4j.to2.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long userID;

    @Property
    private String firstName;

    @Property
    private String login;

    @Relationship(type = "FRIEND_OF")
    private List<User> friends;

    @Relationship(type = "WROTE", direction = Relationship.UNDIRECTED)
    private List<ProfilePost> writtenProfilePosts;

    @Relationship(type = "HAVE", direction = Relationship.UNDIRECTED)
    private List<ProfilePost> profilePosts;

    @Relationship(type = "PARTICIPATES", direction = Relationship.UNDIRECTED)
    private List<Chat> chats;

    @Relationship(type = "WROTE", direction = Relationship.UNDIRECTED)
    private List<Message> messages;

    @Relationship(type = "WROTE", direction = Relationship.UNDIRECTED)
    private List<Answer> answers;

    @Relationship(type = "CREATES", direction = Relationship.UNDIRECTED)
    private List<ForumThread> forumThreads;

    @Relationship(type = "PLUSES", direction = Relationship.UNDIRECTED)
    private List<Answer> pluses;

    public User(){}

    public User(String firstName) {
        this.firstName = firstName;
    }

    public User(Long userID, String firstName) {
        this.userID = userID;
        this.firstName = firstName;
    }

    public User(String firstName, String login) {
        this.firstName = firstName;
        this.login = login;
    }

    public User(Long userID, String firstName, String login) {
        this.userID = userID;
        this.firstName = firstName;
        this.login = login;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user){
        if(friends == null){
            friends = new ArrayList<>();
        }

        friends.add(user);
    }

    public List<ProfilePost> getWrittenProfilePosts() {
        return writtenProfilePosts;
    }

    public void setWrittenProfilePosts(List<ProfilePost> writtenProfilePosts) {
        this.writtenProfilePosts = writtenProfilePosts;
    }

    public void addWrittenProfilePost(ProfilePost writtenProfilePost){
        if(writtenProfilePosts == null){
            writtenProfilePosts = new ArrayList<>();
        }

        writtenProfilePosts.add(writtenProfilePost);
    }

    public List<ProfilePost> getProfilePosts() {
        return profilePosts;
    }

    public void setProfilePosts(List<ProfilePost> profilePosts) {
        this.profilePosts = profilePosts;
    }

    public void addProfilePost(ProfilePost profilePost){
        if(profilePosts == null){
            profilePosts = new ArrayList<>();
        }

        profilePosts.add(profilePost);
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public void addChat(Chat chat){
        if(chats == null){
            chats = new ArrayList<>();
        }
        chats.add(chat);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        if(messages == null){
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer){
        if(answers == null){
            answers = new ArrayList<>();
        }
        answers.add(answer);
    }

    public List<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public void setForumThreads(List<ForumThread> forumThreads) {
        this.forumThreads = forumThreads;
    }

    public void addForumThread(ForumThread forumThread){
        if(forumThreads == null){
            forumThreads = new ArrayList<>();
        }
        forumThreads.add(forumThread);
    }

    public List<Answer> getPluses() {
        return pluses;
    }

    public void setPluses(List<Answer> pluses) {
        this.pluses = pluses;
    }

    public void addPlus(Answer plus){
        if(pluses == null){
            pluses = new ArrayList<>();
        }
        pluses.add(plus);
    }

    public void removePlus(Answer plus){
        if(pluses != null){
            pluses.remove(plus);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
