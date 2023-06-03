package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public interface IAlquileres {

    List<Alquiler> get();

    List<Alquiler> get(Cliente cliente);

    List<Alquiler> get(Vehiculo vehiculo);

    int getCantidad();

    void insertar(Alquiler alquiler) throws Exception;

    void devolver(Cliente alquiler, LocalDate fechaDevolucion)
            throws NullPointerException, OperationNotSupportedException;

    Alquiler buscar(Alquiler alquiler);

    void borrar(Alquiler alquiler) throws OperationNotSupportedException;

}
