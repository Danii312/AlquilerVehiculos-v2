package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Clientes implements IClientes {

    private List<Cliente> coleccionClientes;

    // CONSTRUCTOR
    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    // MÉTODO get
    public List<Cliente> get() {
        ArrayList<Cliente> copiaClientes = new ArrayList<>(coleccionClientes);
        return copiaClientes;
    }

    // MÉTODO getCantidad
    public int getCantidad() {
        return coleccionClientes.size();
    }

    // MÉTODO insertar
    public void insertar(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    // MÉTODO buscar
    public Cliente buscar(Cliente cliente) {
        Cliente cliente2 = null;
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
        }
        Iterator<Cliente> iterator = coleccionClientes.iterator();
        while (iterator.hasNext()) {
            cliente2 = iterator.next();
            if (cliente2.getDni().equals(cliente.getDni())) {
                return cliente2;
            }
        }
        return null;
    }

    // MÉTODO borrar
    public void borrar(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            coleccionClientes.remove(coleccionClientes.indexOf(cliente));
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
        }
    }

    // MÉTODO modificar
    public void modificar(Cliente cliente, String nombre, String telefono) throws Exception {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            cliente.setNombre(nombre);
        }
        if (telefono != null && !telefono.trim().isEmpty()) {
            cliente.setTelefono(telefono);
        }
        if (!coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
        }

    }

}


