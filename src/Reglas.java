public class Reglas {
    private final Grupo grupoDeJugadores;
    private final Tablero tableroDeJuego;
    private final Pozo pozoDeJuego;

    public Reglas() {
        grupoDeJugadores = new Grupo(2);
        tableroDeJuego = new Tablero();
        pozoDeJuego = new Pozo();
    }

    public void inicializarJuego() {
        grupoDeJugadores.nombrarJugadores();
        jugar();
    }



    public void jugar() {
        mezclarFichas();
        darFichasIniciales();



        determinarJugadorInicial();

        int turno;
        while (!seAcaboElJuego()) {
            turno = 0; // Contador para los turnos de los jugadores
            while (turno < 2) {
                grupoDeJugadores.setJugadorActual(turno);
                colocarFicha();
                turno++;

            }
        }

        declararGanadorDelJuego();
    }

    private void mezclarFichas() {
        pozoDeJuego.mezclarFichas();
    }

    private void darFichasIniciales() {
        for (Jugador jugador : grupoDeJugadores.getJugadores()) {
            repartirFichas(10, jugador);
        }
    }

    private void determinarJugadorInicial() {
        FichaDomino[] fichasIniciales = new FichaDomino[2];
        int[] valores = new int[2];

        do {
            for (int i = 0; i < 2; i++) {
                fichasIniciales[i] = pozoDeJuego.seleccionarFichaAleatoria();
                valores[i] = fichasIniciales[i].sumarValores();

                    System.out.println("El jugador " + grupoDeJugadores.getJugadores().get(i) + " tiene ficha con " + valores[i] + ".");

            }

            if (valores[0] > valores[1]) {
                tableroDeJuego.colocarFichaEnTablero(fichasIniciales[0]);
                pozoDeJuego.retirarFicha(fichasIniciales[0]);

                    System.out.println("El jugador " + grupoDeJugadores.getJugadores().getFirst() + " comienza");

            } else if (valores[1] > valores[0]) {
                grupoDeJugadores.cambiarOrdenDeJugadores(grupoDeJugadores.getJugadores().getLast(), grupoDeJugadores);
                tableroDeJuego.colocarFichaEnTablero(fichasIniciales[1]);
                pozoDeJuego.retirarFicha(fichasIniciales[1]);

                    System.out.println("El jugador " + grupoDeJugadores.getJugadores().getFirst() + " gano el duelo");

            }
            if (valores[0] == valores[1]) {

                    System.out.println("Empate, vuelven a competir");

            }
        } while (valores[0] == valores[1]);
    }

    private void colocarFicha() {
        Jugador jugadorActual = grupoDeJugadores.getJugadorActual();
        FichaDomino fichaParaJugar;
        boolean sePuedeJugarFicha;
        if (tieneJugadasDisponibles(jugadorActual)) {
            do {
                tableroDeJuego.mostrarTablero();

                    fichaParaJugar = jugadorActual.seleccionarFichaParaJugar();

                sePuedeJugarFicha = valida(fichaParaJugar);
                if (sePuedeJugarFicha) {
                    tableroDeJuego.colocarFichaEnTablero(fichaParaJugar);
                    jugadorActual.getBoncheDeFichas().retirarFicha(fichaParaJugar);
                    sumaPuntosFicha(fichaParaJugar);
                } else {

                        System.out.println("Error, selecciona nueva ficha.");

                }
            } while (!sePuedeJugarFicha);
        } else {
            if (!pozoDeJuego.getFichasDelPozo().isEmpty()) {

                    System.out.println(jugadorActual + " toma del pozo.");

                obtenerFichasDelPozo();
            } else {

                    System.out.println("No hay fichas en el pozo.");
                    declararGanadorDelJuego();

            }
        }
    }

    private boolean tieneJugadasDisponibles(Jugador jugador) {
        for (FichaDomino ficha : jugador.getBoncheDeFichas().getFichasJugables()) {
            if (valida(ficha)) {
                return true;
            }
        }
        return false;
    }

    private boolean valida(FichaDomino fichaParaJugar) {
        FichaDomino ultimaFichaJugada = tableroDeJuego.getFichasColocadas().getLast();

        if (ultimaFichaJugada instanceof FichaTriomino) {
            if (fichaParaJugar instanceof FichaTriomino) {
                if (ultimaFichaJugada.isOrientacion() == FichaTriomino.ARRIBA) {

                    if (fichaParaJugar.isOrientacion() == FichaTriomino.ARRIBA) {
                        fichaParaJugar.rotateRight();
                    }
                    for (int i = 0; i < 4; i++) {
                        if (((FichaTriomino) ultimaFichaJugada).getValorIzquierdo() == ((FichaTriomino) fichaParaJugar).getValorIzquierdo() &&
                                ((FichaTriomino) ultimaFichaJugada).getValorDerecho() == ((FichaTriomino) fichaParaJugar).getValorDerecho()) {
                            return true;
                        }
                        fichaParaJugar.rotateRight();
                        fichaParaJugar.rotateRight();
                    }
                } else if (ultimaFichaJugada.isOrientacion() == FichaTriomino.ABAJO) {
                    if (fichaParaJugar.isOrientacion() == FichaTriomino.ABAJO) {
                        fichaParaJugar.rotateRight();
                    }
                    for (int i = 0; i < 4; i++) {
                        if (ultimaFichaJugada.getValorCentro() == fichaParaJugar.getValorCentro()) {
                            return true;
                        }
                        fichaParaJugar.rotateRight();
                        fichaParaJugar.rotateRight();
                    }
                }
            } else {
                if (ultimaFichaJugada.isOrientacion() == FichaTriomino.ABAJO) {
                    if (ultimaFichaJugada.getValorCentro() == fichaParaJugar.getValorArriba()) {
                        return true;
                    }
                    fichaParaJugar.intercambiarValores();
                    return ultimaFichaJugada.getValorCentro() == fichaParaJugar.getValorArriba();
                }
            }
        } else {
            if (fichaParaJugar instanceof FichaTriomino) {
                if (fichaParaJugar.isOrientacion() == FichaTriomino.ABAJO) {
                    fichaParaJugar.rotateRight();
                }
                for (int i = 0; i < 4; i++) {
                    if (ultimaFichaJugada.getValorAbajo() == fichaParaJugar.getValorCentro()) {
                        return true;
                    }
                    fichaParaJugar.rotateRight();
                    fichaParaJugar.rotateRight();
                }
            } else {
                if (ultimaFichaJugada.getValorAbajo() == fichaParaJugar.getValorArriba()) {
                    return true;
                }
                fichaParaJugar.intercambiarValores();
                return ultimaFichaJugada.getValorAbajo() == fichaParaJugar.getValorArriba();
            }
        }
        return false;
    }

    private void obtenerFichasDelPozo() {
        Jugador jugadorActual = grupoDeJugadores.getJugadorActual();
        repartirFichas(2, jugadorActual);
    }

    private void sumaPuntosFicha(FichaDomino ficha) {
        Jugador jugadorActual = grupoDeJugadores.getJugadorActual();
        int puntosASumar = ficha.sumarValores();
        int puntosActuales = grupoDeJugadores.getJugadorActual().getPuntuacion();
        jugadorActual.setPuntuacion(puntosActuales + puntosASumar);
    }

    private boolean seAcaboElJuego() {
        return seQuedoJugadorSinFicha() || hayJugadasDisponibles();
    }

    private boolean seQuedoJugadorSinFicha() {
        for (Jugador jugador : grupoDeJugadores.getJugadores()) {
            if (jugador.getBoncheDeFichas().getFichasJugables().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean hayJugadasDisponibles() {
        if (pozoDeJuego.getFichasDelPozo().isEmpty()) {
            for (Jugador jugador : grupoDeJugadores.getJugadores()) {
                if (tieneJugadasDisponibles(jugador)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void declararGanadorDelJuego() {
        int puntuacionMayor = 0;
        Jugador jugadorGanador = null;

        for (Jugador jugador : grupoDeJugadores.getJugadores()) {
            if (jugador.getPuntuacion() > puntuacionMayor) {
                puntuacionMayor = jugador.getPuntuacion();
                jugadorGanador = jugador;
            }

        }
        if (jugadorGanador == null) {
            grupoDeJugadores.setJugadorGanador(jugadorGanador);
            System.out.println("El ganador es " + grupoDeJugadores.getJugadorGanador() + " con: " + jugadorGanador.getPuntuacion());
        }   else {
                System.out.println("Hubo un empate");
            }


        System.exit(0);
    }


    private void repartirFichas(int cantidad, Jugador jugador) {
        for (int i = 0; i < cantidad; i++) {
            FichaDomino fichaParaDar = pozoDeJuego.retirarFichaAleatoria();
            jugador.getBoncheDeFichas().getFichasJugables().addFirst(fichaParaDar);
        }
    }
}