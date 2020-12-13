package kg.aloha.pet.model.model;

import javax.persistence.*;

/**
 * Created by 99670 on 30.10.2020.
 */
@Entity
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long p_id;
    private long a_id;
    @Column(length = 100)
    private String profession_name;

    public long getA_id() {
        return a_id;
    }

    public void setA_id(long a_id) {
        this.a_id = a_id;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }
}
