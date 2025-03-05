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
    private String cafeteria;
    @Column(name="mealtype")
    private String mealType;
    private String menu;

    public void addMenu(String menu){
        this.menu += menu;
    }

    public MealInfo_AfterProcess(String date, String cafeteria, String mealType, String menuList) {
        this.date = date;
        this.cafeteria = cafeteria;
        this.mealType = mealType;
        this.menu = menuList.toString();
    }
}
