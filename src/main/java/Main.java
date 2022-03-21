import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import controller.*;
import database.ConnectionFactory;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

  static SessionFactory sessionFactoryObj;

  private static SessionFactory buildSessionFactory() {
    try {
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    } catch (HibernateException he) {
      System.out.println("Session Factory creation failure");
      throw he;
    }
  }

  public static EntityManagerFactory createEntityManagerFactory(){
    EntityManagerFactory emf;
    try {
      emf = Persistence.createEntityManagerFactory("JPAMagazines");
    } catch (Throwable ex) {
      System.err.println("Failed to create EntityManagerFactory object."+ ex);
      throw new ExceptionInInitializerError(ex);
    }
    return emf;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    Connection c = connectionFactory.connect();

    EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

    TipoController tipoController = new TipoController(c, entityManagerFactory);
    HabilidadController habilidadController = new HabilidadController(c, entityManagerFactory);
    PokemonController pokemonController = new PokemonController(c, entityManagerFactory);

    Menu menu = new Menu();
    int opcio;
    opcio = menu.mainMenu();

    switch (opcio) {

      case 1:
        System.out.println("1!!");
        try{
          List<Tipo> tipos = tipoController.readTipoFile("src/main/resources/pokemons.csv");
          for (Tipo r : tipos) {
            try {
              tipoController.addTipo(r);
            } catch (Exception e) {
            }
          }
          List<Habilidad> habilidads = habilidadController.readHabilidadFile("src/main/resources/pokemons.csv");
          for (Habilidad r : habilidads) {
            try {
              habilidadController.addHabilidad(r);
            } catch (Exception e) {
            }

          }
          List<Pokemon> pokemons = pokemonController.readPokemonFile("src/main/resources/pokemons.csv");
          for (Pokemon r : pokemons) {
            try {
              pokemonController.addPokemon(r);
            } catch (Exception e) {
            }

          }

        }catch (NumberFormatException | IOException e){
          e.printStackTrace();
        }
        break;

      case 2:
        pokemonController.showPokemonPorRol();
        break;
      case 3:
        pokemonController.showPokemonCon();
        break;
      case 4:
        pokemonController.showPokemonPor();
        break;
      case 5:
        pokemonController.modificarPokemon();
        break;
      case 6:
        tipoController.modificarTipo();
        break;
      case 7:
        pokemonController.borrarPokemon();
        break;
      case 8:
        pokemonController.borrarPokemonPorRol();
        break;
      case 9:
        System.out.println("----------------------");
        System.out.println("Crear Rol");
        System.out.println("----------------------");

        System.out.println("Rol:");
        String tipe = sc.nextLine().toUpperCase(Locale.ROOT);

        tipoController.addTipo(new Tipo(tipe));

        break;
      case 10:
        System.out.println("----------------------");
        System.out.println("Crear Campeon");
        System.out.println("----------------------");

        System.out.println("Nombre:");
        String nom = sc.nextLine().toUpperCase(Locale.ROOT);

        System.out.println("Elige un primer tipo:");
        String tipo1 = menu.TipoMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);
        System.out.println("Elige un segundo tipo:");
        String tipo2 = menu.TipoMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);

        System.out.println("Elige una primera habilidad:");
        String habilidad1 = menu.HabilidadMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);
        System.out.println("Elige una segunda habilidad:");
        String habilidad2 = menu.HabilidadMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);

        System.out.println("Historia:");
        String descripcion = sc.nextLine();

        pokemonController.addPokemon(new Pokemon(nom, new Tipo(tipo1), new Tipo(tipo2), new Habilidad(habilidad1), new Habilidad(habilidad2), descripcion));

        break;
      case 11:
        pokemonController.showPokemon();
        break;
      case 12:
        tipoController.showTipos();
        break;
      case 13:
        System.exit(1);
        break;

      default:
        System.out.println("Adeu!!");
        System.exit(1);
        break;

    }
  }
}


/*


    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void main(String[] args) {
        System.out.println(".......Hibernate Maven Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for(int i = 101; i <= 105; i++) {
                userObj = new User();
                userObj.setUserid(i);
                userObj.setUsername("Editor " + i);
                userObj.setCreatedBy("Administrator");
                userObj.setCreatedDate(new Date());

                sessionObj.save(userObj);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }


*/
