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
public class Form {
    Content content;
    Reason reason;
    Writer writer;
    LocalDate date;
}
