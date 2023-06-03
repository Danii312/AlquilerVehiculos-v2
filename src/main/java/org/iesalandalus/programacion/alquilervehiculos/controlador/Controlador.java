package org.iesalandalus.programacion.alquilervehiculos.controlador;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public class Controlador {

    private Vista vistaTexto;
    private Modelo modeloCascada;

    public Controlador(Modelo modeloCascada, Vista vistaTexto) {
        if (modeloCascada != null && vistaTexto != null) {
            this.modeloCascada = modeloCascada;
            this.vistaTexto = vistaTexto;
        }
        assert vistaTexto != null;
        vistaTexto.setControlador(this);
    }

    public void comenzar() {
        modeloCascada.comenzar();
        vistaTexto.comenzar();
    }

    public void terminar() {
        modeloCascada.terminar();
        vistaTexto.terminar();
    }

    public void insertar(Cliente cliente) throws Exception {
        modeloCascada.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        modeloCascada.insertar(vehiculo);
    }

    public void insertar(Alquiler alquiler) throws Exception {
        modeloCascada.insertar(alquiler);
    }

    public Cliente buscar(Cliente cliente) throws OperationNotSupportedException {
        return modeloCascada.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) throws OperationNotSupportedException {
        return modeloCascada.buscar(vehiculo);
    }

    public Alquiler buscar(Alquiler alquiler) throws OperationNotSupportedException {
        return modeloCascada.buscar(alquiler);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws Exception {
        modeloCascada.modificar(cliente, nombre, telefono);
    }

    public void devolver(Cliente cliente, LocalDate fechaDevolucion) {
        try {
            modeloCascada.devolver(cliente, fechaDevolucion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) {
        try {
            modeloCascada.devolver(vehiculo, fechaDevolucion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrar(Cliente cliente) throws Exception {
        modeloCascada.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        modeloCascada.borrar(vehiculo);
    }

    public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
        modeloCascada.borrar(alquiler);
    }

    public List<Cliente> getClientes() {
        return modeloCascada.getClientes();
    }

    public List<Vehiculo> getVehiculos() {
        return modeloCascada.getVehiculos();
    }

    public List<Alquiler> getAlquileres() {
        return modeloCascada.getAlquileres();
    }

    public List<Alquiler> getAlquileres(Cliente cliente) {
        return modeloCascada.getAlquileres(cliente);
    }

    public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
        return modeloCascada.getAlquileres(vehiculo);
    }

}
