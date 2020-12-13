package kg.aloha.pet.model;

import javax.persistence.*;

/**
 * Created by 99670 on 02.12.2020.
 */
@Entity
public class Imushestvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long i_id;
    private long d_id;
    private long f_id;
    private long p_id;
    private long a_id;



    @Column(length = 10000)
    private String info;
    @Column(length = 255)
    private String photo;


    public long getI_id() {
        return i_id;
    }

    public void setI_id(long i_id) {
        this.i_id = i_id;
    }

    public long getD_id() {
        return d_id;
    }

    public void setD_id(long d_id) {
        this.d_id = d_id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public long getA_id() {
        return a_id;
    }

    public void setA_id(long a_id) {
        this.a_id = a_id;
    }
}
