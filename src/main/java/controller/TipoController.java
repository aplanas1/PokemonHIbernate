package controller;

import antlr.StringUtils;
import model.Pokemon;
import view.Menu;
import model.Tipo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

/**
 * Esta clase sirve para controlar la tabla habilidades situada en la base de datos
 */
public class TipoController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    Menu menu = new Menu();
    List<Tipo> tipos;

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public TipoController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        tipos = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de los tipos
     * @throws IOException
     */
    public List<Tipo> readTipoFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, ",");
            try {
                StringBuilder tipo1 = new StringBuilder(listToken.get(2));
                tipo1 = tipo1.deleteCharAt(0);
                tipo1 = new StringBuilder(tipo1.substring(0, tipo1.length() - 1));
                String tips1 = tipo1.toString();

                StringBuilder tipo2 = new StringBuilder(listToken.get(3));
                tipo2 = tipo2.deleteCharAt(0);
                tipo2 = new StringBuilder(tipo2.substring(0, tipo2.length() - 1));
                String tips2 = tipo2.toString();
                if (!tips1.equals("")){
                    tipos.add(new Tipo(tips1));
                }
                if (!tips2.equals("")){
                    tipos.add(new Tipo(tips2));
                }
            } catch (Exception e) {
            }

        }

        return tipos;
    }

    /**
     * Sirve para añadir un tipo a la base de datos
     * @param tipo habilidad a añadir
     */
    public void addTipo(Tipo tipo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tipo);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar los tipos
     */
    public void showTipos(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Tipo> result = em.createQuery("from Tipo ", Tipo.class).getResultList();
        for (Tipo tipo : result) {
            System.out.println(tipo.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar un tipo
     */
    public void modificarTipo(){
        String tipo = menu.TipoMenu(connection,entityManagerFactory);
        System.out.println("Escribe la primera letra del campeon que quieras modificar ?");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "from Pokemon where nombre like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Pokemon> result = em.createQuery(sql, Pokemon.class).getResultList();
        for (Pokemon pokemon : result) {
            pokemon.setTipo1(new Tipo(tipo));
            em.merge(pokemon);
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
