package controller;

import model.Habilidad;
import model.Tipo;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Esta clase sirve para controlar la tabla habilidades situada en la base de datos
 */
public class HabilidadController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    Menu menu = new Menu();
    List<Habilidad> habilidades;

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public HabilidadController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        habilidades = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de las habilidades
     * @throws IOException
     */
    public List<Habilidad> readHabilidadFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, ",");
            try {
                StringBuilder habilidad1 = new StringBuilder(listToken.get(4));
                habilidad1 = habilidad1.deleteCharAt(0);
                habilidad1 = new StringBuilder(habilidad1.substring(0, habilidad1.length() - 1));
                String habs1 = habilidad1.toString();

                StringBuilder habilidad2 = new StringBuilder(listToken.get(5));
                habilidad2 = habilidad2.deleteCharAt(0);
                habilidad2 = new StringBuilder(habilidad2.substring(0, habilidad2.length() - 1));
                String habs2 = habilidad2.toString();
                if (!habs1.equals("")){
                    habilidades.add(new Habilidad(habs1));
                }
                if (!habs2.equals("")){
                    habilidades.add(new Habilidad(habs2));
                }
            } catch (Exception e) {
            }

        }

        return habilidades;
    }

    /**
     * Sirve para añadir una habilidad a la base de datos
     * @param habilidad habilidad a añadir
     */
    public void addHabilidad(Habilidad habilidad) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(habilidad);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar las habilidades
     */
    public void showHabilidades(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Habilidad> result = em.createQuery("from Habilidad ", Habilidad.class).getResultList();
        for (Habilidad habilidad : result) {
            System.out.println(habilidad.toString());
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
