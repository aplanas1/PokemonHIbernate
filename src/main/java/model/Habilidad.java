package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para crear el objeto de la habilidad
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "habilidades")
public class Habilidad implements Serializable {
    @Id
    @Column(name = "habilidad", length = 30)
    String habilidad;

    /**
     * Constructor de la habilidad
     * @param habilidad
     */
    public Habilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    /**
     * Constructor vacio de la habilidad
     */
    public Habilidad() {}

    /**
     * Pemite obtener la habilidad
     * @return
     */
    public String getHabilidad() {
        return habilidad;
    }

    /**
     * Permite cambiar la habilidad
     * @param habilidad
     */
    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    /**
     * To string del objeto de la habilidad
     * @return
     */
    @Override
    public String toString() {
        return "Habilidad{" +
                "habilidad='" + habilidad + '\'' +
                '}';
    }
}
