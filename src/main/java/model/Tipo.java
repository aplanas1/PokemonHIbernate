package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "tipos")
public class Tipo implements Serializable {
    @Id
    @Column(name = "id_tipo")
    int tipoId;
    @Column(name = "tipo", length = 30)
    String tipo;

    public Tipo(int tipoId, String tipo) {
        this.tipoId = tipoId;
        this.tipo = tipo;
    }

    public Tipo() {

    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "tipoId=" + tipoId +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
