package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    protected static final String PATRON_FECHA = "dd/MM/yyyy";
    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

    private Consola() {

    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println("---------------------------------------");
        System.out.println(mensaje);
    }

    public static void mostrarMenuAcciones() {
        mostrarCabecera("Gestión de reservas de vehículos");
        System.out.println("");
        for (Accion accion : Accion.values()) {
            System.out.print(accion.ordinal());
            System.out.print("- ");
            System.out.println(accion);
        }
    }

    public static Accion elegirAccion() throws OperationNotSupportedException {
        try {
            Accion opcion = null;
            while (opcion == null) {
                int ordinal = leerEntero("Elige una opción:");
                opcion = Accion.values()[ordinal];
            }
            return opcion;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    private static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        String cadena = "";
        while (cadena == "" || cadena == null || cadena == " " || cadena.trim().isEmpty()) {
            cadena = Entrada.cadena();
        }
        return cadena;
    }

    private static Integer leerEntero(String mensaje) {
        System.out.println(mensaje);
        Integer num = -1;
        while (num < 0) {
            num = Entrada.entero();
        }
        return num;
    }

    private static LocalDate leerFecha(String mensaje) {
        System.out.println(mensaje);
        LocalDate fecha = null;
        while (fecha == null) {
            try {
                LocalDate localDate = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
                fecha = localDate;
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        return fecha;
    }

    public static Cliente leerCliente() {
        Cliente cliente = null;
        String nombre = "Daniel Mora Faba";
        String telefono = "601336891";
        String dni;
        do {
            nombre = leerNombre();
            dni = leerCadena("Introduce el dni:");
            telefono = leerTelefono();
            try {
                Cliente leerCliente = new Cliente(nombre, dni, telefono);
                return leerCliente;
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        } while (cliente == null);
        return cliente;
    }

    public static Cliente leerClienteDni() {
        Cliente cliente = null;
        String nombre = "Daniel Mora";
        String telefono = "601336891";
        String dni;
        do {
            dni = leerCadena("Introduce el dni:");
            try {
                Cliente leerCliente = new Cliente(nombre, dni, telefono);
                cliente = leerCliente;
                return leerCliente;
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        } while (cliente == null);
        return cliente;
    }

    public static String leerNombre() {
        String nombre;
        do {
            System.out.print("Introduce el nombre:");
            nombre = Entrada.cadena();
        }
        while (nombre.equals(""));
        return nombre;
    }

    public static String leerTelefono() {
        String telefono;
        do {
            System.out.print("Introduce el teléfono:");
            telefono = Entrada.cadena();
        }
        while (telefono.equals(""));
        return telefono;
    }

    public static Vehiculo leerVehiculo() {
        Vehiculo vehiculo;
        mostrarMenuTiposVehiculos();
        vehiculo = leerVehiculo(elegirTipoVehiculo());
        return vehiculo;
    }

    private static void mostrarMenuTiposVehiculos() {
        mostrarCabecera("Estos son los tipos de vehículos a escoger:");
        System.out.println("");
        for (tipoVehiculo tipovehiculo : tipoVehiculo.values()) {
            System.out.print(tipovehiculo.ordinal());
            System.out.print("- ");
            System.out.println(tipovehiculo);
        }
    }

    private static tipoVehiculo elegirTipoVehiculo() {
        try {
            tipoVehiculo estadoVehiculo = null;
            while (estadoVehiculo == null) {
                int ordinal = leerEntero("Introduce el número del vehiculo a insertar:");
                estadoVehiculo = tipoVehiculo.values()[ordinal];
            }
            return estadoVehiculo;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    private static Vehiculo leerVehiculo(tipoVehiculo tipovehiculo) {
        mostrarMenuTiposVehiculos();
        String marca;
        String modelo;
        int cilindrada = -1;
        int plazas = -1;
        int pma = -1;
        String matricula;
        Vehiculo vehiculo = null;
        do {
            do {
                System.out.print("Introduce la marca:");
                marca = Entrada.cadena();
            }
            while (marca.equals(""));
            do {
                System.out.print("Introduce el modelo:");
                modelo = Entrada.cadena();
            }
            while (modelo.equals(""));
            do {
                System.out.print("Introduce la matrícula (formato 1111BBB):");
                matricula = Entrada.cadena();
            }
            while (matricula.equals(""));
            switch (tipovehiculo) {
                case TURISMO -> {
                    do {
                        System.out.print("Introduce la cilindrada:");
                        cilindrada = Entrada.entero();
                    }
                    while (cilindrada <= 0);
                    try {
                        vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
                        return vehiculo;
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
                case AUTOBUS -> {
                    do {
                        System.out.print("Introduce las plazas:");
                        plazas = Entrada.entero();
                    }
                    while (plazas <= 0);
                    try {
                        vehiculo = new Autobus(marca, modelo, plazas, matricula);
                        return vehiculo;
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
                case FURGONETA -> {
                    do {
                        System.out.print("Introduce las plazas:");
                        plazas = Entrada.entero();
                    }
                    while (plazas <= 0);
                    do {
                        System.out.print("Introduce el pma:");
                        pma = Entrada.entero();
                    }
                    while (pma <= 0 || pma > 4300);
                    try {
                        vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
                        return vehiculo;
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
                default -> {
                }
            }
        } while (vehiculo == null);
        return vehiculo;
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduce la matrícula (formato 1111BBB):");
        Vehiculo vehiculo = new Furgoneta("Seat", "Leon", 10, 4, matricula);
        return vehiculo.getVehiculoConMatricula(matricula);
    }

    public static Alquiler leerAlquiler() {
        Cliente cliente;
        Vehiculo vehiculo;
        LocalDate fechaDate;
        Alquiler alquiler = null;
        do {
            cliente = leerClienteDni();
            vehiculo = leerVehiculoMatricula();
            fechaDate = leerFecha("Introduce la fecha de alquiler (formato dd/mm/aaaa):");
            try {
                Alquiler leerAlquiler = new Alquiler(cliente, vehiculo, fechaDate);
                alquiler = leerAlquiler;
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        while (alquiler == null);
        return alquiler;
    }

    public static LocalDate leerFechaDevolucion() {
        String fecha;
        boolean estadoDevolucion = false;
        do {
            System.out.print("Introduce la fecha (formato dd/mm/aaaa): ");
            fecha = Entrada.cadena();
            try {
                LocalDate.parse(fecha, FORMATO_FECHA);
                estadoDevolucion = true;
            } catch (DateTimeParseException e) {
                estadoDevolucion = false;
            }
        } while (!estadoDevolucion);
        return LocalDate.parse(fecha, FORMATO_FECHA);
    }

    static Integer leerMes(String mensaje) {
        System.out.println(mensaje);
        Integer mes = null;
        while (mes == null || (mes < 0 || mes > 12)) {
            try {
                mes = Entrada.entero();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        return mes;
    }

}
