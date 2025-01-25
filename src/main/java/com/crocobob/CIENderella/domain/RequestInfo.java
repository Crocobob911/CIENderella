package com.crocobob.CIENderella.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestInfo {
    long id;

    Writer writer;
    String title;
    String content;
    Reason reason;

    LocalDateTime dateTime; // 굳이?
}
