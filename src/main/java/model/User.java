package main.java.model;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;
    private String createAt;
    private String gmail;

    // Constructor
    public User() {
    	
    }
    
    public User(String username, String password, String gmail, boolean string) {
    	this.username = username;
    	this.password = password;
    	this.gmail = gmail;
    	this.isAdmin = string;
    }
    
    public User(int id, String username, String password, boolean isAdmin, String createAt, String gmail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createAt = createAt;
        this.gmail = gmail;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
