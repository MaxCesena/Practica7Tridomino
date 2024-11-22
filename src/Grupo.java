import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Grupo {
    private final ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Jugador jugadorGanador;


    public Grupo(int numeroDeJugadores) {
        jugadores = new ArrayList<>();
        jugadorActual = new Jugador();
        jugadorGanador = new Jugador();

        for (int i = 0; i < numeroDeJugadores; i++) {
            jugadores.add(new Jugador());
        }
    }

    public void nombrarJugadores() {
        Scanner entrada = new Scanner(System.in);
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.print(String.format("Jugador %d: ", i + 1));
            jugador.setNombre(entrada.nextLine());

        }
    }




    public void cambiarOrdenDeJugadores(Jugador primerJugador, Grupo listaDeJugadores) {
        while (!listaDeJugadores.getJugadores().getFirst().equals(primerJugador)) {
            Collections.rotate(listaDeJugadores.getJugadores(), 1);
        }
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }


    public Jugador getJugadorActual() {
        return jugadorActual;
    }


    public void setJugadorActual(int indiceDeJugador) {
        this.jugadorActual = jugadores.get(indiceDeJugador);
    }


    public Jugador getJugadorGanador() {
        return jugadorGanador;
    }


    public void setJugadorGanador(Jugador jugadorGanador) {
        this.jugadorGanador = jugadorGanador;
    }
}