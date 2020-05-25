/*
Maria Jose Morales 19145
Hoja de trabajo 10
main 
*/

import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        controlador controlador = new controlador();
        Scanner sc = new Scanner(System.in);
        controlador.importText();
        String fila = "";
		String columna = "";
        int valor = 0;
        controlador.iniciar();
        String opcion = "";
        while(!opcion.equals("7")){
            System.out.print("Ingrese la opcion que desea: \n1. Mostrar ruta entre 2 ciudades y la distancia \n2. Mostrar centro del grafo \n3. Interrupcion entre ciudades\n4. Modificacion de distancia \n5. Mostrar matriz de adyacencia\n6. Mostrar matriz floyd\n7. Salir\n");
            opcion = sc.nextLine();
            if(opcion.equals("1")){
                System.out.print("Ingrese ciudad de inicio: ");
                fila = sc.nextLine();
                System.out.print("Ingrese ciudad de destino: ");
                columna = sc.nextLine();
                System.out.println(controlador.imprimirInfo(fila, columna));
            }
            if(opcion.equals("2")){
                System.out.println("El centro es: " + controlador.imprimirCentro());
            }
            if(opcion.equals("3")){
                System.out.print("Ingrese ciudad de inicio: ");
                fila = sc.nextLine();
                System.out.print("Ingrese ciudad de destino: ");
                columna = sc.nextLine();
                controlador.Interrupcion(fila, columna);
                controlador.calcular();
            }
            if(opcion.equals("4")){
                System.out.print("Ingrese ciudad de inicio: ");
                fila = sc.nextLine();
                System.out.print("Ingrese ciudad de destino: ");
                columna = sc.nextLine();
                System.out.print("Ingrese distancia entre ciudades: ");
                valor = sc.nextInt();
                controlador.modificarRuta(fila, columna, valor);
                controlador.calcular();
            }
            if(opcion.equals("5"))
                System.out.println(controlador.imprimirMatriz());
            if(opcion.equals("6"))
                System.out.println(controlador.imprimirCalculos());
        }
    }
    
}
