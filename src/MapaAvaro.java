import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapaAvaro extends Mapa{

    private int distHeuristica;
    // la posicion 0 es el numero de la nave, 1 los pasos restantes, 2 la fila y 3 columna
    private int[][] naves;

    public MapaAvaro(int[][] contadorItems,boolean devolverse,List<int[]> moviJugador, List<String> direccion, int distHeuristicaI, int[][] navesI){
        super(contadorItems, devolverse, moviJugador, direccion);
        this.distHeuristica = distHeuristicaI;
        
        this.naves = new int[2][4];
        this.naves[0][0] = navesI[0][0];
        this.naves[0][1] = navesI[0][1];
        this.naves[0][2] = navesI[0][2];
        this.naves[0][3] = navesI[0][3];
        this.naves[1][0] = navesI[1][0];
        this.naves[1][1] = navesI[1][1];
        this.naves[1][2] = navesI[1][2];
        this.naves[1][3] = navesI[1][3];
    }

    public MapaAvaro(MapaAvaro principal){
        this(principal.getContadorItems(), principal.getDevolverse(), principal.getMoviJugador(), principal.getDireccion(), principal.getDistHeuristica(), principal.getNaves());
    }

    public void setDistHeuristica(int distHeuristicaI){
        this.distHeuristica = distHeuristicaI;
    }

    public int getDistHeuristica(){
        return this.distHeuristica;
    }

    public void setNaves(int[][] naves){
        this.naves = new int[2][4];
        this.naves[0][0] = naves[0][0];
        this.naves[0][1] = naves[0][1];
        this.naves[0][2] = naves[0][2];
        this.naves[0][3] = naves[0][3];
        this.naves[1][0] = naves[1][0];
        this.naves[1][1] = naves[1][1];
        this.naves[1][2] = naves[1][2];
        this.naves[1][3] = naves[1][3];
    }

    public int[][] getNaves(){
        return naves;
    }

}