import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Font.PLAIN;

public class App {
    public static void main(String[] args) throws Exception {
        //Mapa mapa = new Mapa(new int[2][3], false ,new ArrayList<int[]>());
        MapaCU mapa = new MapaCU(new int[2][3], false ,new ArrayList<int[]>(), new ArrayList<String>(),0, new int[2][4]);
        Ventana ventana = new Ventana(mapa,0);
        ventana.costoUniforme();
        
    }
}
