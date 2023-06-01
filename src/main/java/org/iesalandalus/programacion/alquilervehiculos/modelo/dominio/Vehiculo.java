package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public abstract class Vehiculo {

    private static final String ER_MARCA = "Seat|Land Rover|KIA|Rolls-Royce|SsangYong";
    private static final String ER_MATRICULA = "^[0-9]{4}[B-Z]{3}$";
    private String marca;
    private String modelo;
    private String matricula;

    // CONSTRUCTORES
    protected Vehiculo(String marca, String modelo, String matricula) {
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
    }

    protected Vehiculo(Vehiculo turismo) {
        if (turismo == null) {
            throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
        }
        setMarca(turismo.getMarca());
        setModelo(turismo.getModelo());
        setMatricula(turismo.getMatricula());
    }

    // MÉTODO copiar
    public Vehiculo copiar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo instanceof Turismo) {
            return new Turismo((Turismo) vehiculo);
        } else if (vehiculo instanceof Autobus) {
            return new Autobus((Autobus) vehiculo);
        } else if (vehiculo instanceof Furgoneta) {
            return new Furgoneta((Furgoneta) vehiculo);
        } else throw new OperationNotSupportedException("ERROR; El tipo de vehículo no se encuentra en nuestra lista");
    }

    // MÉTODO getVehiculoConMatricula
    public abstract Vehiculo getVehiculoConMatricula(String matricula);

    // MÉTODO getFactorPrecio
    protected abstract int getFactorPrecio();

    // GETTERS
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    //SETTERS
    protected void setMarca(String marca) {
        if (marca == null) {
            throw new NullPointerException("ERROR: La marca no puede ser nula.");
        }
        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
        } else {
            this.marca = marca;
        }
    }

    protected void setModelo(String modelo) {
        if (modelo == null) {
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        }
        if (modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
        } else {
            this.modelo = modelo;
        }
    }

    protected void setMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
        }
        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
        } else {
            this.matricula = matricula;
        }
    }

    // MÉTODO hashCode & equals
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Vehiculo) {
            Vehiculo other = (Vehiculo) obj;
            return Objects.equals(matricula, other.matricula);
        }
        return false;
    }

    // MÉTODO toString
    @Override
    public String toString() {
        return String.format("%s %s (%sCV) - %s", getMarca(), getModelo(), getMatricula(), "disponible");
    }


}
