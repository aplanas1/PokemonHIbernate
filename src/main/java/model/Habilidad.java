package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "habilidades")
public class Habilidad implements Serializable {
    @Id
    @Column(name = "habilidad", length = 30)
    String habilidad;

    public Habilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public Habilidad() {

    }


    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "habilidad='" + habilidad + '\'' +
                '}';
    }
}
