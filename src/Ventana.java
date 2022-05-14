import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.awt.Font.PLAIN;

public class Ventana extends JFrame {
    JPanel panel;
    Mapa mapaI;

    public Ventana(Mapa mapaE) throws FileNotFoundException {

        mapaI = mapaE;
        initPanel();
        initPantalla();

    }

    void initPanel() {

        panel = new JPanel();
        add(panel); // Añado el panel al JFrame
        panel.setPreferredSize(new Dimension(800, 600)); // Dimensiones del panel

    }

    private void initPantalla() {

        setTitle("Ventana");
        setSize(800, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void amplitud() throws CloneNotSupportedException {
        Mapa padre = mapaI;
        List<Mapa> cola = new ArrayList<Mapa>();
        cola.add(padre);

        while (true) {
            padre = new Mapa(cola.get(0));
            if (padre.ganar()) {
                break;
            }
            Mapa hijo = new Mapa(padre);
            hijo.moverIzquierda();

            // auxiliares para encontrar la posicion del ultimo movimiento del padre y del
            // hijo
            int aux1 = hijo.getPosUltMov();
            int aux2 = padre.getPosUltMov();

            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                cola.add(hijo);
            }

            hijo = new Mapa(padre);
            hijo.moverDerecha();
            aux1 = hijo.getPosUltMov();
            aux2 = padre.getPosUltMov();
            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                cola.add(hijo);
            }

            System.out.println("Soy padre Fila: " + padre.getMoviJugador().get(aux2)[0] + " Columna= "
                    + padre.getMoviJugador().get(aux2)[1]);
            System.out.println("Soy hijo Fila: " + hijo.getMoviJugador().get(aux1)[0] + " Columna= "
                    + hijo.getMoviJugador().get(aux1)[1]);

            hijo = new Mapa(padre);
            hijo.moverArriba();
            aux1 = hijo.getPosUltMov();
            aux2 = padre.getPosUltMov();
            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                cola.add(hijo);
            }

            hijo = new Mapa(padre);
            hijo.moverAbajo();
            aux1 = hijo.getPosUltMov();
            aux2 = padre.getPosUltMov();
            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                cola.add(hijo);
            }

            
            cola.remove(0);
        }
        int contadorPasos = 0;
        System.out.println("Eureka");
        for (int[] posiciones : cola.get(0).getMoviJugador()) {
            contadorPasos+=1;
            System.out.println("Fila: " + posiciones[0] + " Columna: " + posiciones[1]);
        }
        System.out.println("Pasos: "+contadorPasos);
        System.out.println("Items: " + cola.get(0).getContadorItems()[0][0] + " Fila: " + cola.get(0).getContadorItems()[0][1]
                + " Columna: " + cola.get(0).getContadorItems()[0][2]);
        System.out.println("Items: " + cola.get(0).getContadorItems()[1][0] + " Fila: " + cola.get(0).getContadorItems()[1][1]
                + " Columna: " + cola.get(0).getContadorItems()[1][2]);

    }

    public void paint(Graphics g) {
        super.paint(g);

        // Para poder modificar más propiedades con Graphics 2d
        Graphics2D g2d = (Graphics2D) g;

        // Línea
        // g2d.setColor(Color.BLUE);
        // g2d.setStroke(new BasicStroke(5));
        // g2d.drawLine(30, 70, 770, 70);

        int[][] matrizMapa = mapaI.getMapa();
        int x = 30;
        int y = 100;
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                switch (matrizMapa[fila][columna]) {
                    case 0:
                        g2d.setColor(Color.white);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;
                    case 1:
                        g2d.setColor(new Color(116, 78, 59));
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;

                    case 2:
                        g2d.setColor(new Color(0, 170, 228));
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;

                    case 3:
                        g2d.setColor(Color.green);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;
                    case 4:
                        g2d.setColor(Color.gray);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;
                    case 5:
                        g2d.setColor(Color.yellow);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;
                    case 6:
                        g2d.setColor(Color.red);
                        g2d.fillRect(x, y, 60, 60);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, 60, 60);
                        x = x + 60;
                        break;
                }
            }
            x = 30;
            y = y + 60;
        }
    }

}
