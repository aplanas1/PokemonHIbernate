package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "tipos")
public class Tipo implements Serializable {
    @Id
    @Column(name = "tipo", length = 30)
    String tipo;

    public Tipo(String tipo) {
        this.tipo = tipo;
    }

    public Tipo() {}


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
