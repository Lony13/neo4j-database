package server.Controllers;

import server.DAOs.*;
import server.Validators.EmailExistsException;
import server.Validators.IUserService;
import server.Validators.UserService;
import server.domain.Answer;
import server.domain.Section;
import server.domain.Topic;
import server.domain.User;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import server.service.AnswerService;
import server.service.SectionService;
import server.service.TopicService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
public class Controller {
    // todo spytać chłopaków z forum co chciewl

    private SectionService sectionService = new SectionService();
    private TopicService topicService = new TopicService();
    private AnswerService answerService = new AnswerService();

    private IChatDAO iChatDAO = new IChatDaoImpl();
    private IUserDAO iUserDAO = new IUserDAOImpl();

    private IForumDAO iForumDAO = new IForumDAOImplMock();

    @RequestMapping(value = "/getSections", method = RequestMethod.GET)
    public List<Section> getSections(@RequestParam(value = "number")
                                             Integer number) {
        List<Section> categories = sectionService.getSections(number);  // todo czo tu dodać, czo to za number;
        return categories;
    }

    @RequestMapping(value = "/getSection", method = RequestMethod.GET)
    public Section getSection(@RequestParam(value = "sectionID") Long sectionID){
        return sectionService.getSection(sectionID);
    }


    @RequestMapping(value = "/createSection", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Section createSection(@RequestBody Section section,
                              @RequestParam(value = "userToken") String userToken) {
        Integer id = section.getId();


        switch (id) {
            case 0:
                return sectionService.createSection(section);
            default:
                return sectionService.updateSection(section);
        }
    }


    @RequestMapping(value = "/deleteSection", method = RequestMethod.POST)
    public void deleteSection(@RequestBody Section section,
                              @RequestParam(value = "userToken") String userToken) {

        sectionService.deleteSection(section);
    }


    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public List<Topic> getTopics(@RequestParam(value = "categoryId") Integer categoryId,
                                 @RequestParam(value = "from") Integer from,
                                 @RequestParam(value = "to") Integer to) {
        return sectionService.getTopicsForSection(categoryId, from, to);
    }

    // method adds a new topic if topicId is 0
    @RequestMapping(value = "/editTopic", method = RequestMethod.POST)
    public Topic editTopic(@RequestBody Topic topic) {
        int topicId = Topic.getId();

        switch (topicId) {
            case 0:
                return  topicService.createTopic(topic);
            default:
                return  topicService.updateTopic(topic);
        }
    }

    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    public void deleteTopic(@RequestBody Topic topic
    ) {
        topicService.deleteTopic(topic);
    }

    @RequestMapping(value = "/getTopic", method = RequestMethod.GET)
    public Topic getTopic(@RequestParam(value = "topicId") Long topicID){
        return topicService.getTopic(topicID);
    }

    @RequestMapping(value = "/getPost", method = RequestMethod.GET)
    public List<Answer> getPosts(@RequestParam(value = "topicId") Long topicId) {
        List<Answer> posts = topicService.getAnswersFromTopic(topicId);
        return posts;
    }



    @RequestMapping(value = "/editPost", method = RequestMethod.POST)
    public void editPost(@RequestParam(value = "userToken") String userToken,
                         @RequestParam(value = "answer") Answer answer) {

        Long id = answer.getId();
        switch (id.intValue()) {
            case 0:
                answerService.createAnswer(answer);
                break;
            default:
                answerService.updateAnswer(answer);
        }
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    public void deletePost(@RequestParam(value = "answer") Answer answer,
                           @RequestParam(value = "userToken") Integer userToken) {
        answerService.deleteAnswer(answer);
    }

    // value == 0 adds plus, value == 1 adds minus
    @RequestMapping(value = "/addTopicPlusOrMinus", method = RequestMethod.POST)
    public void addTopicPlusOrMinus(@RequestParam(value = "topicId") Integer topicId,
                                    @RequestParam(value = "profileId") Integer profileId,
                                    @RequestParam(value = "userToken") Integer userToken,
                                    @RequestParam(value = "value") Integer value) {

        switch (value) {
            case 0:
                topicService.addTopicPlus(profileId, topicId);
                break;
            case 1:
                topicService.addTopicMinus(profileId, topicId);
                break;
        }
    }

    // value == 0 adds plus, value == 1 adds minus
    @RequestMapping(value = "/addPostPlusMinus", method = RequestMethod.POST)
    public void addPostPlusMinus(@RequestParam(value = "postId") Integer postId,
                                   @RequestParam(value = "profileId") Integer profileId,
                                   @RequestParam(value = "userToken") Integer userToken,
                                   @RequestParam(value = "value") Integer value) {

        switch (value) {
            case 0:
                answerService.addPlus(profileId, postId);
                break;
            case 1:
                answerService.addMinus(profileId, postId);
                break;
        }
    }

    @RequestMapping(value = "/getPlusesPost", method = RequestMethod.GET)
    public List<User> getPlusesPost(@RequestParam(value = "postId") Integer postId) {
        return answerService.getPlusesAnswer(postId);
    }
    
    @RequestMapping(value = "/getMinusesPost", method = RequestMethod.GET)
    public List<User> getMinusesPost(@RequestParam(value = "postId") Integer postId) {
        return answerService.getMinusesAnswer(postId);
    }

    @RequestMapping(value = "/getPlusesTopic", method = RequestMethod.GET)
    public List<User> getPlusesTopic(@RequestParam(value = "topicId") Integer topicId) {
        return topicService.getPlusesTopic(topicId);
    }

    @RequestMapping(value = "/getMinusesTopic", method = RequestMethod.GET)
    public List<User> getMinusesTopic(@RequestParam(value = "topicId") Integer topicId) {
        return topicService.getMinusesTopic(topicId);
    }


    @RequestMapping(value = "/initializeMock", method = RequestMethod.POST)
    public void initializeMock(){
        iForumDAO.initializeMockData();
    }

//    @RequestMapping(value = "/sharePublicKeys", method = RequestMethod.GET)
//    public PublicKey sharePublicKeys(@RequestParam(value = "userPublicKey") PublicKey userPublicKey,
//                               @RequestParam(value = "userId") Integer userId){
//
//        authenticationService.addUserIdKeyPair(userId, userPublicKey);
//        return authenticationService.getServerPublicKey();
//    }
//
//    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
//    public String getToken( @RequestParam(value = "userId") Integer userId){
//        return authenticationService.generateTokenForUser(userId);
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/getProfilePosts", method = RequestMethod.GET)
    public List<ProfilePost> getProfilePosts(@RequestParam(value = "profileId") Integer profileId,
                                             @RequestParam(value = "from") Integer from,
                                             @RequestParam(value = "to") Integer to) {

        return null;
    }


    @RequestMapping(value = "/createProfilePost", method = RequestMethod.POST)
    public void createPost(@RequestParam(value = "userToken") String userToken,
                           @RequestParam(value = "profilePostId") Integer profilePostId,
                           @RequestParam(value = "profilePostContent") ProfilePostContent profilePostContent) {

    }

    // jesli id =0 dodaj nowy profil
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public void createProfile(@RequestParam(value = "profileData") ProfileData profileData) {

    }

    @RequestMapping(value = "/addPlus", method = RequestMethod.POST)
    public void addPlus(@RequestParam(value = "postId") Integer postId,
                        @RequestParam(value = "userToken") String userToken) {

    }


    @RequestMapping(value = "/chatSessions", method = RequestMethod.GET)
    public List<Integer> getSessions(@RequestParam(value = "userToken") String userToken) {
        return iChatDAO.getChatsId(userToken);
    }


    @RequestMapping(value = "/chatCreate", method = RequestMethod.POST)
    public Integer createSession(@RequestParam(value = "users") List<Integer> userIds) {
        return iChatDAO.createChat(userIds);
    }

    @RequestMapping(value = "/chatArchive", method = RequestMethod.GET)
    public List<Message> getArchive(@RequestParam(value = "userToken") String userToken,
                                    @RequestParam(value = "ChatSessionId") String chatSessionId,
                                    @RequestParam(value = "count") Integer messages) {
        return iChatDAO.getMessages(Integer.parseInt(chatSessionId),messages);
    }

    @MessageMapping("/chat")
    @SendTo("/chatSession/{chatId}")
    public Message send(@DestinationVariable String chatId, Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new Message(message.getFrom(), message.getText(), time);
    }


    @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
    public User getProfile(@RequestParam(value = "profileId") String profileId) {
        return iUserDAO.getUser(Integer.parseInt(profileId));
    }


    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public List<String> getFriends(@RequestParam(value = "profileId") String profileId) {

        Iterator iterator = iUserDAO.getFriends(Integer.parseInt(profileId)).iterator();
        List<String> returnList = new ArrayList<>();
        while(iterator.hasNext()){
            returnList.add(iterator.next().toString());
        }
        return returnList;
    }




    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        Profile registered = new Profile();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }
    private Profile createUserAccount(UserDto accountDto, BindingResult result) {
        Profile registered = null;
        try {
            IUserService service = new UserService();
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }




}
