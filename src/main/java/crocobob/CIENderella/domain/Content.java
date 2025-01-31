package crocobob.CIENderella.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Content {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String password;
    Boolean status;
    String title;
    String text;

    public Content(String password, Boolean status, String title, String text) {
        this.password = password;
        this.status = status;
        this.title = title;
        this.text = text;
    }
}
