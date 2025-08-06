package net.digest.journalAPP.entity;
import java.time.LocalDateTime;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "journal_entries")
//public class JournalEntry {
//    @Id
//    private ObjectId id;
//    private String title;
//    private String content;
//    private LocalDateTime date;
//
//    public JournalEntry() {
//        // No-args constructor
//    }
//
//    // Getters
//    public ObjectId getId() {
//        return id;
//    }
//    public String getTitle() {
//        return title;
//    }
//    public String getContent() {
//        return content;
//    }
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    // Setters
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    public void setContent(String content) {
//        this.content = content;
//    }
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//}

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
