import java.util.ArrayList;


public class Soporte {
    private final ArrayList<FichaDomino> fichasJugables;


    public Soporte() {
        fichasJugables = new ArrayList<>();
    }


    // Retorna el tamaño de la lista de fichas
    public int size() {
        return fichasJugables.size();
    }


    public FichaDomino seleccionarFicha(int indiceDeFicha) {
        return fichasJugables.get(indiceDeFicha);
    }


    public void retirarFicha(FichaDomino fichaARetirar) {
        fichasJugables.remove(fichaARetirar);
    }


    public ArrayList<FichaDomino> getFichasJugables() {
        return fichasJugables;
    }
}