package crocobob.CIENderella.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReturnFormPractice {
    LocalDate date;
    String reason;
    String writer;
}