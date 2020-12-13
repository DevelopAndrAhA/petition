package kg.aloha.pet.model.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by 99670 on 30.10.2020.
 */
@Entity
public class Profession_Count {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pc_id;
    private long p_id;
    private long a_id;

    public long getPc_id() {
        return pc_id;
    }

    public void setPc_id(long pc_id) {
        this.pc_id = pc_id;
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
