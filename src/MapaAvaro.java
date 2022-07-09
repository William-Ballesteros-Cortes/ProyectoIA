import java.util.List;

public class MapaAvaro extends MapaCU{

    private int heuristica = 0;
    private int fn = 0;

    public MapaAvaro(int[][] contadorItems,boolean devolverse,List<int[]> moviJugador, List<String> direccion, int[][] navesI, int heuristicaI){
        super(contadorItems, devolverse, moviJugador, direccion, navesI);
        this.heuristica = heuristicaI; 
    }

    // constructor de copia
    public MapaAvaro (MapaAvaro principal){
        this(principal.getContadorItems(), principal.getDevolverse(), principal.getMoviJugador(), principal.getDireccion(), principal.getNaves(), principal.getHeuristica());
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
        this.fn = this.heuristica;
    }
    
}