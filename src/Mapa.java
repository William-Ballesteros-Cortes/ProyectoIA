import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Mapa implements Cloneable {
    // private int casilla_libre = 0;
    // private int muro = 1;
    // private int inicio = 2;
    // private int nave1 = 3;
    // private int nave2 = 4;
    // private int item = 5;
    // private int aceite = 6;

    private boolean devolverse = false;

    private int[][] contadorItems;

    private List<int[]> moviJugador;
    private List<String> direccion;

    // constructor principal
    public Mapa(int[][] contadorItems, boolean devolverse, List<int[]> moviJugador, List<String> direccion) {
        this.contadorItems = new int[2][3];
        this.contadorItems[0][0] = contadorItems[0][0];
        this.contadorItems[0][1] = contadorItems[0][1];
        this.contadorItems[0][2] = contadorItems[0][2];
        this.contadorItems[1][0] = contadorItems[1][0];
        this.contadorItems[1][1] = contadorItems[1][1];
        this.contadorItems[1][2] = contadorItems[1][2];

        this.devolverse = devolverse;
        this.moviJugador = new ArrayList<int[]>();
        this.moviJugador.addAll(moviJugador);

        this.direccion = new ArrayList<String>();
        this.direccion.addAll(direccion);
    }

    // constructor de copia
    public Mapa(Mapa principal) {
        this(principal.contadorItems, principal.devolverse, principal.moviJugador, principal.direccion);
    }

    public boolean getDevolverse() {
        return devolverse;
    }

    public void setDevolverse(boolean devolverse) {
        this.devolverse = devolverse;
    }

    public List<String> getDireccion(){
        return direccion;
    }

    // obtiene la posicion de su ultimo movimiento en el array
    public Object clone() throws CloneNotSupportedException {
        return (Mapa) super.clone();
    }

    public int getPosUltMov() {
        return (moviJugador.size() - 1);
    }

    public List<int[]> getMoviJugador() {
        return moviJugador;
    }

    public void setMoviJugador(List<int[]> moviList) {
        moviJugador = moviList;
    }

    public void agregarMovimiento(int fila, int columna) {
        int[] aux2 = new int[2];
        aux2[0] = fila;
        aux2[1] = columna;
        moviJugador.add(aux2);
    }

    public void setContadorItems(int[][] contadorItemsE) {
        contadorItems = contadorItemsE;
    }

    public int[][] getContadorItems() {
        return this.contadorItems;
    }

    public boolean ganar() {
        if (contadorItems[0][0] == 1 && contadorItems[1][0] == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void moverArriba() {
        Mapa mapaArriba = this;
        int ultiMov = getPosUltMov();
        int fila = getMoviJugador().get(ultiMov)[0];
        int columna = getMoviJugador().get(ultiMov)[1];
        if (ultiMov == 0) {
            ultiMov = 1;
        }
        if (fila != 0) {
            if ((mapaArriba.getMoviJugador().get(ultiMov - 1)[0] != fila - 1) || (devolverse == true)) {
                agregarMovimiento(fila - 1, columna);
                direccion.add("Arriba");
                devolverse = false;
            }

        }
    }

    public void moverAbajo() {
        Mapa mapaAbajo = this;
        int ultiMov = mapaAbajo.getPosUltMov();
        int fila = mapaAbajo.getMoviJugador().get(ultiMov)[0];
        int columna = mapaAbajo.getMoviJugador().get(ultiMov)[1];
        if (ultiMov == 0) {
            ultiMov = 1;
        }
        if (fila != 9) {
            if (mapaAbajo.getMoviJugador().get(ultiMov - 1)[0] != fila + 1 || (devolverse == true)) {

                mapaAbajo.agregarMovimiento(fila + 1, columna);
                direccion.add("Abajo");
                devolverse = false;

            }
        }

    }

    public void moverDerecha() {
        Mapa mapaDerecha = this;
        int ultiMov = mapaDerecha.getPosUltMov();
        int fila = mapaDerecha.getMoviJugador().get(ultiMov)[0];
        int columna = mapaDerecha.getMoviJugador().get(ultiMov)[1];
        if (ultiMov == 0) {
            ultiMov = 1;
            
        }
        if (columna != 9) {
            if ((mapaDerecha.getMoviJugador().get(ultiMov - 1)[1] != columna + 1) || (devolverse == true)) {

                mapaDerecha.agregarMovimiento(fila, columna + 1);
                direccion.add("Derecha");
                devolverse = false;
                

            }
        }

    }

    public void moverIzquierda() {
        Mapa mapaIzquierda = this;
        int ultiMov = mapaIzquierda.getPosUltMov();
        int fila = mapaIzquierda.getMoviJugador().get(ultiMov)[0];
        int columna = mapaIzquierda.getMoviJugador().get(ultiMov)[1];
        
        if (ultiMov == 0) {
            ultiMov = 1;
        }
        if (columna != 0) {
            if (mapaIzquierda.getMoviJugador().get(ultiMov - 1)[1] != columna - 1 || devolverse == true) {

                mapaIzquierda.agregarMovimiento(fila, columna - 1);
                direccion.add("Izquierda");
                devolverse = false;

            }
        }

    }

}
