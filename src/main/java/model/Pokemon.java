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
    @GeneratedValue
    @Column(name = "id_pokemon")
    int pokemonId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo1")
    Tipo tipo1;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo2")
    Tipo tipo2;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "habilidad1")
    Habilidad habilidad1;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "habilidad2")
    Habilidad habilidad2;
    @Column(name = "descripcion", length = 1000)
    String descripcion;

    public Pokemon(int pokemonId, Tipo tipo1, Tipo tipo2, Habilidad habilidad1, Habilidad habilidad2, String descripcion) {
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

    public Tipo getTipo1() {
        return tipo1;
    }

    public void setTipo1(Tipo tipo1) {
        this.tipo1 = tipo1;
    }

    public Tipo getTipo2() {
        return tipo2;
    }

    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }

    public Habilidad getHabilidad1() {
        return habilidad1;
    }

    public void setHabilidad1(Habilidad habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public Habilidad getHabilidad2() {
        return habilidad2;
    }

    public void setHabilidad2(Habilidad habilidad2) {
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
