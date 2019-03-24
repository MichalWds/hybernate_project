package hibernate.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;

    }
    public void setId(Integer id) {
        this.id = id;
    }
    @NotNull(message= "nie moze byc puste")    //imie nie moze byc nullem, message jesli chcemy dac swoj komunikat
    @Size(min=2, max=30, message = "Minimum 3 znaki")

    private String name;

    @NotNull
    @Min(18)
    private Integer age;
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }

}

