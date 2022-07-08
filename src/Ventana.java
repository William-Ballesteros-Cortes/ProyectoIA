import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.awt.Font.PLAIN;

public class Ventana extends JFrame {
    JPanel panel;
    Mapa mapaI;
    MapaCU mapaCUI;
    Laberinto laberinto = new Laberinto();
    //Archivo auxiliar para cargar el laberinto
    File testFile = new File("");

    public Ventana(Object mapaE, int id) throws FileNotFoundException {
        //Este codigo trae el camino absoluto hasta la carpet src y luego concatena la posicion del archivo
        String testPath = testFile.getAbsolutePath();
        laberinto.llenarMapa(testPath.concat("\\Prueba1.txt"));

        mapaI = (Mapa) mapaE;
        mapaI.agregarMovimiento(laberinto.getPosInicio()[0], laberinto.getPosInicio()[1]);
        switch (id) {
            case 0:
                mapaCUI = (MapaCU) mapaE;
                break;
        }

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

    public List<MapaCU> procesarMovimientoCU(MapaCU hijo, MapaCU padre, List<MapaCU> cola) {
        // auxiliares para encontrar la posicion del ultimo movimiento del padre y del
        // hijo
        List<MapaCU> auxCola = new ArrayList<MapaCU>();
        auxCola.addAll(cola);
        int aux1 = hijo.getPosUltMov();
        int aux2 = padre.getPosUltMov();
        int aux3 = hijo.getAcumulado();
        int[][] auxLaberinto = laberinto.getLaberinto();
        if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
            int fila = hijo.getMoviJugador().get(aux1)[0];
            int columna = hijo.getMoviJugador().get(aux1)[1];
            if (auxLaberinto[fila][columna] != 1) {
                if (auxLaberinto[fila][columna] == 0 || auxLaberinto[fila][columna] == 5) {
                    if (hijo.getNaves()[0][1] > 0) {
                        int[][] aux = hijo.getNaves();
                        aux[0][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[1][1] > 0) {
                        int[][] aux = hijo.getNaves();
                        aux[1][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    hijo.setAcumulado(aux3 + 1);
                }
                if (auxLaberinto[fila][columna] == 3 && hijo.getNaves()[0][0] == 0) {
                    if (hijo.getNaves()[0][1] > 0) {
                        int[][] aux = hijo.getNaves();
                        aux[0][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[1][1] > 0) {
                        int[][] aux = hijo.getNaves();
                        aux[1][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[1][0] == 0 || hijo.getNaves()[1][1] == 0) {
                        
                        hijo.setAcumulado(aux3 + 1);
                        hijo.setDevolverse(true);
                        int[][] aux = hijo.getNaves();
                        aux[0][0] = 1;
                        aux[0][1] = 10;
                        aux[0][2] = fila;
                        aux[0][3] = columna;
                        hijo.setNaves(aux);
                    }
                }

                if (auxLaberinto[fila][columna] == 4 && hijo.getNaves()[1][0] == 0) {
                    if (hijo.getNaves()[0][1] > 0) {
                        int[][] aux = hijo.getNaves();
                        aux[0][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[1][1] > 0) {
                        int[][] aux = hijo.getNaves();
                        aux[1][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[0][0] == 0 || hijo.getNaves()[0][1] == 0) {
                        hijo.setAcumulado(aux3 + 1);
                        hijo.setDevolverse(true);
                        int[][] aux = hijo.getNaves();
                        aux[1][0] = 2;
                        aux[1][1] = 20;
                        aux[1][2] = fila;
                        aux[1][3] = columna;
                        hijo.setNaves(aux);
                    }
                }

                if (auxLaberinto[fila][columna] == 6) {
                    if (hijo.getNaves()[0][1] > 0) {
                        hijo.setAcumulado(aux3 + 1);
                        int[][] aux = hijo.getNaves();
                        aux[0][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[1][1] > 0) {
                        hijo.setAcumulado(aux3 + 1);
                        int[][] aux = hijo.getNaves();
                        aux[1][1] -= 1;
                        hijo.setNaves(aux);
                    }
                    if (hijo.getNaves()[1][1] == 0 && hijo.getNaves()[0][1] == 0) {
                        hijo.setAcumulado(aux3 + 4);
                    }
                }

                if (auxLaberinto[fila][columna] == 5) {

                    if (hijo.getContadorItems()[0][0] == 0) {
                        hijo.setDevolverse(true);
                        int[][] aux = hijo.getContadorItems();
                        aux[0][0] = 1;
                        aux[0][1] = fila;
                        aux[0][2] = columna;
                        hijo.setContadorItems(aux);
                    } else {
                        if (hijo.getContadorItems()[1][0] == 0 && (hijo.getContadorItems()[0][1] != fila + 1) &&
                                (hijo.getContadorItems()[0][2] != columna)) {
                            hijo.setDevolverse(true);
                            int[][] aux = hijo.getContadorItems();
                            aux[1][0] = 2;
                            aux[1][1] = fila;
                            aux[1][2] = columna;

                            hijo.setContadorItems(aux);
                        }
                    }

                }
                auxCola.add(hijo);
            }
        }
        return auxCola;
    }

    public void amplitud() throws CloneNotSupportedException {
        Mapa padre = (Mapa) mapaI;
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
            int[][] auxLaberinto = laberinto.getLaberinto();

            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                int fila = hijo.getMoviJugador().get(aux1)[0];
                int columna = hijo.getMoviJugador().get(aux1)[1];
                if (auxLaberinto[fila][columna] != 1) {

                    if (auxLaberinto[fila][columna] == 5) {

                        if (hijo.getContadorItems()[0][0] == 0) {
                            hijo.setDevolverse(true);
                            int[][] aux = hijo.getContadorItems();
                            aux[0][0] = 1;
                            aux[0][1] = fila;
                            aux[0][2] = columna;

                            hijo.setContadorItems(aux);
                        } else {
                            if (hijo.getContadorItems()[1][0] == 0 && (hijo.getContadorItems()[0][1] != fila + 1) &&
                                    (hijo.getContadorItems()[0][2] != columna)) {
                                hijo.setDevolverse(true);
                                int[][] aux = hijo.getContadorItems();
                                aux[1][0] = 2;
                                aux[1][1] = fila;
                                aux[1][2] = columna;

                                hijo.setContadorItems(aux);
                            }
                        }

                    }
                    cola.add(hijo);
                }
            }

            hijo = new Mapa(padre);
            hijo.moverDerecha();
            aux1 = hijo.getPosUltMov();
            aux2 = padre.getPosUltMov();
            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                int fila = hijo.getMoviJugador().get(aux1)[0];
                int columna = hijo.getMoviJugador().get(aux1)[1];
                if (auxLaberinto[fila][columna] != 1) {

                    if (auxLaberinto[fila][columna] == 5) {

                        if (hijo.getContadorItems()[0][0] == 0) {
                            hijo.setDevolverse(true);
                            int[][] aux = hijo.getContadorItems();
                            aux[0][0] = 1;
                            aux[0][1] = fila;
                            aux[0][2] = columna;

                            hijo.setContadorItems(aux);
                        } else {
                            if (hijo.getContadorItems()[1][0] == 0 && (hijo.getContadorItems()[0][1] != fila + 1) &&
                                    (hijo.getContadorItems()[0][2] != columna)) {
                                hijo.setDevolverse(true);
                                int[][] aux = hijo.getContadorItems();
                                aux[1][0] = 2;
                                aux[1][1] = fila;
                                aux[1][2] = columna;

                                hijo.setContadorItems(aux);
                            }
                        }

                    }
                    cola.add(hijo);
                }
            }

            hijo = new Mapa(padre);
            hijo.moverArriba();
            aux1 = hijo.getPosUltMov();
            aux2 = padre.getPosUltMov();
            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                int fila = hijo.getMoviJugador().get(aux1)[0];
                int columna = hijo.getMoviJugador().get(aux1)[1];
                if (auxLaberinto[fila][columna] != 1) {

                    if (auxLaberinto[fila][columna] == 5) {

                        if (hijo.getContadorItems()[0][0] == 0) {
                            hijo.setDevolverse(true);
                            int[][] aux = hijo.getContadorItems();
                            aux[0][0] = 1;
                            aux[0][1] = fila;
                            aux[0][2] = columna;

                            hijo.setContadorItems(aux);
                        } else {
                            if (hijo.getContadorItems()[1][0] == 0 && (hijo.getContadorItems()[0][1] != fila + 1) &&
                                    (hijo.getContadorItems()[0][2] != columna)) {
                                hijo.setDevolverse(true);
                                int[][] aux = hijo.getContadorItems();
                                aux[1][0] = 2;
                                aux[1][1] = fila;
                                aux[1][2] = columna;

                                hijo.setContadorItems(aux);
                            }
                        }

                    }
                    cola.add(hijo);
                }
            }

            hijo = new Mapa(padre);
            hijo.moverAbajo();
            aux1 = hijo.getPosUltMov();
            aux2 = padre.getPosUltMov();
            if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
                int fila = hijo.getMoviJugador().get(aux1)[0];
                int columna = hijo.getMoviJugador().get(aux1)[1];
                if (auxLaberinto[fila][columna] != 1) {

                    if (auxLaberinto[fila][columna] == 5) {

                        if (hijo.getContadorItems()[0][0] == 0) {
                            hijo.setDevolverse(true);
                            int[][] aux = hijo.getContadorItems();
                            aux[0][0] = 1;
                            aux[0][1] = fila;
                            aux[0][2] = columna;

                            hijo.setContadorItems(aux);
                        } else {
                            if (hijo.getContadorItems()[1][0] == 0 && (hijo.getContadorItems()[0][1] != fila + 1) &&
                                    (hijo.getContadorItems()[0][2] != columna)) {
                                hijo.setDevolverse(true);
                                int[][] aux = hijo.getContadorItems();
                                aux[1][0] = 2;
                                aux[1][1] = fila;
                                aux[1][2] = columna;

                                hijo.setContadorItems(aux);
                            }
                        }

                    }
                    cola.add(hijo);

                }
            }

            cola.remove(0);
        }

        int contadorPasos = 0;
        System.out.println("Eureka");
        for (int[] posiciones : cola.get(0).getMoviJugador()) {
            contadorPasos += 1;
            System.out.println("Fila: " + posiciones[0] + " Columna: " + posiciones[1]);
        }
        System.out.println("Pasos: " + contadorPasos);
        System.out.println(
                "Items: " + cola.get(0).getContadorItems()[0][0] + " Fila: " + cola.get(0).getContadorItems()[0][1]
                        + " Columna: " + cola.get(0).getContadorItems()[0][2]);
        System.out.println(
                "Items: " + cola.get(0).getContadorItems()[1][0] + " Fila: " + cola.get(0).getContadorItems()[1][1]
                        + " Columna: " + cola.get(0).getContadorItems()[1][2]);

    }

    public void prueba() {
        MapaCU padre = (MapaCU) mapaI;
        List<MapaCU> cola = new ArrayList<MapaCU>();
        cola.add(padre);
////////////////////////////////////////////////////////////
        MapaCU hijo = new MapaCU(padre);
        hijo.moverIzquierda();
        cola=procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverIzquierda();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
//////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverArriba();

        cola = (procesarMovimientoCU(hijo, padre, cola));
        cola.remove(0);
        
        padre=cola.get(0);

        hijo = new MapaCU(padre);
        hijo.moverArriba();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);

        /////////////////////////////////////////////

        padre=cola.get(0);

        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);

        /////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        ///////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        ///////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        /////////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);

        
        padre=cola.get(0);
        ////////////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverIzquierda();

        cola = procesarMovimientoCU(hijo, padre, cola);
        System.out.println("Fila "+ padre.getMoviJugador().get(padre.getPosUltMov())[0]+ " Columna "+ padre.getMoviJugador().get(padre.getPosUltMov())[1]);
        System.out.println("Encontre el problema Naves: " + padre.getNaves()[0][1]);
        System.out.println("Fila "+ hijo.getMoviJugador().get(hijo.getPosUltMov())[0]+ " Columna "+ hijo.getMoviJugador().get(hijo.getPosUltMov())[1]);
        System.out.println("Encontre el problema Naves: " + hijo.getNaves()[0][1]);
        
        cola.remove(0);
        
        padre=cola.get(0);
        ///////////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        /////////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverIzquierda();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverIzquierda();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverIzquierda();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        ////////////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverAbajo();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        /////////////////////////////////////////////////////////////////////
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);
        
        padre=cola.get(0);
        hijo = new MapaCU(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoCU(hijo, padre, cola);
        cola.remove(0);

        System.out.println("Eureka");
        for (int[] posiciones : cola.get(cola.size()-1).getMoviJugador()) {
            System.out.println("Fila: " + posiciones[0] + " Columna: " + posiciones[1]);
        }

        for (String moviString : cola.get(cola.size()-1).getDireccion()) {
            System.out.println(" " + moviString);
        }
        System.out.println("Peso: " + cola.get(cola.size()-1).getAcumulado());
        System.out.println(
                "Naves: " + cola.get(cola.size()-1).getNaves()[0][0] + " Fila: "
                        + cola.get(cola.size()-1).getNaves()[0][2]
                        + " Columna: " + cola.get(cola.size()-1).getNaves()[0][3]);

        System.out.println(
                "Items: " + cola.get(cola.size()-1).getContadorItems()[0][0] + " Fila: "
                        + cola.get(cola.size()-1).getContadorItems()[0][1]
                        + " Columna: " + cola.get(cola.size()-1).getContadorItems()[0][2]);
        System.out.println(
                "Items: " + cola.get(cola.size()-1).getContadorItems()[1][0] + " Fila: "
                        + cola.get(cola.size()-1).getContadorItems()[1][1]
                        + " Columna: " + cola.get(cola.size()-1).getContadorItems()[1][2]);
        
    }

    public void costoUniforme() throws CloneNotSupportedException {
        MapaCU padre = (MapaCU) mapaI;
        List<MapaCU> cola = new ArrayList<MapaCU>();
        int posMenorCostoFinal = 0;
        cola.add(padre);

        while (true) {
            int posMenorCosto = 0;
            int menorCosto = cola.get(0).getAcumulado();

            for (int x = 0; x < cola.size(); x++) {
                if (cola.get(x).getAcumulado() < menorCosto) {
                    posMenorCosto = x;
                    menorCosto = cola.get(x).getAcumulado();
                }
            }

            padre = new MapaCU(cola.get(posMenorCosto));

            if (padre.ganar()) {
                posMenorCostoFinal = posMenorCosto;
                break;
            }

            // movimiento hacia la izquiera

            MapaCU hijo = new MapaCU(padre);
            hijo.moverIzquierda();

            cola = procesarMovimientoCU(hijo, padre, cola);

            // -------------------------------------------------------------------------------

            // movimiento hacia la derecha
            hijo = new MapaCU(padre);
            hijo.moverDerecha();

            cola = procesarMovimientoCU(hijo, padre, cola);

            // -----------------------------------------------------------------------------

            // movimiento hacia arriba
            hijo = new MapaCU(padre);
            hijo.moverArriba();

            cola = procesarMovimientoCU(hijo, padre, cola);
            // ----------------------------------------------------------------------------

            // movimiento hacia abajo
            hijo = new MapaCU(padre);
            hijo.moverAbajo();

            cola = procesarMovimientoCU(hijo, padre, cola);
            // -----------------------------------------------------------------------------
            cola.remove(posMenorCosto);
        }

        System.out.println("Eureka");
        for (int[] posiciones : cola.get(posMenorCostoFinal).getMoviJugador()) {
            System.out.println("Fila: " + posiciones[0] + " Columna: " + posiciones[1]);
        }

        for (String moviString : cola.get(posMenorCostoFinal).getDireccion()) {
            System.out.println(" " + moviString);
        }
        System.out.println("Peso: " + cola.get(posMenorCostoFinal).getAcumulado());
        System.out.println(
                "Naves: " + cola.get(posMenorCostoFinal).getNaves()[0][0] + " Combustible " + cola.get(posMenorCostoFinal).getNaves()[0][1] + " Fila: "
                        + cola.get(posMenorCostoFinal).getNaves()[0][2]
                        + " Columna: " + cola.get(posMenorCostoFinal).getNaves()[0][3]);

        System.out.println(
                "Items: " + cola.get(posMenorCostoFinal).getContadorItems()[0][0] + " Fila: "
                        + cola.get(posMenorCostoFinal).getContadorItems()[0][1]
                        + " Columna: " + cola.get(posMenorCostoFinal).getContadorItems()[0][2]);
        System.out.println(
                "Items: " + cola.get(posMenorCostoFinal).getContadorItems()[1][0] + " Fila: "
                        + cola.get(posMenorCostoFinal).getContadorItems()[1][1]
                        + " Columna: " + cola.get(posMenorCostoFinal).getContadorItems()[1][2]);

    }

    public void procesarMovimientoAvaro() {
        
    }

    public void busquedaAvara() throws CloneNotSupportedException{
        MapaAvaro padre = (MapaAvaro) mapaI;
        int distHeuristica = 0;
        
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Para poder modificar más propiedades con Graphics 2d
        Graphics2D g2d = (Graphics2D) g;

        // Línea
        // g2d.setColor(Color.BLUE);
        // g2d.setStroke(new BasicStroke(5));
        // g2d.drawLine(30, 70, 770, 70);

        int[][] matrizMapa = laberinto.getLaberinto();
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
