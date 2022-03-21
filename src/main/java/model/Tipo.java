package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para crear el objeto del tipo
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "tipos")
public class Tipo implements Serializable {
    @Id
    @Column(name = "tipo", length = 30)
    String tipo;

    /**
     * Constructor principal del tipo
     * @param tipo
     */
    public Tipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Constructor vacio del tipo
     */
    public Tipo() {}

    /**
     * Permite obtener el tipo
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Permite cambiar el tipo
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * To string del objeto tipo
     * @return
     */
    @Override
    public String toString() {
        return "Tipo{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
