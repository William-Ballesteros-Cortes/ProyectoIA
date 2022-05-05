import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import static java.awt.Font.PLAIN;

public class Ventana extends JFrame {
    JPanel panel;
    Mapa mapa = new Mapa();

    public Ventana() throws FileNotFoundException{
        mapa.llenarMapa("ProyectoIA\\src\\Prueba1.txt");
        initPanel();
        initPantalla();

    }

    void initPanel(){

        panel = new JPanel();
        add(panel); //Añado el panel al JFrame
        panel.setPreferredSize(new Dimension(800,600)); //Dimensiones del panel

    }

    private void initPantalla(){

        setTitle("Ventana");
        setSize(800,900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    
    public void paint(Graphics g){
        super.paint(g);

        //Para poder modificar más propiedades con Graphics 2d
        Graphics2D g2d = (Graphics2D) g;

        //Línea
        // g2d.setColor(Color.BLUE);
        // g2d.setStroke(new BasicStroke(5));
        // g2d.drawLine(30, 70, 770, 70);


        int[][] matrizMapa = mapa.getMapa();
        int x=30;
        int y = 100;
        for(int fila = 0; fila < 10; fila++){
            for(int columna = 0; columna < 10; columna++){
                switch(matrizMapa[fila][columna]){
                    case 0:
                        g2d.setColor(Color.white);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;
                    case 1:
                        g2d.setColor(new Color(116, 78, 59));
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;
                    
                    case 2:
                        g2d.setColor(new Color(0, 170, 228));
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;

                    case 3:
                        g2d.setColor(Color.green);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;
                    case 4:
                        g2d.setColor(Color.gray);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;
                    case 5:
                        g2d.setColor(Color.yellow);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;
                    case 6:
                        g2d.setColor(Color.red);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x+60;
                        break;
                }
            }
            x = 30;
            y = y+60;
        }
    }

}
