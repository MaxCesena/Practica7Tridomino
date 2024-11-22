public class FichaDomino implements Movible {
    public static final boolean HORIZONTAL = false;
    public static final boolean VERTICAL = true;
    protected int valorArriba;
    protected int valorAbajo;
    protected boolean orientacion = VERTICAL;

    public FichaDomino(int valorArriba, int valorAbajo) {
        this.valorArriba = valorArriba;
        this.valorAbajo = valorAbajo;
    }

    @Override
    public void rotateRight() {
        orientacion = !orientacion;
        if (orientacion == VERTICAL) {
            intercambiarValores();
        }
    }

    @Override
    public void rotateLeft() {
        orientacion = !orientacion;
        if (orientacion == HORIZONTAL) {
            intercambiarValores();
        }
    }


    public String mostrarFichaCompleta() {

        if (orientacion == VERTICAL) {
            return String.format(" ___\n| %d |\n ___\n| %d |\n ___", valorArriba, valorAbajo);
        } else {
            return String.format("[%d|%d]", valorArriba, valorAbajo);
        }


    }

    public void intercambiarValores() {
        int aux = valorArriba;
        valorArriba = valorAbajo;
        valorAbajo = aux;
    }

    public int sumarValores() {
        return valorArriba + valorAbajo;
    }

    public String toString() {
        return String.format("|%d|%d|", valorArriba, valorAbajo);
    }



    public int getValorArriba() {
        return valorArriba;
    }

    public int getValorAbajo() {
        return valorAbajo;
    }

    public int getValorCentro() {
        return 0;
    }

    public boolean isOrientacion() {
        return orientacion;
    }

    public void setOrientacion(boolean orientacion) {
        this.orientacion = orientacion;
    }
}