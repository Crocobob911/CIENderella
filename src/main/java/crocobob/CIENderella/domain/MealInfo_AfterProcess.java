package crocobob.CIENderella.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MealInfo_AfterProcess {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String dueTime;
    private String cafeteria;
    @Column(name="mealtype")
    private String mealType;
    private String menu;

    public void addMenu(String menu){
        this.menu += menu;
    }

    public MealInfo_AfterProcess(String date, String dueTime, String cafeteria, String mealType, String menu) {
        this.date = date;
        this.dueTime = dueTime;
        this.cafeteria = cafeteria;
        this.mealType = mealType;
        this.menu = menu;
    }
}
