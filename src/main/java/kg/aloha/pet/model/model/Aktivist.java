package kg.aloha.pet.model.model;

import javax.persistence.*;

/**
 * Created by 99670 on 30.10.2020.
 */

@Entity
public class Aktivist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long a_id;

    private String photo;
    @Column(length = 100)
    private String fio;

    public long getA_id() {
        return a_id;
    }

    public void setA_id(long a_id) {
        this.a_id = a_id;
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
}
