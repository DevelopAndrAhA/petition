package kg.aloha.pet.model;

import javax.persistence.*;

/**
 * Created by 99670 on 03.11.2020.
 */
@Entity
public class Deputat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long d_id;
    @Column(length = 250)
    private String photo;
    @Column(length = 150)
    private String fio;
    @Column(length = 50000)
    private String info;
    private long f_id;

    public long getD_id() {
        return d_id;
    }

    public void setD_id(long d_id) {
        this.d_id = d_id;
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

    public long getF_id() {
        return f_id;
    }

    public void setF_id(long f_id) {
        this.f_id = f_id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
