package vttp2023.batch3.ssf.frontcontroller;

import java.io.StringReader;
//import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    public static final int MAX_FAILED_ATTEMPTS = 3;

    @NotNull
    @Size(min = 2, message = "Username must be at least 2 characters")
    private String username;
    
    @NotNull
    @Size(min = 2, message = "Password must be at least 2 characters")
    private String password;
    
    //private String id;

    private int failedAttempts;

    private boolean isLocked;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //this.id = id;
    }

    public User() {
        //this.id = UUID.randomUUID().toString();
        this.isLocked = false;
        this.failedAttempts = 0;
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

    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public static JsonObject toJSON(String json){
        JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static User create(String jsonStr){
        JsonObject o = toJSON(jsonStr);
        User user = new User();
        //user.setId(o.getString("id"));
        user.setUsername(o.getString("username"));
        user.setPassword(o.getString("password"));
        user.setFailedAttempts(o.getJsonNumber("failedAttempts").intValue());
        user.setLocked(o.getBoolean("isLocked"));        
        return user;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
                //.add("id", this.getId())
                .add("username", this.getUsername())
                .add("passord", this.getPassword())
                .add("failedAttempts", this.getFailedAttempts())
                .add("isLocked", this.isLocked())
                .build();
    }
    

    
}
