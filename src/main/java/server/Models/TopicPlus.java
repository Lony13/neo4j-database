package server.Models;

@Deprecated
public class TopicPlus {
    private int id;
    private int topicId;

    public TopicPlus(int id, int topicId, int userId) {
        this.id = id;
        this.topicId = topicId;
        this.userId = userId;
    }

    private int userId;



    public int getTopicId() {
        return topicId;
    }

    public int getUserId() {
        return userId;
    }
}
