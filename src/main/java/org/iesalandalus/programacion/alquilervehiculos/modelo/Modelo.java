package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public abstract class Modelo {

    protected IAlquileres alquileres;
    protected IClientes clientes;
    protected IVehiculos vehiculos;
    protected IFuenteDatos fuenteDatos;

    protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }

    public Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {
        setFuenteDatos(factoriaFuenteDatos.crear());

    }

    public void comenzar() {
        alquileres = fuenteDatos.crearAlquileres();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public abstract void insertar(Cliente cliente) throws OperationNotSupportedException, Exception;

    public abstract void insertar(Vehiculo turismo) throws OperationNotSupportedException;

    public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException, Exception;

    public abstract Cliente buscar(Cliente cliente);

    public abstract Vehiculo buscar(Vehiculo vehiculo);

    public abstract Alquiler buscar(Alquiler alquiler);

    public abstract void modificar(Cliente cliente, String nombre, String telefono)
            throws OperationNotSupportedException, Exception;

    public abstract void borrar(Cliente cliente) throws OperationNotSupportedException, Exception;

    public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

    public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;

    public abstract List<Cliente> getClientes();

    public abstract List<Vehiculo> getVehiculos();

    public abstract List<Alquiler> getAlquileres();

    public abstract List<Alquiler> getAlquileres(Cliente cliente);

    public abstract List<Alquiler> getAlquileres(Vehiculo vehiculo);

    public abstract void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;

    public abstract void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;

}
