package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class VistaTexto extends Vista {

    public void comenzar() {
        Accion accion = null;
        do {
            while (accion == null) {
                Consola.mostrarMenuAcciones();
                try {
                    accion = Consola.elegirAccion();
                } catch (OperationNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
                if (accion != null) {
                    switch (accion) {
                        case INSERTAR_CLIENTE -> {
                            insertarCliente();
                            accion = null;
                        }
                        case INSERTAR_TURISMO -> {
                            insertarVehiculo();
                            accion = null;
                        }
                        case INSERTAR_ALQUILER -> {
                            insertarAlquiler();
                            accion = null;
                        }
                        case BUSCAR_CLIENTE -> {
                            buscarCliente();
                            accion = null;
                        }
                        case BUSCAR_TURISMO -> {
                            buscarVehiculo();
                            accion = null;
                        }
                        case BUSCAR_ALQUILER -> {
                            buscarAlquiler();
                            accion = null;
                        }
                        case MODIFICAR_CLIENTE -> {
                            modificarCliente();
                            accion = null;
                        }
                        case DEVOLVER_ALQUILER -> {
                            devolverAlquiler();
                            accion = null;
                        }
                        case BORRAR_CLIENTE -> {
                            borrarCliente();
                            accion = null;
                        }
                        case BORRAR_TURISMO -> {
                            borrarVehiculo();
                            accion = null;
                        }
                        case BORRAR_ALQUILER -> {
                            borrarAlquiler();
                            accion = null;
                        }
                        case LISTAR_CLIENTES -> {
                            listarCliente();
                            accion = null;
                        }
                        case LISTAR_TURISMOS -> {
                            listarVehiculos();
                            accion = null;
                        }
                        case LISTAR_ALQUILERES -> {
                            listarAlquiler();
                            accion = null;
                        }
                        case LISTAR_ALQUILERES_CLIENTES -> {
                            listarAlquileresCliente();
                            accion = null;
                        }
                        case LISTAR_ALQUILERES_TURISMO -> {
                            listarAlquileresVehiculo();
                            accion = null;
                        }
                        default -> accion = Accion.SALIR;
                    }
                }
            }
        } while (accion != Accion.SALIR);
        terminar();
    }

    public void terminar() {
        System.out.println("Fin de la ejecución");
    }

    protected void insertarCliente() {
        Consola.mostrarCabecera("Insertar cliente");
        Cliente cliente = Consola.leerCliente();
        try {
            controlador.insertar(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void insertarVehiculo() {
        Consola.mostrarCabecera("Insertar vehiculo");
        Vehiculo vehiculo = Consola.leerVehiculo();
        try {
            controlador.insertar(vehiculo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void insertarAlquiler() {
        Consola.mostrarCabecera("Insertar alquiler");
        Alquiler alquiler = Consola.leerAlquiler();
        try {
            controlador.insertar(alquiler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        Cliente cliente = Consola.leerClienteDni();
        try {
            cliente = controlador.buscar(cliente);
            System.out.println(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehiculo");
        Vehiculo vehiculo = Consola.leerVehiculoMatricula();
        try {
            vehiculo = controlador.buscar(vehiculo);
            System.out.println(vehiculo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void buscarAlquiler() {
        Consola.mostrarCabecera("Buscar alquiler");
        Alquiler alquiler = Consola.leerAlquiler();
        try {
            alquiler = controlador.buscar(alquiler);
            System.out.println(alquiler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void modificarCliente() {
        Consola.mostrarCabecera("Modificar cliente");
        Cliente cliente = Consola.leerClienteDni();
        String nombre = Consola.leerNombre();
        String telefono = Consola.leerTelefono();
        try {
            controlador.modificar(cliente, nombre, telefono);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void devolverAlquiler() {
        Consola.mostrarCabecera("Devolver alquiler");
        Alquiler alquiler = Consola.leerAlquiler();
        LocalDate fechaDevolucion = Consola.leerFechaDevolucion();
        try {
            controlador.devolver(alquiler, fechaDevolucion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        Cliente cliente = Consola.leerClienteDni();
        try {
            controlador.borrar(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar vehiculo");
        Vehiculo vehiculo = Consola.leerVehiculoMatricula();
        try {
            controlador.borrar(vehiculo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void borrarAlquiler() {
        Consola.mostrarCabecera("Borrar alquiler");
        Alquiler alquiler = Consola.leerAlquiler();
        try {
            controlador.borrar(alquiler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void listarCliente() {
        Consola.mostrarCabecera("Listar clientes");
        List<Cliente> clientes = controlador.getClientes();
        Iterator<Cliente> iteratorClientes = clientes.iterator();
        while (iteratorClientes.hasNext()) {
            Cliente cliente = iteratorClientes.next();
            System.out.println(cliente);
        }
    }

    protected void listarVehiculos() {
        Consola.mostrarCabecera("Listar todos los vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        Iterator<Vehiculo> iteratorVehiculos = vehiculos.iterator();
        while (iteratorVehiculos.hasNext()) {
            Vehiculo vehiculo = iteratorVehiculos.next();
            System.out.println(vehiculo);
        }
    }

    protected void listarAlquiler() {
        Consola.mostrarCabecera("Listar alquileres");
        List<Alquiler> alquileres = controlador.getAlquileres();
        Iterator<Alquiler> iteratorAlquileres = alquileres.iterator();
        while (iteratorAlquileres.hasNext()) {
            Alquiler alquiler = iteratorAlquileres.next();
            System.out.println(alquiler);
        }
    }

    protected void listarAlquileresCliente() {
        Consola.mostrarCabecera("Listar alquileres de un cliente");
        Cliente cliente = Consola.leerClienteDni();
        List<Alquiler> alquileres = controlador.getAlquileres(cliente);
        Iterator<Alquiler> iteratorAlquileres = alquileres.iterator();
        while (iteratorAlquileres.hasNext()) {
            Alquiler alquiler = iteratorAlquileres.next();
            System.out.println(alquiler);
        }
    }

    protected void listarAlquileresVehiculo() {
        Consola.mostrarCabecera("Listar alquileres de un vehículo");
        Vehiculo vehiculo = Consola.leerVehiculoMatricula();
        List<Alquiler> alquileres = controlador.getAlquileres(vehiculo);
        Iterator<Alquiler> iteratorAlquileres = alquileres.iterator();
        while (iteratorAlquileres.hasNext()) {
            Alquiler alquiler = iteratorAlquileres.next();
            System.out.println(alquiler);
        }
    }

}
