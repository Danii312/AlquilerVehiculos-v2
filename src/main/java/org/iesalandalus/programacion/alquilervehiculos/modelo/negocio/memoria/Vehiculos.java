package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vehiculos implements IVehiculos {

    private List<Vehiculo> coleccionVehiculos;

    // CONSTRUCTOR
    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    // MÉTODO get
    public List<Vehiculo> get() {
        ArrayList<Vehiculo> copiaVehiculos = new ArrayList<>(coleccionVehiculos);
        return copiaVehiculos;
    }

    // MÉTODO getCantidad
    public int getCantidad() {
        return coleccionVehiculos.size();
    }

    // MÉTODO insertar
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
        }
        if ((coleccionVehiculos.contains(vehiculo))) {
            throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    // MÉTODO buscar
    public Vehiculo buscar(Vehiculo vehiculo) {
        Vehiculo vehiculo2 = null;
        Iterator<Vehiculo> iterator = coleccionVehiculos.iterator();
        while (iterator.hasNext()) {
            vehiculo2 = iterator.next();
            if (vehiculo2.getMatricula().equals(vehiculo.getMatricula())) {
                return vehiculo2;
            }
        }
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
        }
        return null;
    }

    // MÉTODO borrar
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
        }
        if (coleccionVehiculos.contains(vehiculo)) {
            coleccionVehiculos.remove(vehiculo);
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
        }
    }

}
