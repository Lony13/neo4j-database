package server.Models;

@Deprecated
public class PostPlus {
    private int id;
    private int postId;

    public PostPlus(int id, int postId, int userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    private int userId;



    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }
}
