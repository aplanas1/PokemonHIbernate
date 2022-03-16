package controller;

import antlr.StringUtils;
import view.Menu;
import model.Tipo;

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

public class TipoController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    Menu menu = new Menu();
    List<Tipo> tipos;

    public TipoController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        tipos = new ArrayList<>();
    }

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

    public void addTipo(Tipo tipo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tipo);
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
