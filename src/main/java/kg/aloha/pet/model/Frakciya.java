package kg.aloha.pet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by 99670 on 03.11.2020.
 */
@Entity
public class Frakciya {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long f_id;
    private String name;

    public long getF_id() {
        return f_id;
    }

    public void setF_id(long f_id) {
        this.f_id = f_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
