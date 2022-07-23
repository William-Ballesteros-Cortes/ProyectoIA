import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Map;

import static java.awt.Font.PLAIN;

public class Ventana extends JFrame implements ActionListener {
    JPanel panel, panel2;
    JScrollPane scroll;
    JComboBox metodos;
    JTextArea camino;
    JButton aplicar;
    String caminoCorrecto;
    Mapa mapaI;
    MapaCU mapaCUI;
    MapaA mapaAI;
    Laberinto laberinto = new Laberinto();

    public Ventana() throws FileNotFoundException {

        laberinto.llenarMapa("src/Prueba1.txt");
        initPanel();
        // initPanel2();
        initPantalla();
    }

    public void inicializarMapas(Object mapaE, int id) {

        switch (id) {
            case 0:
                mapaI = (Mapa) mapaE;
                mapaI.agregarMovimiento(laberinto.getPosInicio()[0], laberinto.getPosInicio()[1]);
                break;
            case 1:
                mapaCUI = (MapaCU) mapaE;
                mapaCUI.agregarMovimiento(laberinto.getPosInicio()[0], laberinto.getPosInicio()[1]);
                break;
            case 2:
                mapaAI = (MapaA) mapaE;
                mapaAI.agregarMovimiento(laberinto.getPosInicio()[0], laberinto.getPosInicio()[1]);
                break;
        }

    }

    void initPanel() {
        String[] lista = { "AMPLITUD", "COSTO UNIFORME", "PROFUNDIDAD", "ÁVARA", "A*" };

        panel = new JPanel();
        add(panel); // Añado el panel al JFrame
        panel.setPreferredSize(new Dimension(1000, 750)); // Dimensiones del panel
        panel.setLocation(0, 0);
        panel.setLayout(null);

        JLabel etiqueta = new JLabel();
        etiqueta.setText("SELECCIONE UN MÉTODO DE BÚSQUEDA: ");
        etiqueta.setBounds(680, 60, 300, 40);
        panel.add(etiqueta);

        metodos = new JComboBox(lista);
        metodos.setBounds(740, 100, 150, 30);
        panel.add(metodos);

        camino = new JTextArea();
        camino.setEditable(false);
        panel.add(camino);

        scroll = new JScrollPane(camino, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(680, 210, 270, 400);
        panel.add(scroll);

        aplicar = new JButton();
        aplicar.setText("BUSCAR SOLUCIÓN");
        aplicar.setBounds(715, 160, 200, 30);
        panel.add(aplicar);

        aplicar.addActionListener(this);

    }

    private void initPantalla() {

        setTitle("Ventana");
        setSize(1000, 750);
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
                if (auxLaberinto[fila][columna] == 0 || auxLaberinto[fila][columna] == 5 || auxLaberinto[fila][columna] == 2) {
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
        caminoCorrecto = "Camino encontrado por amplitud: ";
        System.out.println("Eureka");
        for (int[] posiciones : cola.get(0).getMoviJugador()) {
            contadorPasos += 1;
            caminoCorrecto = caminoCorrecto + "\nFila: " + posiciones[0] + " Columna: " + posiciones[1];
        }
        caminoCorrecto = caminoCorrecto + "\nPasos: " + contadorPasos;
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(0).getContadorItems()[0][0] + " Fila: " + cola.get(0).getContadorItems()[0][1]
                + " Columna: " + cola.get(0).getContadorItems()[0][2];
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(0).getContadorItems()[1][0] + " Fila: " + cola.get(0).getContadorItems()[1][1]
                + " Columna: " + cola.get(0).getContadorItems()[1][2];

    }

    public void profundidad() throws CloneNotSupportedException {
        Mapa padre = (Mapa) mapaI;
        List<Mapa> cola = new ArrayList<Mapa>();
        cola.add(padre);

        while (true) {

            padre = new Mapa(cola.get(0));

            if (padre.ganar()) {
                break;
            }
            cola.remove(0);
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

                    Collections.reverse(cola);
                    cola.add(hijo);
                    Collections.reverse(cola);
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
                    Collections.reverse(cola);
                    cola.add(hijo);
                    Collections.reverse(cola);
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
                    Collections.reverse(cola);
                    cola.add(hijo);
                    Collections.reverse(cola);
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
                    Collections.reverse(cola);
                    cola.add(hijo);
                    Collections.reverse(cola);

                }
            }
        }

        int contadorPasos = 0;
        caminoCorrecto = "Camino encontrado por profundidad:";
        System.out.println("Eureka");
        for (int[] posiciones : cola.get(0).getMoviJugador()) {
            contadorPasos += 1;
            caminoCorrecto = caminoCorrecto + "\nFila: " + posiciones[0] + " Columna: " + posiciones[1];
        }
        caminoCorrecto = caminoCorrecto + "\nPasos: " + contadorPasos;
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(0).getContadorItems()[0][0] + " Fila: " + cola.get(0).getContadorItems()[0][1]
                + " Columna: " + cola.get(0).getContadorItems()[0][2];
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(0).getContadorItems()[1][0] + " Fila: " + cola.get(0).getContadorItems()[1][1]
                + " Columna: " + cola.get(0).getContadorItems()[1][2];

    }

    public void prueba() {
        MapaA padre = (MapaA) mapaAI;
        List<MapaA> cola = new ArrayList<MapaA>();
        cola.add(padre);

        MapaA hijo = new MapaA(padre);
        hijo.moverDerecha();

        cola = procesarMovimientoA(hijo, padre, cola);

        MapaA hijo2 = new MapaA(cola.get(1));

        hijo2.moverDerecha();

        cola = procesarMovimientoA(hijo2, padre, cola);

        MapaA hijo3 = new MapaA(cola.get(2));

        hijo3.moverDerecha();

        cola = procesarMovimientoA(hijo3, padre, cola);

        System.out.println("Eureka");
        caminoCorrecto = "Camino encontrado por A*:";
        for (int[] posiciones : cola.get(3).getMoviJugador()) {
            caminoCorrecto = caminoCorrecto + "\nFila: " + posiciones[0] + " Columna: " + posiciones[1];
        }
        for (int fn : cola.get(3).getFnList()) {
            caminoCorrecto = caminoCorrecto + "\nFn: " + fn;
        }
        caminoCorrecto = caminoCorrecto + "\nPeso: " + cola.get(3).getAcumulado();
        caminoCorrecto = caminoCorrecto +
                "\nNaves: " + cola.get(3).getNaves()[0][0] + " Combustible "
                + cola.get(3).getNaves()[0][1] + " Fila: "
                + cola.get(3).getNaves()[0][2]
                + " Columna: " + cola.get(3).getNaves()[0][3];

        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(3).getContadorItems()[0][0] + " Fila: "
                + cola.get(3).getContadorItems()[0][1]
                + " Columna: " + cola.get(3).getContadorItems()[0][2];
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(3).getContadorItems()[1][0] + " Fila: "
                + cola.get(3).getContadorItems()[1][1]
                + " Columna: " + cola.get(3).getContadorItems()[1][2];
    }



    public void a_asterisco() {
        MapaA padre = (MapaA) mapaAI;
        List<MapaA> cola = new ArrayList<MapaA>();
        int posMenorFnFinal = 0;
        cola.add(padre);

        while (true) {
            int posMenorFn = 0;

            int menorFn = cola.get(0).getFn();

            for (int x = 0; x < cola.size(); x++) {
                if (cola.get(x).getFn() < menorFn) {
                    posMenorFn = x;
                    menorFn = cola.get(x).getFn();
                }
            }

            padre = new MapaA(cola.get(posMenorFn));

            if (padre.ganar()) {
                posMenorFnFinal = posMenorFn;
                break;
            }

            // movimiento hacia la izquiera

            MapaA hijo = new MapaA(padre);
            hijo.moverIzquierda();

            cola = procesarMovimientoA(hijo, padre, cola);

            // -------------------------------------------------------------------------------

            // movimiento hacia la derecha
            hijo = new MapaA(padre);
            hijo.moverDerecha();

            cola = procesarMovimientoA(hijo, padre, cola);

            // -----------------------------------------------------------------------------

            // movimiento hacia arriba
            hijo = new MapaA(padre);
            hijo.moverArriba();

            cola = procesarMovimientoA(hijo, padre, cola);
            // ----------------------------------------------------------------------------

            // movimiento hacia abajo
            hijo = new MapaA(padre);
            hijo.moverAbajo();

            cola = procesarMovimientoA(hijo, padre, cola);
            // -----------------------------------------------------------------------------
            cola.remove(posMenorFn);

        }

        System.out.println("Eureka");
        caminoCorrecto = "Camino encontrado por A*:";
        for (int[] posiciones : cola.get(posMenorFnFinal).getMoviJugador()) {
            caminoCorrecto = caminoCorrecto + "\nFila: " + posiciones[0] + " Columna: " + posiciones[1];
        }
        for (int fn : cola.get(posMenorFnFinal).getFnList()) {
            caminoCorrecto = caminoCorrecto + "\nFn: " + fn;
        }

        caminoCorrecto = caminoCorrecto + "\nPeso: " + cola.get(posMenorFnFinal).getAcumulado();
        caminoCorrecto = caminoCorrecto +
                "\nNave 1: " + cola.get(posMenorFnFinal).getNaves()[0][0] + " Combustible "
                + cola.get(posMenorFnFinal).getNaves()[0][1] + " Fila: "
                + cola.get(posMenorFnFinal).getNaves()[0][2]
                + " Columna: " + cola.get(posMenorFnFinal).getNaves()[0][3];

        caminoCorrecto = caminoCorrecto +
                "\nNave 2: " + cola.get(posMenorFnFinal).getNaves()[1][0] + " Combustible "
                + cola.get(posMenorFnFinal).getNaves()[1][1] + " Fila: "
                + cola.get(posMenorFnFinal).getNaves()[1][2]
                + " Columna: " + cola.get(posMenorFnFinal).getNaves()[0][3];

        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(posMenorFnFinal).getContadorItems()[0][0] + " Fila: "
                + cola.get(posMenorFnFinal).getContadorItems()[0][1]
                + " Columna: " + cola.get(posMenorFnFinal).getContadorItems()[0][2];
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(posMenorFnFinal).getContadorItems()[1][0] + " Fila: "
                + cola.get(posMenorFnFinal).getContadorItems()[1][1]
                + " Columna: " + cola.get(posMenorFnFinal).getContadorItems()[1][2];

    }

    public List<MapaA> procesarMovimientoA(MapaA hijo, MapaA padre, List<MapaA> cola) {

        List<MapaA> auxCola = new ArrayList<MapaA>();
        auxCola.addAll(cola);
        int aux1 = hijo.getPosUltMov();
        int aux2 = padre.getPosUltMov();
        int aux3 = hijo.getAcumulado();

        int[][] auxLaberinto = laberinto.getLaberinto();
        if (!hijo.getMoviJugador().get(aux1).equals(padre.getMoviJugador().get(aux2))) {
            int fila = hijo.getMoviJugador().get(aux1)[0];
            int columna = hijo.getMoviJugador().get(aux1)[1];
            if (auxLaberinto[fila][columna] != 1) {
                if (auxLaberinto[fila][columna] == 0 || auxLaberinto[fila][columna] == 5
                        || auxLaberinto[fila][columna] == 2) {
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
                int[] posJugador = hijo.getMoviJugador().get(aux1);
                int dA = Math.abs(posJugador[0] - laberinto.getPosItems().get(0)[0])
                        + Math.abs(posJugador[1] - laberinto.getPosItems().get(0)[1]);

                int dB = Math.abs(posJugador[0] - laberinto.getPosItems().get(1)[0])
                        + Math.abs(posJugador[1] - laberinto.getPosItems().get(1)[1]);

                int[][] aux = hijo.getContadorItems();

                if (aux[0][0] == 0 && aux[1][0] == 0) {

                    int dOcercano = 0;
                    if (dA < dB) {
                        dOcercano = dA;
                    } else {
                        dOcercano = dB;
                    }

                    int dAB = Math.abs(laberinto.getPosItems().get(0)[0] - laberinto.getPosItems().get(1)[0])
                            + Math.abs(laberinto.getPosItems().get(0)[1] - laberinto.getPosItems().get(1)[1]);

                    hijo.setHeuristica(dOcercano + dAB);
                } else {
                    if (aux[0][1] == laberinto.getPosItems().get(0)[0]
                            && aux[0][2] == laberinto.getPosItems().get(0)[1]) {
                        hijo.setHeuristica(dB);
                    }
                    if (aux[0][1] == laberinto.getPosItems().get(1)[0]
                            && aux[0][2] == laberinto.getPosItems().get(1)[1]) {
                        hijo.setHeuristica(dA);
                    }
                }
                hijo.setFn();

                auxCola.add(hijo);
            }
        }

        return auxCola;
    }

    public void costoUniforme() throws CloneNotSupportedException {
        MapaCU padre = (MapaCU) mapaCUI;
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
        caminoCorrecto = "Camino encontrado por costo uniforme:";
        for (int[] posiciones : cola.get(posMenorCostoFinal).getMoviJugador()) {
            caminoCorrecto = caminoCorrecto + "\nFila: " + posiciones[0] + " Columna: " + posiciones[1];
        }

        for (String moviString : cola.get(posMenorCostoFinal).getDireccion()) {
            System.out.println(" " + moviString);
        }
        caminoCorrecto = caminoCorrecto + "\nPeso: " + cola.get(posMenorCostoFinal).getAcumulado();
        caminoCorrecto = caminoCorrecto +
                "\nNave 1: " + cola.get(posMenorCostoFinal).getNaves()[0][0] + " Combustible "
                + cola.get(posMenorCostoFinal).getNaves()[0][1] + " Fila: "
                + cola.get(posMenorCostoFinal).getNaves()[0][2]
                + " Columna: " + cola.get(posMenorCostoFinal).getNaves()[0][3];

        caminoCorrecto = caminoCorrecto +
                "\nNave 2: " + cola.get(posMenorCostoFinal).getNaves()[1][0] + " Combustible "
                + cola.get(posMenorCostoFinal).getNaves()[1][1] + " Fila: "
                + cola.get(posMenorCostoFinal).getNaves()[1][2]
                + " Columna: " + cola.get(posMenorCostoFinal).getNaves()[0][3];

        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(posMenorCostoFinal).getContadorItems()[0][0] + " Fila: "
                + cola.get(posMenorCostoFinal).getContadorItems()[0][1]
                + " Columna: " + cola.get(posMenorCostoFinal).getContadorItems()[0][2];
        caminoCorrecto = caminoCorrecto +
                "\nItems: " + cola.get(posMenorCostoFinal).getContadorItems()[1][0] + " Fila: "
                + cola.get(posMenorCostoFinal).getContadorItems()[1][1]
                + " Columna: " + cola.get(posMenorCostoFinal).getContadorItems()[1][2];

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String metodo = (String) metodos.getSelectedItem();
        if (e.getSource() == aplicar) {
            if (metodo == "AMPLITUD") {
                camino.setText("");
                try {
                    Mapa mapa = new Mapa(new int[2][3], false, new ArrayList<int[]>(), new ArrayList<String>());
                    this.inicializarMapas(mapa, 0);
                    this.amplitud();
                    camino.setText(caminoCorrecto);
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (metodo == "PROFUNDIDAD") {
                camino.setText("");
                try {
                    Mapa mapa = new Mapa(new int[2][3], false, new ArrayList<int[]>(), new ArrayList<String>());
                    this.inicializarMapas(mapa, 0);
                    this.profundidad();
                    camino.setText(caminoCorrecto);
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (metodo == "PROFUNDIDAD") {
                camino.setText("");
                try {
                    Mapa mapa = new Mapa(new int[2][3], false, new ArrayList<int[]>(), new ArrayList<String>());
                    this.inicializarMapas(mapa, 0);
                    this.profundidad();
                    camino.setText(caminoCorrecto);
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (metodo == "COSTO UNIFORME") {
                camino.setText("");
                try {
                    MapaCU mapa = new MapaCU(new int[2][3], false, new ArrayList<int[]>(), new ArrayList<String>(), 0,
                            new int[2][4]);
                    this.inicializarMapas(mapa, 1);
                    this.costoUniforme();
                    camino.setText(caminoCorrecto);
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (metodo == "A*") {
                camino.setText("");

                MapaA mapa = new MapaA(new int[2][3], false, new ArrayList<int[]>(), new ArrayList<String>(), 0,
                        new int[2][4], 0, new ArrayList<Integer>());
                this.inicializarMapas(mapa, 2);
                this.a_asterisco();
                camino.setText(caminoCorrecto);

            }

        }
    }

}
