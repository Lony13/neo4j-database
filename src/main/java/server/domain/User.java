package server.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long userID;

    @Property
    private Long tokenID;

    @Property
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String login;

    @Property
    private String password;

    @Property
    private String email;

    @Property
    private String phone;

    @Property
    private String country;

    @Property
    private String city;

    @Property
    private String address;

    @Property
    private Integer experience;

    @Property
    private byte[] image;

    @Property
    private List<String> languages;

    @Property
    private List<String> programmingLanguages;

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
    private List<Topic> topics;

    @Relationship(type = "PLUSES", direction = Relationship.UNDIRECTED)
    private List<Answer> plusedAnswers;

    @Relationship(type = "PLUSES", direction = Relationship.UNDIRECTED)
    private List<Topic> plusedTopics;

    @Relationship(type = "MINUSES", direction = Relationship.UNDIRECTED)
    private List<Answer> minussedAnswers;

    @Relationship(type = "MINUSES", direction = Relationship.UNDIRECTED)
    private List<Topic> minussedTopics;

    public User(){}

    public User(String firstName) {
        this.firstName = firstName;
    }

    public User(Long userID, String firstName) {
        this.userID = userID;
        this.firstName = firstName;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String login, String password) {
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    public User(Long tokenID, String firstName, String lastName, String login) {
        this.tokenID = tokenID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public User(String firstName, String lastName, String country, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
    }

    public User(Long tokenID, String firstName, String lastName, String login, byte[] image) {
        this.tokenID = tokenID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.image = image;
    }

    public User(String firstName, String lastName, String login, String email,
                String phone, String country, String city, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
        this.password = password;
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

    public List<ProfilePost> getWrittenProfilePosts() {
        return writtenProfilePosts;
    }

    public void setWrittenProfilePosts(List<ProfilePost> writtenProfilePosts) {
        this.writtenProfilePosts = writtenProfilePosts;
    }

    public List<ProfilePost> getProfilePosts() {
        return profilePosts;
    }

    public void setProfilePosts(List<ProfilePost> profilePosts) {
        this.profilePosts = profilePosts;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Answer> getPlusedAnswers() {
        return plusedAnswers;
    }

    public void setPlusedAnswers(List<Answer> pluses) {
        this.plusedAnswers = pluses;
    }

    public List<Topic> getPlusedTopics() {
        return plusedTopics;
    }

    public void setPlusedTopics(List<Topic> pluses) {
        this.plusedTopics = pluses;
    }

    public List<Answer> getMinusedAnswers() {
        return minussedAnswers;
    }

    public void setMinusedAnswers(List<Answer> pluses) {
        this.minussedAnswers = pluses;
    }

    public List<Topic> getMinusedTopics() {
        return minussedTopics;
    }

    public void setMinusedTopics(List<Topic> pluses) {
        this.minussedTopics = pluses;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getTokenID() {
        return tokenID;
    }

    public void setTokenID(Long tokenID) {
        this.tokenID = tokenID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Answer> getMinussedAnswers() {
        return minussedAnswers;
    }

    public void setMinussedAnswers(List<Answer> minussedAnswers) {
        this.minussedAnswers = minussedAnswers;
    }

    public List<Topic> getMinussedTopics() {
        return minussedTopics;
    }

    public void setMinussedTopics(List<Topic> minussedTopics) {
        this.minussedTopics = minussedTopics;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getLanguages(){
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void addFriend(User user){
        if(friends == null){
            friends = new ArrayList<>();
        }

        friends.add(user);
    }

    public void addLanguage(String lang){
        if(languages == null){
            languages = new ArrayList<>();
        }

        languages.add(lang);
    }

    public void addProgrammingLanguage(String lang){
        if(programmingLanguages == null){
            programmingLanguages = new ArrayList<>();
        }

        programmingLanguages.add(lang);
    }

    public void addWrittenProfilePost(ProfilePost writtenProfilePost){
        if(writtenProfilePosts == null){
            writtenProfilePosts = new ArrayList<>();
        }

        writtenProfilePosts.add(writtenProfilePost);
    }

    public void addProfilePost(ProfilePost profilePost){
        if(profilePosts == null){
            profilePosts = new ArrayList<>();
        }

        profilePosts.add(profilePost);
    }

    public void addChat(Chat chat){
        if(chats == null){
            chats = new ArrayList<>();
        }
        chats.add(chat);
    }

    public void addMessage(Message message){
        if(messages == null){
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public void addAnswer(Answer answer){
        if(answers == null){
            answers = new ArrayList<>();
        }
        answers.add(answer);
    }

    public void addTopic(Topic topic){
        if(topics == null){
            topics = new ArrayList<>();
        }
        topics.add(topic);
    }

    public void addPlusedAnswer(Answer plus){
        if(plusedAnswers == null){
            plusedAnswers = new ArrayList<>();
        }
        plusedAnswers.add(plus);
    }

    public void removePlusedAnswer(Answer plus){
        if(plusedAnswers != null){
            plusedAnswers.remove(plus);
        }
    }

    public void addPlusedTopic(Topic plus){
        if(plusedTopics == null){
            plusedTopics = new ArrayList<>();
        }
        plusedTopics.add(plus);
    }

    public void removePlusedTopic(Topic plus){
        if(plusedTopics != null){
            plusedTopics.remove(plus);
        }
    }

    public void addMinussedAnswer(Answer minus){
        if(minussedAnswers == null){
            minussedAnswers = new ArrayList<>();
        }
        minussedAnswers.add(minus);
    }

    public void removeMinussedAnswer(Answer minus){
        if(minussedAnswers != null){
            minussedAnswers.remove(minus);
        }
    }

    public void addMinussedTopic(Topic minus){
        if(minussedTopics == null){
            minussedTopics = new ArrayList<>();
        }
        minussedTopics.add(minus);
    }

    public void removeMinussedTopic(Topic minus){
        if(minussedTopics != null){
            minussedTopics.remove(minus);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", tokenID=" + tokenID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
