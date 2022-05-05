import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mapa {
    private int casilla_libre = 0;
    private int muro = 1;
    private int inicio = 2;
    private int nave1 = 3;
    private int nave2 = 4;
    private int item = 5;
    private int aceite = 6;

    private  int [][] mapa = new int [10][10];

    public int getCasilla_libre(){
        return casilla_libre;
    }

    public int getMuro(){
        return muro;
    }
    public int getInicio(){
        return inicio;
    }
    public int getNave1(){
        return nave1;
    }
    public int getNave2(){
        return nave2;
    }
    public int getItem(){
        return item;
    }

    public int getAceite(){
        return aceite;
    }

    public int[][] getMapa(){
        return mapa;
    }

    public void llenarMapa(String path) throws FileNotFoundException{
        File doc = new File(path);
        Scanner obj = new Scanner(doc);
        int fila = 0;
        while (obj.hasNextLine()){
            String aux = obj.nextLine();
            String[] casillas = aux.split(" ");
            int columna = 0;
            for(String casilla : casillas){
                mapa[fila][columna] = Integer.parseInt(casilla);
                columna++;
            }
            fila++;
        }
        String aux = "";
        for(int i = 0; i <10; i++){
            aux = "";
            for(int x = 0; x <10; x++){
                aux = aux + " " + mapa[i][x];
            }
            System.out.println(aux);
        }

    }


}
