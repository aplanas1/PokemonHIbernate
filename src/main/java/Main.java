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

/**
 * Esta es la clase principal dode se inicializa el programa
 */
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

  /**
   * Este metodo sirve para crear el Manager de Entity
   * @return
   */
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
        pokemonController.showPokemon();
        break;
      case 3:
        tipoController.showTipos();
        break;
      case 4:
        pokemonController.showPokemonPorRol();
        break;
      case 5:
        pokemonController.showPokemonCon();
        break;
      case 6:
        pokemonController.showPokemonPor();
        break;
      case 7:
        pokemonController.modificarPokemon();
        break;
      case 8:
        tipoController.modificarTipo();
        break;
      case 9:
        pokemonController.borrarPokemon();
        break;
      case 10:
        pokemonController.borrarPokemonPorRol();
        break;
      case 11:
        System.out.println("----------------------");
        System.out.println("Crear Tipo");
        System.out.println("----------------------");

        System.out.println("Tipo:");
        String tipe = sc.nextLine().toUpperCase(Locale.ROOT);

        tipoController.addTipo(new Tipo(tipe));

        break;
      case 12:
        System.out.println("----------------------");
        System.out.println("Crear Pokemon");
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

        System.out.println("Descripcion:");
        String descripcion = sc.nextLine();

        pokemonController.addPokemon(new Pokemon(nom, new Tipo(tipo1), new Tipo(tipo2), new Habilidad(habilidad1), new Habilidad(habilidad2), descripcion));

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
