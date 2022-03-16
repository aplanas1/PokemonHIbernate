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

public class HabilidadController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    Menu menu = new Menu();
    List<Habilidad> habilidades;

    public HabilidadController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        habilidades = new ArrayList<>();
    }

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

    public void addHabilidad(Habilidad habilidad) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(habilidad);
        em.getTransaction().commit();
        em.close();
    }

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
