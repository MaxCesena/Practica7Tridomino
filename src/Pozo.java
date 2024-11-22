import java.util.ArrayList;
import java.util.Collections;

public class Pozo {
    private final ArrayList<FichaDomino> fichasDelPozo;
    public boolean fin;

    public Pozo() {
        fichasDelPozo = new ArrayList<>();
        crearFichasDomino();
        crearFichasTriomino();
        System.out.println();
    }

    private void crearFichasDomino() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fichasDelPozo.add(new FichaDomino(i, j));
            }
        }
    }

    private void crearFichasTriomino() {
        for (int i = 0; i <= 5; i++) {
            for (int j = i; j <= 5; j++) {
                for (int k = j; k <= 5; k++) {
                    fichasDelPozo.add(new FichaTriomino(i, j, k));
                }
            }
        }
    }

    public void mezclarFichas() {
        Collections.shuffle(fichasDelPozo);
    }

    public void vaciarPozo(){
        fichasDelPozo.clear();
    }

    public FichaDomino retirarFichaAleatoria() {
        mezclarFichas();
        if (fichasDelPozo.isEmpty()){
            fin = true;
        }
        else if (fichasDelPozo.size() >= 1){
            FichaDomino fichaARegresar = fichasDelPozo.getFirst();
            fichasDelPozo.removeFirst();
            return fichaARegresar;
        }
        return null;
    }

    public void retirarFicha(FichaDomino fichaARetirar) {
        fichasDelPozo.remove(fichaARetirar);
    }

    public FichaDomino seleccionarFichaAleatoria() {
        mezclarFichas();
        return fichasDelPozo.getFirst();
    }

    public ArrayList<FichaDomino> getFichasDelPozo() {
        return fichasDelPozo;
    }
}