package net.digest.journalAPP.entity;

import java.time.LocalDateTime;

import lombok.Data;
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
//    public LocalDateTime getDate() {
//        return date;
//    }
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public String getContent() {
//        return content;
//    }
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//}
@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
