package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Autobus extends Vehiculo {

    private static final int FACTOR_PLAZAS = 2;
    private int plazas;

    // CONSTRUCTORES
    public Autobus(String marca, String modelo, int plazas, String matricula) {
        super(marca, modelo, matricula);
        setPlazas(plazas);
    }

    public Autobus(Autobus autobus) {
        super(autobus);
        setPlazas(autobus.getPlazas());
    }

    // MÉTODOS Plazas
    public int getPlazas() {
        return plazas;
    }

    private void setPlazas(int plazas) {
        if (plazas < 0) {
            throw new IllegalArgumentException("ERROR: Las palzas no pueden ser negativas.");
        }
        this.plazas = plazas;
    }

    @Override
    public Autobus getVehiculoConMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
        }
        return new Autobus("Seat", "Leon", 8, matricula);
    }

    @Override
    protected int getFactorPrecio() {
        return plazas * FACTOR_PLAZAS;
    }

    @Override
    public String toString() {
        return (String.format("%s  (%s plazas) (%sCV) - %s", super.getMarca(), getPlazas(), super.getModelo(), super.getMatricula(), "disponible"));
    }

}
