package kg.aloha.pet.model;

import javax.persistence.*;

/**
 * Created by 99670 on 30.10.2020.
 */
@Entity
public class Prezident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long p_id;
    @Column(length = 250)
    private String photo;
    @Column(length = 150)
    private String fio;
    @Column(length = 15)
    private String birth_date;
    private int age;
    @Column(length = 10)
    private String male;
    @Column(length = 100)
    private String nation;
    @Column(length = 50)
    private String status;

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
