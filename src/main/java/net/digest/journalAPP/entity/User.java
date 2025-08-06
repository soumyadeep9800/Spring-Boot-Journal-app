package net.digest.journalAPP.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

//@Document(collection = "users")
//public class User {
//    @Id
//    private ObjectId id;
//    @Indexed(unique = true)
//    @NotBlank
//    private String username;
//    @NotBlank
//    private String password;
//    @DBRef
//    private List<JournalEntry> journalEntries = new ArrayList<>();
//    private List<String> role;
//
//    // No-args constructor
//    public User() {}
//    // Getters
//    public ObjectId getId() {
//        return id;
//    }
//    public String getUsername() {
//        return username;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public List<JournalEntry> getJournalEntries() {
//        return journalEntries;
//    }
//    public List<String> getRole() {
//        return role;
//    }
//    // Setters
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public void setJournalEntries(List<JournalEntry> journalEntries) {
//        this.journalEntries = journalEntries;
//    }
//    public void setRole(List<String> role) {
//        this.role = role;
//    }
//    // Optional: toString(), equals(), and hashCode() if you need exact behavior of @Data
//}

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @DBRef
    private  List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> role;
}