package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeloCascada extends Modelo {

    public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos){
        super(factoriaFuenteDatos);
        comenzar();
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
        Cliente listaClientes = new Cliente(clientes.buscar(cliente));
        return listaClientes;
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Vehiculo listaVehiculos = (vehiculos.buscar(vehiculo));
        return listaVehiculos;
    }

    public Alquiler buscar(Alquiler alquiler) {
        Alquiler listaAlquileres = new Alquiler(alquileres.buscar(alquiler));
        return listaAlquileres;
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws Exception {
        clientes.modificar(cliente, nombre, telefono);
    }

    public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws NullPointerException, OperationNotSupportedException {
        alquileres.devolver(cliente, fechaDevolucion);
    }

    public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws NullPointerException, OperationNotSupportedException {
        alquileres.devolver(vehiculo, fechaDevolucion);
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
