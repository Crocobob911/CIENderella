package crocobob.CIENderella.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String notice;
    LocalDate date;

    public Notice(String notice, LocalDate date) {
        this.notice = notice;
        this.date = date;
    }
}
