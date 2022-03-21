package view;

import controller.HabilidadController;
import controller.PokemonController;
import controller.TipoController;

import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Esta clase sirve para mostrar los menus
 */
public class Menu {
    private int option;
    private String opciones;

    /**
     *Este es un constructor y llama a su clase madre
     */
    public Menu() {
        super();
    }

    /**
     *Este metodo sirve para mostar el menu principal del programa
     * @return
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Rellenar Tablas");
            System.out.println("2. Mostrar los que sean ?");
            System.out.println("3. Mostrar los campeones que tengan un ?");
            System.out.println("4. Mostrar todos los campeon que empiezan por ?");
            System.out.println("5. Modificar el nombre de un campeon");
            System.out.println("6. Modificar el rols de los campeones que empiezan por ?");
            System.out.println("7. Eliminar un campeon");
            System.out.println("8. Eliminar campeones con el rol ?");
            System.out.println("9. Añadir un rol");
            System.out.println("10. Añadir un campeon");
            System.out.println("11. Mostrar campeones");
            System.out.println("12. Mostrar roles");
            System.out.println("13. Exit");
            System.out.println("Esculli opció: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
                && option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13);


        return option;
    }

    /**
     * Este metodo sirve para mostrar un menu de los tipos
     * @param c recibe la coneccion
     * @return devuelve el tipo elegido
     */
    public String TipoMenu(Connection c, EntityManagerFactory entityManagerFactory){
        TipoController rolController = new TipoController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            rolController.showTipos();
            System.out.println("Elige el rol: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }

    /**
     * Este metodo sirve para mostrar un menu de las habilidades
     * @param c recibe la coneccion
     * @return devuelve la habilidad elegida
     */
    public String HabilidadMenu(Connection c, EntityManagerFactory entityManagerFactory){
        HabilidadController habilidadController = new HabilidadController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            habilidadController.showHabilidades();
            System.out.println("Elige una habilidad: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }
    /**
     * Este metodo sirve para mostrar un menu sobre los pokemons, con sus id y nombres
     * @param c recibe la coneccion
     * @return devuelve el nombre del pokemon elegido
     */
    public int NombreMenu(Connection c, EntityManagerFactory entityManagerFactory){
        PokemonController campeonController = new PokemonController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Campeones: ");
        for(;;){
            campeonController.showPokemonNom();
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return option;
        }
    }
}