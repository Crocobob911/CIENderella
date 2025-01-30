package crocobob.CIENderella.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
@ToString
public class Form {
    Boolean status;
    String title;
    String text;
    LocalDate date;
    long password;
    Reason reason;
    Writer writer;
    LocalTime time;
}