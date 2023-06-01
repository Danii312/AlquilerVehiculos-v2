package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IVehiculos {

    List<Vehiculo> get();

    int getCantidad();

    void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

    Vehiculo buscar(Vehiculo vehiculo);

    void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

}
