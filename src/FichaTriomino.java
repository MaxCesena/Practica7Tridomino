public class FichaTriomino extends FichaDomino implements Movible {
    public static final boolean ARRIBA = false;
    public static final boolean ABAJO = true;
    private int valorIzquierdo;
    private int valorDerecho;
    private int valorCentro;
    private boolean orientacion = ARRIBA;

    public FichaTriomino(int valorIzquierdo, int valorCentro, int valorDerecho) {
        super(valorIzquierdo, valorDerecho);
        this.valorIzquierdo = valorIzquierdo;
        this.valorCentro = valorCentro;
        this.valorDerecho = valorDerecho;
        super.setOrientacion(this.orientacion);
    }

    public boolean isIguales() {
        return getValorIzquierdo() == getValorDerecho() &&
                getValorDerecho() == getValorCentro();
    }

    public void ajustarOrientacion() {
        if (isIguales()) {
            rotateRight();
        }
    }



    @Override
    public void rotateRight() {
        orientacion = !orientacion;
        super.setOrientacion(this.orientacion);
        if (orientacion == ABAJO) {
            int aux = valorDerecho;
            valorDerecho = valorCentro;
            valorCentro = aux;
        } else {
            int aux = valorCentro;
            valorCentro = valorIzquierdo;
            valorIzquierdo = aux;
        }
    }

    @Override
    public void rotateLeft() {
        orientacion = !orientacion;
        if (orientacion == ARRIBA) {
            int aux = valorIzquierdo;
            valorIzquierdo = valorCentro;
            valorCentro = aux;
        }
    }

    public int getValorIzquierdo() {
        return valorIzquierdo;
    }

    public int getValorDerecho() {
        return valorDerecho;
    }

    public int getValorCentro() {
        return valorCentro;
    }

    public int sumarValores() {
        return valorIzquierdo + valorCentro + valorDerecho;
    }

    public String imprimirParteFicha(int mediaParte) {
        switch (mediaParte) {
            case 0:
                if (orientacion == ARRIBA) {
                    return String.format("*/%d\\*", valorCentro);
                } else if (orientacion == ABAJO) {
                    return String.format("\\%d|%d/", valorIzquierdo, valorDerecho);
                }
                break;
            case 1:
                if (orientacion == ARRIBA) {
                    return String.format("/%d|%d\\", valorIzquierdo, valorDerecho);
                } else if (orientacion == ABAJO) {
                    return String.format("-\\%d/-", valorCentro);
                }
                break;
        }
        return "ERROR";
    }

    @Override
    public String mostrarFichaCompleta() {

            return String.format("%s\n%s", imprimirParteFicha(0), imprimirParteFicha(1));

    }

    @Override
    public String toString() {
        return String.format("|%d| |%d| |%d|", valorCentro, valorIzquierdo, valorDerecho);
    }
}