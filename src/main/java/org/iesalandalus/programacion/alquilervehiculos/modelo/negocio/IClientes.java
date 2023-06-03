package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import java.util.List;

public interface IClientes {

    List<Cliente> get();

    int getCantidad();

    void insertar(Cliente cliente) throws Exception;

    Cliente buscar(Cliente cliente);

    void borrar(Cliente cliente) throws Exception;

    void modificar(Cliente cliente, String nombre, String telefono) throws Exception;

}
