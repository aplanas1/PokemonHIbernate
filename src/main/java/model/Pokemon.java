package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "pokemons")
public class Pokemon implements Serializable {
    @Id
    @Column(name = "id_pokemon")
    int pokemonId;
    @Column(name = "tipo1", length = 30)
    String tipo1;
    @Column(name = "tipo2", length = 30)
    String tipo2;
    @Column(name = "habilidad1", length = 30)
    String habilidad1;
    @Column(name = "habilidad2", length = 30)
    String habilidad2;
    @Column(name = "descripcion", length = 1000)
    String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo1", referencedColumnName = "id_tipo")
    @JoinColumn(name = "tipo2", referencedColumnName = "id_tipo")
    private List<Tipo> tipos = new ArrayList<Tipo>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "habilidad1", referencedColumnName = "id_habilidad")
    @JoinColumn(name = "habilidad2", referencedColumnName = "id_habilidad")
    private List<Habilidad> habilidades = new ArrayList<Habilidad>();

    public Pokemon(int pokemonId, String tipo1, String tipo2, String habilidad1, String habilidad2, String descripcion) {
        this.pokemonId = pokemonId;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.habilidad1 = habilidad1;
        this.habilidad2 = habilidad2;
        this.descripcion = descripcion;
    }

    public Pokemon() {

    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public String getHabilidad1() {
        return habilidad1;
    }

    public void setHabilidad1(String habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public String getHabilidad2() {
        return habilidad2;
    }

    public void setHabilidad2(String habilidad2) {
        this.habilidad2 = habilidad2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", tipo1='" + tipo1 + '\'' +
                ", tipo2='" + tipo2 + '\'' +
                ", habilidad1='" + habilidad1 + '\'' +
                ", habilidad2='" + habilidad2 + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
