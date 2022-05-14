import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Mapa implements Cloneable{
    private int casilla_libre = 0;
    private int muro = 1;
    private int inicio = 2;
    private int nave1 = 3;
    private int nave2 = 4;
    private int item = 5;
    private int aceite = 6;

    private boolean devolverse = false;

    private int[][] contadorItems;

    private List<int[]> moviJugador;

    private int[][] mapa;

    //constructor principal
    public Mapa(int[][] contadorItems,boolean devolverse,List<int[]> moviJugador, int[][] mapa){
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
        this.mapa = mapa;
    }
    //constructor de copia
    public Mapa(Mapa principal){
        this(principal.contadorItems, principal.devolverse,principal.moviJugador, principal.mapa);
    }

    // obtiene la posicion de su ultimo movimiento en el array
    public Object clone() throws CloneNotSupportedException 
   {
      return (Mapa)super.clone();
   }

    public int getPosUltMov() {
        return (moviJugador.size() - 1);
    }

    public List<int[]> getMoviJugador() {
        return moviJugador;
    }
    
    public void setMoviJugador(List<int[]> moviList){
        moviJugador = moviList;
    }

    public void agregarMovimiento(int fila, int columna) {
        int[] aux2 = new int[2];
        aux2[0] = fila;
        aux2[1] = columna;
        moviJugador.add(aux2);
    }

    public int getCasilla_libre() {
        return casilla_libre;
    }

    public void setContadorItems(int[][] contadorItemsE) {
        contadorItems = contadorItemsE;
    }

    public int[][] getContadorItems() {
        return this.contadorItems;
    }

    public int getMuro() {
        return muro;
    }

    public int getInicio() {
        return inicio;
    }

    public int getNave1() {
        return nave1;
    }

    public int getNave2() {
        return nave2;
    }

    public int getItem() {
        return item;
    }

    public int getAceite() {
        return aceite;
    }

    public int[][] getMapa() {
        return mapa;
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
                    moviJugador.add(aux2);

                }
                mapa[fila][columna] = Integer.parseInt(casilla);
                columna++;
            }
            fila++;
        }

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
        if(ultiMov == 0){
            ultiMov = 1;
        }
        if (fila != 9){
            if((mapaArriba.getMoviJugador().get(ultiMov-1)[0] != fila + 1) || (devolverse == true)){
                if (mapa[fila + 1][columna] != 1) {

                    agregarMovimiento(fila + 1, columna);
                    devolverse = false;
                    if (mapa[fila + 1][columna] == 5) {
                        if (contadorItems[0][0] == 0) {
                            devolverse = true;
                            int[][] aux = contadorItems;
                            aux[0][0] = 1;
                            aux[0][1] = fila + 1;
                            aux[0][2] = columna;
        
                            setContadorItems(aux);
                        } else {
                            if (contadorItems[1][0] == 0 && (contadorItems[0][1] != fila+1) && (contadorItems[0][1] != columna)) {
                                devolverse = true;
                                int[][] aux = contadorItems;
                                aux[1][0] = 2;
                                aux[1][1] = fila + 1;
                                aux[1][2] = columna;
        
                                setContadorItems(aux);
                            }
                        }
                    }
        
                }
            }
        }
        

        
    }

    public Mapa moverAbajo() {
        Mapa mapaAbajo = this;
        int ultiMov = mapaAbajo.getPosUltMov();
        int fila = mapaAbajo.getMoviJugador().get(ultiMov)[0];
        int columna = mapaAbajo.getMoviJugador().get(ultiMov)[1];
        if(ultiMov == 0){
            ultiMov = 1;
        }
        if (fila != 0) {
            if(mapaAbajo.getMoviJugador().get(ultiMov-1)[0] != fila - 1 || (devolverse == true)){
                if (mapa[fila - 1][columna] != 1 && fila != 0) {

                    mapaAbajo.agregarMovimiento(fila - 1, columna);
                    devolverse = false;
                    if (mapa[fila - 1][columna] == 5) {
                        if (contadorItems[0][0] == 0) {
                            devolverse = true;
                            int[][] aux = contadorItems;
                            aux[0][0] = 1;
                            aux[0][1] = fila - 1;
                            aux[0][2] = columna;
    
                            mapaAbajo.setContadorItems(aux);
                        } else {
                            if (contadorItems[1][0] == 0 && (contadorItems[0][1] != fila-1) && (contadorItems[0][1] != columna)) {
                                devolverse = true;
                                int[][] aux = contadorItems;
                                aux[1][0] = 2;
                                aux[1][1] = fila - 1;
                                aux[1][2] = columna;
    
                                mapaAbajo.setContadorItems(aux);
                            }
                        }
                    }
    
                }
            }
            
        }

        return mapaAbajo;
    }

    public Mapa moverDerecha() {
        Mapa mapaDerecha = this;
        int ultiMov = mapaDerecha.getPosUltMov();
        int fila = mapaDerecha.getMoviJugador().get(ultiMov)[0];
        int columna = mapaDerecha.getMoviJugador().get(ultiMov)[1];
        if(ultiMov == 0){
            ultiMov = 1;
        }
        if (columna != 9) {
            if((mapaDerecha.getMoviJugador().get(ultiMov-1)[1] != columna + 1) || (devolverse == true)){

                if (mapa[fila][columna + 1] != 1) {
                    mapaDerecha.agregarMovimiento(fila, columna + 1);
                    devolverse = false;
                    if (mapa[fila][columna + 1] == 5) {
                        if (contadorItems[0][0] == 0) {
                            devolverse = true;
                            int[][] aux = contadorItems;
                            aux[0][0] = 1;
                            aux[0][1] = fila;
                            aux[0][2] = columna + 1;
    
                            mapaDerecha.setContadorItems(aux);
                        } else {
                            if (contadorItems[1][0] == 0 && (contadorItems[0][1] != fila) && (contadorItems[0][1] != columna+1)) {
                                devolverse = true;
                                int[][] aux = contadorItems;
                                aux[1][0] = 2;
                                aux[1][1] = fila;
                                aux[1][2] = columna + 1;
    
                                mapaDerecha.setContadorItems(aux);
                            }
                        }
                    }
    
                }
            }
            
        }

        return mapaDerecha;
    }

    public Mapa moverIzquierda() {
        Mapa mapaIzquierda = this;
        int ultiMov = mapaIzquierda.getPosUltMov();
        int fila = mapaIzquierda.getMoviJugador().get(ultiMov)[0];
        int columna = mapaIzquierda.getMoviJugador().get(ultiMov)[1];
        if(ultiMov == 0){
            ultiMov = 1;
        }
        if (columna != 0) {
            if(mapaIzquierda.getMoviJugador().get(ultiMov-1)[1] != columna - 1 || devolverse == true){
                if (mapa[fila][columna - 1] != 1) {

                    mapaIzquierda.agregarMovimiento(fila, columna - 1);
                    devolverse = false;
                    if (mapa[fila][columna - 1] == 5) {
                        if (contadorItems[0][0] == 0) {
                            devolverse = true;
                            int[][] aux = contadorItems;
                            aux[0][0] = 1;
                            aux[0][1] = fila;
                            aux[0][2] = columna - 1;
    
                            mapaIzquierda.setContadorItems(aux);
                        } else {
                            if (contadorItems[1][0] == 0 && (contadorItems[0][1] != fila) && (contadorItems[0][1] != columna-1)) {
                                devolverse = true;
                                int[][] aux = contadorItems;
                                aux[1][0] = 2;
                                aux[1][1] = fila;
                                aux[1][2] = columna - 1;
    
                                mapaIzquierda.setContadorItems(aux);
                            }
                        }
                    }
    
                }
            }
            
        }

        return mapaIzquierda;
    }

}
