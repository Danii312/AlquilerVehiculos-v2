package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Turismo extends Vehiculo {

    private static final int FACTOR_CILINDRADA = 10;
    private int cilindrada;

    // CONSTRUCTORES
    public Turismo(String marca, String modelo, int cilindrada, String matricula) {
        super(marca, modelo, matricula);
        setCilindrada(cilindrada);
    }

    public Turismo(Turismo turismo) {
        super(turismo);
        setCilindrada(turismo.getCilindrada());
    }

    // MÉTODOS Cilindrada
    public int getCilindrada() {
        return cilindrada;
    }

    private void setCilindrada(int cilindrada) {
        if ((cilindrada <= 0) || (cilindrada > 5000)) {
            throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public Vehiculo getVehiculoConMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
        }
        return new Turismo("Seat", "Leon", 90, matricula);
    }

    @Override
    protected int getFactorPrecio() {
        return cilindrada / FACTOR_CILINDRADA;
    }

    @Override
    public String toString() {
        return (String.format("%s %s (%sCV) - %s", super.getMarca(), getCilindrada(), super.getModelo(), super.getMatricula(), "disponible"));
    }

}
