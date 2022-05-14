import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Font.PLAIN;

public class App {
    public static void main(String[] args) throws Exception {
        Mapa mapa = new Mapa(new int[2][3], false ,new ArrayList<int[]>(),  new int[10][10]);
        mapa.llenarMapa("src/Prueba1.txt");
        Ventana ventana = new Ventana(mapa);
        ventana.amplitud();
        
    }
}
