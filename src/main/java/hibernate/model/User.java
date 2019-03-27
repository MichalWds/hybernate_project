package hibernate.model;

import javax.persistence.*;


// create przy restarcie bedzie nam wszystko czysci
//update bedzie updetowac!
// W apllication.properties


@Entity // This tells Hibernate to make a table out of this class   pozwala nam zrobic tabele poza java, Entity!
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //stworzyna specjalna tabela z ID w hybrernate, by sie implementowalo
    private Integer id;

    private String name;

    private String email;    //zmapowane 1 do 1 z kolumny bazy danych

    //jesli chcemy zrobic adnotacje by nazywalo sie inaczej to pisemy columny   @Column(name="imie")

    // @Transient  znaczy, ze ignorujemy cala kolumne, nie bedzie tworzona w bazie kolumna
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}