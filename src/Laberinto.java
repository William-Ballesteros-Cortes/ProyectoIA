import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Laberinto {
    private int[] posInicio;
    private int[][] laberinto = new int[10][10];


    public int[][] getLaberinto (){
        return laberinto;
    }

    public int[] getPosInicio(){
        return posInicio;
    }

    public void llenarMapa(String path) throws FileNotFoundException {
        File doc = new File(path);
        Scanner obj = new Scanner(doc);
        int fila = 0;
        while (obj.hasNextLine()) {
            String aux = obj.nextLine();
            String[] casillas = aux.split(" ");
            int columna = 0;
            for (String casilla : casillas) {
                if (Integer.parseInt(casilla) == 2) {
                    int[] aux2 = new int[2];
                    aux2[0] = fila;
                    aux2[1] = columna;
                    posInicio = aux2;

                }
                laberinto[fila][columna] = Integer.parseInt(casilla);
                columna++;
            }
            fila++;
        }
    }
}
