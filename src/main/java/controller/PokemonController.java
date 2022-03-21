package controller;

import model.Habilidad;
import model.Pokemon;
import model.Tipo;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

/**
 * Esta clase sirve para controlar la tabla pokemons situada en la base de datos
 */
public class PokemonController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    List<Pokemon> pokemons;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public PokemonController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        pokemons = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de los pokemons
     * @throws IOException
     */
    public List<Pokemon> readPokemonFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, ",");
            try {
                StringBuilder nombre = new StringBuilder(listToken.get(0));
                nombre = nombre.deleteCharAt(0);
                nombre = new StringBuilder(nombre.substring(0, nombre.length() - 1));
                String nombres = nombre.toString();

                StringBuilder tipo1 = new StringBuilder(listToken.get(2));
                tipo1 = tipo1.deleteCharAt(0);
                tipo1 = new StringBuilder(tipo1.substring(0, tipo1.length() - 1));
                String tips1 = tipo1.toString();

                StringBuilder tipo2 = new StringBuilder(listToken.get(3));
                tipo2 = tipo2.deleteCharAt(0);
                tipo2 = new StringBuilder(tipo2.substring(0, tipo2.length() - 1));
                String tips2 = tipo2.toString();

                StringBuilder habilidad1 = new StringBuilder(listToken.get(4));
                habilidad1 = habilidad1.deleteCharAt(0);
                habilidad1 = new StringBuilder(habilidad1.substring(0, habilidad1.length() - 1));
                String habs1 = habilidad1.toString();

                StringBuilder habilidad2 = new StringBuilder(listToken.get(5));
                habilidad2 = habilidad2.deleteCharAt(0);
                habilidad2 = new StringBuilder(habilidad2.substring(0, habilidad2.length() - 1));
                String habs2 = habilidad2.toString();

                StringBuilder descripcion = new StringBuilder(listToken.get(6));
                descripcion = descripcion.deleteCharAt(0);
                descripcion = new StringBuilder(descripcion.substring(0, descripcion.length() - 1));
                String descripcions = descripcion.toString();

                pokemons.add(new Pokemon(nombres, new Tipo(tips1), new Tipo(tips2), new Habilidad(habs1), new Habilidad(habs2), descripcions));

            } catch (Exception e) {
            }

        }

        return pokemons;
    }

    /**
     * Para añadir un pokemon a la base de datos
     * @param pokemon pokemon a añadir
     */
    public void addPokemon(Pokemon pokemon) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(pokemon);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pokemons
     */
    public void showPokemon(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery("from Pokemon", Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pokemons por su tipo
     */
    public void showPokemonPorRol(){
        String tipo = menu.TipoMenu(connection,entityManagerFactory);
        String sql = "from Pokemon where tipo1='"+tipo+"'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery(sql,Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pokemons con un texto especificado
     */
    public void showPokemonCon(){
        System.out.println("Escribe el texto a contener: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);

        String sql = "from Pokemon where nombre like '%" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery(sql, Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pokemons que empiezan por una letra concreta
     */
    public void showPokemonPor(){
        System.out.println("Escribe la letra de inicio: ");
        String letra = sc.nextLine();

        String sql = "from Pokemon where nombre like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery(sql, Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar las id y los nombres del pokemon por su nombre
     */
    public void showPokemonNom(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery("from Pokemon order by id", Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            System.out.println(pokemon.getPokemonId() + " " + pokemon.getNombre());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar el nombre de un pokemon
     */
    public void modificarPokemon() {
        int id = menu.NombreMenu(connection,entityManagerFactory);
        System.out.println("Escribe el nuevo nombre: ");
        String newNom = sc.nextLine();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Pokemon pokemon = (Pokemon) em.find(Pokemon.class, id);
        pokemon.setNombre(newNom);
        em.merge(pokemon);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar un pokemon
     */
    public void borrarPokemon() {
        int id = menu.NombreMenu(connection,entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Pokemon pokemon = (Pokemon) em.find(Pokemon.class, id);
        em.remove(pokemon);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar pokemons por su tipo
     */
    public void borrarPokemonPorRol() {
        String tipo = menu.TipoMenu(connection,entityManagerFactory);
        String sql = "from Pokemon where tipo1='" + tipo + "' OR tipo2='" + tipo + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery(sql,Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            em.remove(pokemon);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para dividir una frase en trozos depediendo del separador
     * @param string recibe una frase
     * @param delimiters recibe cual es el separador
     * @return devuelve un array de palabras separadas.
     */
    private static List<String> getTokenList(String string, String delimiters) {

        List<String> listTokens = new ArrayList<String>();

        try {

            StringTokenizer st = new StringTokenizer(string, delimiters);

            while( st.hasMoreTokens() ) {
                //add token to the list
                listTokens.add( st.nextToken() );
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return listTokens;
    }
}
