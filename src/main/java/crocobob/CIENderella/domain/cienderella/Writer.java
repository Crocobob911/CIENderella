package crocobob.CIENderella.domain.cienderella;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Writer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;
    Boolean valid;

    public Writer(String text, Boolean valid) {
        this.text = text;
        this.valid = valid;
    }
}
