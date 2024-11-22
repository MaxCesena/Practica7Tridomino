import java.util.ArrayList;
import java.util.Scanner;

// Clase Jugador
class Jugador {
    private String nombre;
    private int puntuacion;
    private Soporte BoncheDeFichas;


    public Jugador() {
        nombre = "";
        puntuacion = 0;
        BoncheDeFichas = new Soporte();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Soporte getBoncheDeFichas() {
        return BoncheDeFichas;
    }

    public void setSoporteDeFichas(Soporte BoncheDeFichas) {
        this.BoncheDeFichas = BoncheDeFichas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public FichaDomino seleccionarFichaParaJugar() {
        FichaDomino fichaASeleccionar = null;
        Scanner entrada = new Scanner(System.in);
        int opcionParaFicha;
        ArrayList<FichaDomino> fichasJugables;

        do {

            System.out.println(String.format("Jugador %s, es tu turno.", nombre));
            System.out.println(String.format("Puntos de %s: "+ getPuntuacion(), nombre));

            fichasJugables = BoncheDeFichas.getFichasJugables();
            for (int i = 0; i < fichasJugables.size(); i++) {
                FichaDomino ficha = fichasJugables.get(i);
                System.out.println(String.format("%d. %s", i + 1, ficha));
            }

            opcionParaFicha = entrada.nextInt() - 1;

            if (opcionParaFicha >= 0 && opcionParaFicha < fichasJugables.size()) {
                fichaASeleccionar = BoncheDeFichas.seleccionarFicha(opcionParaFicha);
                if (fichaASeleccionar.getValorAbajo()==fichaASeleccionar.getValorArriba()){
                    fichaASeleccionar.rotateLeft();
                }
            } else {
                System.out.println("error, intentalo de nuevo");
            }
        } while (!(opcionParaFicha >= 0 && opcionParaFicha < fichasJugables.size()));

        return fichaASeleccionar;
    }



    public void retirarFichaQueSePudoColocar(FichaDomino fichaColocada) {
        BoncheDeFichas.retirarFicha(fichaColocada);
    }
}