package server.Models;

public class ProfilePost {
    private int userId;
    private String post;

    public ProfilePost(int userId, String post) {
        this.userId = userId;
        this.post = post;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
