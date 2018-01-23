package server.Models;

@Deprecated
public class User {
    public User(Integer id) {
        this.id = id;
    }

    private Integer id;

    public User() {

    }

    public Integer getId() {
        return id;
    }
}
