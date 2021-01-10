package kg.aloha.pet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by 99670 on 30.10.2020.
 */
@Entity
public class Golos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long g_id;
    private long u_id;
    private long p_id;
    private long a_id;
    private long d_id;

    public long getG_id() {
        return g_id;
    }

    public void setG_id(long g_id) {
        this.g_id = g_id;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }

    public long getD_id() {
        return d_id;
    }

    public void setD_id(long d_id) {
        this.d_id = d_id;
    }

    public long getA_id() {
        return a_id;
    }

    public void setA_id(long a_id) {
        this.a_id = a_id;
    }

    @Override
    public String toString() {
        return "Golos{" +
                "g_id=" + g_id +
                ", u_id=" + u_id +
                ", p_id=" + p_id +
                ", a_id=" + a_id +
                ", d_id=" + d_id +
                '}';
    }
}
