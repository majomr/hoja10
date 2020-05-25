/*
Maria Jose Morales 19145
Hoja de trabajo 10
controlador 
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class controlador {
    private grafo g;
    private ArrayList<String> lineas;
    private HashMap<String,Integer> nodos;
    private HashMap<Integer,String> nodos2;
    public controlador(){
        lineas = new ArrayList<String>();
        nodos = new HashMap<String,Integer>();
        nodos2 = new HashMap<Integer,String>();
    }
    
    public void iniciar(){
        String[] lista;
        int cont = 0;
        for (String linea : lineas) {
            lista = linea.split(" ");
            if(nodos.get(lista[0]) == null){
                cont++;
                nodos.put(lista[0], cont);
                nodos2.put(cont, lista[0]);
            }
            if(nodos.get(lista[1]) == null){
                cont++;
                nodos.put(lista[1], cont);
                nodos2.put(cont, lista[1]);
            }
        }
        g = new grafo(nodos, nodos2, cont);
        for (String linea : lineas){
            lista = linea.split(" ");
            g.setMatriz(nodos.get(lista[0])-1, nodos.get(lista[1])-1, Integer.valueOf(lista[2]));
        }
        g.Floyd();
    }
    
    public String imprimirCalculos(){
        return "Matriz floyd:" + "\n" + g.impresionFloyd();
    }
    
    public String imprimirMatriz(){
        return "Matriz original:" + "\n" +g.impresionAdj();
    }
    
    public String imprimirInfo(String fila, String columna){
        return g.ruta(nodos.get(fila)-1, nodos.get(columna)-1);
    }
    
    public String imprimirCentro(){
        return g.centro();
    }
    
    public void modificarRuta(String fila, String columna, int n){
        g.setMatriz(nodos.get(fila)-1, nodos.get(columna)-1, n);
        g.setCaminos(nodos.get(fila)-1, nodos.get(columna)-1);
    }
    
    public void Interrupcion(String fila, String columna){
        g.setInterrupcion(nodos.get(fila)-1, nodos.get(columna)-1);
    }
    
    public void calcular(){
        g.Floyd();
    }
    
    
    public void importText() {
        File inputStream = null;
        try {
            inputStream = new File(System.getProperty("user.dir")+"\\src\\guategrafo.txt");
            Scanner myReader = new Scanner(inputStream);
            while (myReader.hasNextLine()) {
              lineas.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException ex) {

        }
    }
}
