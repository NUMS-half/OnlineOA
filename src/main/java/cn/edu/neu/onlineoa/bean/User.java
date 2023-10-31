package cn.edu.neu.onlineoa.bean;

public class User {
    private String uid;
    private String username;
    private String password;
    private int identityId = -1;

    public User() {}

    public User(String uid, String username, String password, int identityId) {
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.identityId = identityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identityId=" + identityId +
                '}';
    }
}
