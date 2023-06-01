package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Furgoneta extends Vehiculo {

    private static final int FACTOR_PMA = 100;
    private static final int FACTOR_PLAZAS = 1;
    private int pma;
    private int plazas;

    // CONSTRUCTORES
    public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
        super(marca, modelo, matricula);
        setPlazas(plazas);
        setPma(pma);
    }

    public Furgoneta(Furgoneta furgoneta) {
        super(furgoneta);
        setPlazas(furgoneta.getPlazas());
        setPma(furgoneta.getPma());
    }

    // MÉTODOS Pma
    public int getPma() {
        return pma;
    }

    private void setPma(int pma) {
        this.pma = pma;
    }

    // MÉTODOS Plazas
    public int getPlazas() {
        return plazas;
    }

    private void setPlazas(int plazas) {
        if (plazas < 0) {
            throw new IllegalArgumentException("ERROR: Las plazas no pueden ser negativas.");
        }
        this.plazas = plazas;
    }

    @Override
    public Furgoneta getVehiculoConMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
        }
        return new Furgoneta("Seat", "Leon", 1000, 2, matricula);
    }

    @Override
    protected int getFactorPrecio() {
        return ((pma / FACTOR_PMA) + (plazas * FACTOR_PLAZAS));
    }

    @Override
    public String toString() {
        return (String.format("%s (%s Pma) (%s plazas) (%sCV) - %s", super.getMarca(), getPma(),getPlazas(), super.getModelo(),
                super.getMatricula(), "disponible"));
    }

}
