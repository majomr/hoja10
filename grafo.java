/*
Maria Jose Morales 19145
Hoja de trabajo 10
grafos  
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class grafo {
    private HashMap<String,Integer> nodos;
    private Integer[][] matriz;
    private Integer[][] matrizFloyd;
    private Integer[][] Resultante;
    private String[][] caminos;
    private String[][] caminosFloyd;
    private HashMap<Integer,String> nodosReves;
    
    public grafo(HashMap<String,Integer> nodos, HashMap<Integer,String> nodosReves, int n){
        this.nodos = nodos;
        this.nodosReves = nodosReves;
        matriz = new Integer[n][n];
        caminos = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if(i!=j)
                    caminos[i][j] = nodosReves.get(j+1);
            }
            
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if(i==j)
                    matriz[i][j] = 0;
            }
            
        }
    }
    
    public void setInterrupcion(int f, int c){
        matriz[f][c] = null;
        caminos[f][c] = null;
    }
    
    public void setMatriz(int f, int c, int n){
        matriz[f][c] = n;
    }
    
    public void setCaminos(int f, int c){
        caminos[f][c] = nodosReves.get(c);
    }
    
    public void Floyd(){
        int tam = nodos.size();
        matrizFloyd = new Integer[tam][tam];
        caminosFloyd = new String[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++){
                matrizFloyd[i][j] = matriz[i][j];
                caminosFloyd[i][j] = caminos[i][j];
            }
        }
        
        for (int k = 0; k < nodos.size(); k++) {
            for (int j = 0; j < nodos.size(); j++){
                for (int i = 0; i < nodos.size(); i++){
                    if(matrizFloyd[i][j] != null && matrizFloyd[i][k] != null && matrizFloyd[k][j] != null){
                        if(matrizFloyd[i][j] > (matrizFloyd[i][k] + matrizFloyd[k][j])){
                                matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                                caminosFloyd[i][j] = nodosReves.get(k+1);
                        }
                    }
                    if(matrizFloyd[i][j] == null && !(matrizFloyd[i][k] == null || matrizFloyd[k][j] == null))
                    {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                        caminosFloyd[i][j] = nodosReves.get(k+1);
                    }
                }
            }
            
        }
    }
    
    public String impresionFloyd(){
        String mensaje = "";
        for (int i = 0; i < nodos.size(); i++) {
            mensaje += nodosReves.get(i+1) + "[";
            for (int j = 0; j < nodos.size(); j++){
                if(matrizFloyd[i][j] != null)
                    mensaje += matrizFloyd[i][j].toString() + " ";
                else
                    mensaje += "inf ";
            }
            mensaje += "]\n";
        }
        return mensaje;
    }
    
    public String impresionAdj(){
        String mensaje = "";
        for (int i = 0; i < nodos.size(); i++) {
            mensaje += nodosReves.get(i+1) + "[";
            for (int j = 0; j < nodos.size(); j++){
                if(matriz[i][j] != null)
                    mensaje += matriz[i][j].toString() + " ";
                else
                    mensaje += "inf ";
            }
            mensaje += "]\n";
        }
        return mensaje;
    }
    
    public String ruta(int f, int c){
        String peso = null, mensaje = "Recorrido inexistente";
        if(caminosFloyd[f][c] != null){
            peso = matrizFloyd[f][c].toString();
            mensaje = "El recorrido es: " + nodosReves.get(f+1);
            while(f!=c)
            {
                if(caminosFloyd[f][c] != null){
                    mensaje += "-" + caminosFloyd[f][c];
                    f = nodos.get(caminosFloyd[f][c])-1;
                }
            }
            mensaje += ", con un peso de: " + peso;
        }
        return mensaje;
    }
    
    public String centro(){
        String mensaje = "El centro del grafo es: ";
        ArrayList<Integer> excentridades = new ArrayList<Integer>();
        int suma = 0;
        for (int i = 0; i < nodos.size(); i++) {
            suma = 0;
            for (int j = 0; j < nodos.size(); j++){
                if(matrizFloyd[j][i] != null)
                    suma += matrizFloyd[j][i];
                else
                    suma += Integer.MAX_VALUE/2;
            }
            excentridades.add(suma);
        }
        ArrayList<Integer> temporal = excentridades;
        Collections.sort(temporal);
        mensaje += nodosReves.get(excentridades.indexOf(temporal.get(0))+1);
        return mensaje;
    }
    
    
}
