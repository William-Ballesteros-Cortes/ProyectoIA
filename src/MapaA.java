import java.util.List;

public class MapaA extends MapaCU{

    private int heuristica = 0;
    private int fn = 0;

    public MapaA(int[][] contadorItems,boolean devolverse,List<int[]> moviJugador, List<String> direccion, int acumuladoI, int[][] navesI, int heuristicaI){
        super(contadorItems, devolverse, moviJugador, direccion, acumuladoI, navesI);
        this.heuristica = heuristicaI; 
    }

    // constructor de copia
    public MapaA (MapaA principal){
        this(principal.getContadorItems(), principal.getDevolverse(), principal.getMoviJugador(), principal.getDireccion(), principal.getAcumulado(), principal.getNaves(), principal.getHeuristica());
    }

    public int getHeuristica(){
        return this.heuristica;
    }

    public int getFn(){
        return this.fn;
    }

    public void setHeuristica(int valorH){
        this.heuristica = valorH;
    }

    public void setFn(){
        this.fn = this.heuristica + getAcumulado();
    }
    
}
