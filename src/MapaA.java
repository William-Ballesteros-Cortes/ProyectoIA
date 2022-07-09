import java.util.ArrayList;
import java.util.List;

public class MapaA extends MapaCU{

    private int heuristica = 0;
    private int fn;
    private List<Integer> fnList;

    public MapaA(int[][] contadorItems,boolean devolverse,List<int[]> moviJugador, List<String> direccion, int acumuladoI, int[][] navesI, int heuristicaI, List<Integer> fnListI){
        super(contadorItems, devolverse, moviJugador, direccion, acumuladoI, navesI);
        this.heuristica = heuristicaI; 

        this.fnList = new ArrayList<Integer>();
        this.fnList.addAll(fnListI);
    }

    // constructor de copia
    public MapaA (MapaA principal){
        this(principal.getContadorItems(), principal.getDevolverse(), principal.getMoviJugador(), principal.getDireccion(), principal.getAcumulado(), principal.getNaves(), principal.getHeuristica(), principal.getFnList());
    }

    public List<Integer> getFnList(){
        return this.fnList;
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
        this.fnList.add(this.fn);
    }
    
}
