package crocobob.CIENderella.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Reason {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String text;
    Boolean valid;

    public Reason(String text, Boolean valid) {
        this.text = text;
        this.valid = valid;
    }
}