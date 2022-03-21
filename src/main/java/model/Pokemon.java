package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase sirve para crear el objeto de pokemon con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "pokemons")
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon")
    int pokemonId;
    @Column(name = "nombre")
    String nombre;
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

    /**
     * Es el construcor principal de la clase pokemon, en la que se pasan los valores que quieras
     * @param nombre nombre del pokemon
     * @param tipo1 priimer tipo del pokemon
     * @param tipo2 segundo tipo del pokemon
     * @param habilidad1 primera habilidad del pokemon
     * @param habilidad2 segunda habilidad del pokemon
     * @param descripcion descripcion del pokemon
     */
    public Pokemon( String nombre, Tipo tipo1, Tipo tipo2, Habilidad habilidad1, Habilidad habilidad2, String descripcion) {
        super();
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.habilidad1 = habilidad1;
        this.habilidad2 = habilidad2;
        this.descripcion = descripcion;
    }

    /**
     * Constructor vacio del pokemon
     */
    public Pokemon() {
        super();
    }

    /**
     * Permite obtener el id del pokemon
     * @return
     */
    public int getPokemonId() {
        return pokemonId;
    }

    /**
     * Permite cambiar el id del pokemon
     * @param pokemonId
     */
    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    /**
     * Permite obtener el nombre del pokemon
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite cambiar el nombre del pokemon
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener el primer tipo del pokemon
     * @return
     */
    public Tipo getTipo1() {
        return tipo1;
    }

    /**
     * Pemite cambiar el primer tipo del pokemon
     * @param tipo1
     */
    public void setTipo1(Tipo tipo1) {
        this.tipo1 = tipo1;
    }

    /**
     * Permite obtener el segundo tipo del pokemon
     * @return
     */
    public Tipo getTipo2() {
        return tipo2;
    }

    /**
     * Permite cambiar el segundo tipo del pokemon
     * @param tipo2
     */
    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }

    /**
     * Permite obtener la primera habilidad del pokemon
     * @return
     */
    public Habilidad getHabilidad1() {
        return habilidad1;
    }

    /**
     * Permite cambiar la primera habilidad del pokemon
     * @param habilidad1
     */
    public void setHabilidad1(Habilidad habilidad1) {
        this.habilidad1 = habilidad1;
    }

    /**
     * Permite obtener la segunda habilidad del pokemon
     * @return
     */
    public Habilidad getHabilidad2() {
        return habilidad2;
    }

    /**
     * Permite cambiar la segunda habilidad del pokemon
     * @param habilidad2
     */
    public void setHabilidad2(Habilidad habilidad2) {
        this.habilidad2 = habilidad2;
    }

    /**
     * Permite obtener la descripcion del pokemon
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Permite cambiar la descripcion del pokemon
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * To string del pokemon
     * @return
     */
    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", nombre=" + nombre +
                ", tipo1=" + tipo1 +
                ", tipo2=" + tipo2 +
                ", habilidad1=" + habilidad1 +
                ", habilidad2=" + habilidad2 +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
