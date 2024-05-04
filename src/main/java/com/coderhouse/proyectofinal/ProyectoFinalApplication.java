package com.coderhouse.proyectofinal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class ProyectoFinalApplication  {  //implements CommandLineRunner

    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalApplication.class, args);
    }


 /*   @Override
    public void run(String...args) throws Exception{
        mostrarMenu();
    }

    public void mostrarMenu(){
        try{
            Scanner sc = new Scanner(System.in);

            int opcion = -1; //Inicializamos la variable con una opcion invalida

            do {
                try {
                    System.out.println("Menu: \n" +
                            "1. Metodos Client \n" +
                            "2. Metodos Admin \n" +
                            "3. Metodos Comic \n" +
                            "4. Metodos FiguraDeAccion \n" +
                            "5. Metodos Carrito \n" +
                            "6. Metodos Compra \n" +
                            "7. Metodos Venta \n" +
                            "8. Metodos Debito \n" +
                            "9. Metodos Factura \n" +
                            "0. Salir \n");
                    System.out.println("Ingrese una opcion");

                    if (sc.hasNextInt()){
                        opcion = sc.nextInt();
                        sc.nextLine();
                    }else {
                        System.out.println("Entrada invalida." +
                                "Debe ingresar un numero del Menu");
                        sc.nextLine();
                        continue;
                    }
                }catch (InputMismatchException e){
                    System.err.println("Error: Ingrese un numero valido");
                    sc.nextLine();
                    opcion = -1;
                }
            }while (opcion != 0);

            sc.close();

        }catch (Exception e){
            e.getMessage();
        }
    }*/
}
