package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "habilidades")
public class Habilidad implements Serializable {
    @Id
    @Column(name = "id_habilidad")
    int habilidadId;
    @Column(name = "habilidad", length = 30)
    String habilidad;

    public Habilidad(int habilidadId, String habilidad) {
        this.habilidadId = habilidadId;
        this.habilidad = habilidad;
    }

    public Habilidad() {

    }

    public int getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(int habilidadId) {
        this.habilidadId = habilidadId;
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
                "habilidadId=" + habilidadId +
                ", habilidad='" + habilidad + '\'' +
                '}';
    }
}
