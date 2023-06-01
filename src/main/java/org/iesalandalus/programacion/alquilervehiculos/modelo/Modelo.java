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
import java.util.ArrayList;
import java.util.List;

public abstract class Modelo {

    protected IAlquileres alquileres;
    protected IClientes clientes;
    protected IVehiculos vehiculos;
    protected IFuenteDatos fuenteDatos;

    protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }

    public Modelo() {
        super();
    }

    public void comenzar() {
        alquileres = fuenteDatos.crearAlquileres();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws Exception {
        Cliente cliente2 = new Cliente(cliente);
        clientes.insertar(cliente2);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Alquiler alquiler) throws Exception {
        if (clientes.buscar(alquiler.getCliente()) == null || vehiculos.buscar(alquiler.getVehiculo()) == null) {
            throw new OperationNotSupportedException("ERROR: No se encuentra el cliente o vehiculo");
        } else {
            Alquiler alquiler2 = new Alquiler(clientes.buscar(alquiler.getCliente()), vehiculos.buscar(alquiler.getVehiculo()), alquiler.getFechaAlquiler());
            alquileres.insertar(alquiler2);
        }
    }

    public Cliente buscar(Cliente cliente) {
        Cliente cliente2 = new Cliente(clientes.buscar(cliente));
        return cliente2;
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Vehiculo vehiculo2 = (vehiculos.buscar(vehiculo));
        return vehiculo2;
    }

    public Alquiler buscar(Alquiler alquiler) {
        Alquiler alquiler2 = new Alquiler(alquileres.buscar(alquiler));
        return alquiler2;
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws Exception {
        clientes.modificar(cliente, nombre, telefono);
    }

    public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws NullPointerException, OperationNotSupportedException {
        if (alquileres.buscar(alquiler) == null) {
            throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
        }
        alquileres.devolver(alquiler, fechaDevolucion);
    }

    public void borrar(Cliente cliente) throws Exception {
        for (Alquiler alquiler2 : getAlquileres(cliente)) {
            if (alquiler2.getCliente().equals(cliente)) {
                alquileres.borrar(alquiler2);
            }
        }
        for (Cliente cliente2 : getClientes()) {
            if (cliente2.equals(cliente)) {
                clientes.borrar(cliente2);
            }
        }
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Alquiler alquiler2 : getAlquileres(vehiculo)) {
            if (alquiler2.getVehiculo().equals(vehiculo)) {
                alquileres.borrar(alquiler2);
            }
        }
        for (Vehiculo vehiculo2 : getVehiculos()) {
            if (vehiculo2.equals(vehiculo)) {
                vehiculos.borrar(vehiculo);
            }
        }
    }

    public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
        alquileres.borrar(alquiler);
    }

    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            if (cliente != null) {
                listaClientes.add(new Cliente(cliente));
            }
        }
        return listaClientes;
    }

    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos.get()) {
            if (vehiculo != null) {
                try {
                    listaVehiculos.add(vehiculo.copiar(vehiculo));
                } catch (OperationNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return listaVehiculos;
    }

    public List<Alquiler> getAlquileres() {
        List<Alquiler> listaAlquileres = new ArrayList<>();
        for (Alquiler alquiler : alquileres.get()) {
            if (alquiler != null) {
                listaAlquileres.add(new Alquiler(alquiler));
            }
        }
        return listaAlquileres;
    }

    public List<Alquiler> getAlquileres(Cliente cliente) {
        List<Alquiler> listaAlquileres = new ArrayList<>();
        for (Alquiler alquiler : alquileres.get(cliente)) {
            if (alquiler != null) {
                listaAlquileres.add(new Alquiler(alquiler));
            }
        }
        return listaAlquileres;
    }

    public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
        List<Alquiler> listaAlquileres = new ArrayList<>();
        for (Alquiler alquiler : alquileres.get(vehiculo)) {
            if (alquiler != null) {
                listaAlquileres.add(new Alquiler(alquiler));
            }
        }
        return listaAlquileres;
    }

}
