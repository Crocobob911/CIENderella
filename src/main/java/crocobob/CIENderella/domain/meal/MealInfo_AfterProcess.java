package crocobob.CIENderella.domain.meal;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MealInfo_AfterProcess {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String cafeteria;
    private String mealType;
    private String menu;
}
