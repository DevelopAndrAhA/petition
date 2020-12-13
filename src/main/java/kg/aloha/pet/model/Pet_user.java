package kg.aloha.pet.model;

import javax.persistence.*;

/**
 * Created by 99670 on 30.10.2020.
 */
@Entity
public class Pet_user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long u_id;
    @Column(unique = true,length = 50)
    private String nik;
    @Column(length = 50)
    private String imei;

    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
