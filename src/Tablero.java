import java.util.ArrayList;

public class Tablero{
    private final ArrayList<FichaDomino> fichasColocadas;

    public Tablero() {
        fichasColocadas = new ArrayList<>();
    }

    public void colocarFichaEnTablero(FichaDomino fichaAColocar) {
        fichasColocadas.addLast(fichaAColocar);
    }

    public ArrayList<FichaDomino> getFichasColocadas() {
        return fichasColocadas;
    }

    public void mostrarTablero() {
        System.out.println("Tablero:");
        for (FichaDomino ficha : fichasColocadas) {
            System.out.println(ficha.mostrarFichaCompleta());
        }
    }


}