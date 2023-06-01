package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alquiler {

    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int PRECIO_DIA = 20;

    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;

    // CONSTRUCTORES
    public Alquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler) {
        setCliente(cliente);
        setVehiculo(turismo);
        setFechaAlquiler(fechaAlquiler);
    }

    public Alquiler(Alquiler alquiler) {
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
        }
        setCliente((alquiler.getCliente()));
        setVehiculo((alquiler.getVehiculo()));
        setFechaAlquiler(alquiler.getFechaAlquiler());
        if (alquiler.getFechaDevolucion() == null) {
            this.fechaDevolucion = null;
        } else {
            setFechaDevolucion(alquiler.getFechaDevolucion());
        }
    }

    // GETTERS
    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    // SETTERS

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
        }
        this.vehiculo = vehiculo;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        if (fechaAlquiler == null) {
            throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
        }
        if (fechaAlquiler.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
        }
        if (fechaAlquiler.isBefore(LocalDate.now()) || fechaAlquiler.isEqual(LocalDate.now())) {
            this.fechaAlquiler = fechaAlquiler;
        }
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        if (fechaDevolucion == null) {
            throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
        }
        if (fechaDevolucion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
        }
        if (fechaDevolucion.isBefore(this.fechaAlquiler) || fechaDevolucion == fechaAlquiler) {
            throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
        }
        this.fechaDevolucion = fechaDevolucion;
    }

    // MÉTODO devolver
    public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
        if (fechaDevolucion == null) {
            throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
        }
        if (this.fechaDevolucion != null) {
            throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
        }
        setFechaDevolucion(fechaDevolucion);
    }

    // MÉTODO getPrecio
    public int getPrecio() {
        int numDias = 0;
        try {
            numDias = Period.between(fechaAlquiler, fechaDevolucion).getDays();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int precio = (PRECIO_DIA + this.vehiculo.getFactorPrecio()) * numDias;
        return precio;
    }

    // MÉTODO hashCode & equals
    @Override
    public int hashCode() {
        return Objects.hash(cliente, fechaAlquiler, vehiculo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alquiler other = (Alquiler) obj;
        return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
                && Objects.equals(vehiculo, other.vehiculo);
    }

    // MÉTODO toString
    @Override
    public String toString() {
        return String.format("%s <---> %s, %s - %s (" + getPrecio() + "€)",
                getCliente(), getVehiculo(), getFechaAlquiler().format(FORMATO_FECHA),
                (getFechaDevolucion() == null) ? "Aún no devuelto" : fechaDevolucion.format(FORMATO_FECHA),
                (getFechaDevolucion() == null) ? LocalDate.now().format(FORMATO_FECHA) : "",
                getPrecio());
    }

}
