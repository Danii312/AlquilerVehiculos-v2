package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Cliente {

    private final static String ER_NOMBRE = "^([A-Z][a-z]+[ ]?){1,2}$";
    private final static String ER_DNI = "\\d{8}[A-Za-z]";
    private final static String ER_TELEFONO = "[96]\\d{8}";
    private String nombre;
    private String dni;
    private String telefono;

    // CONSTRUCTORES
    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
        }
        setNombre(cliente.getNombre());
        setDni(cliente.getDni());
        setTelefono(cliente.getTelefono());
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    // SETTERS
    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
        }
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
        }
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    // MÉTODO comprobarLetraDni
    private static boolean comprobarLetraDni(String dni) {
        char[] letrasDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int numDni = Integer.parseInt(dni.substring(0, 8));
        char letraDni = dni.substring(8).charAt(0);
        return letrasDni[numDni % 23] == letraDni ? true : false;
    }

    // MÉTODO getClienteConDni
    public static Cliente getClienteConDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
        }
        Cliente cliente = new Cliente("Bob Esponja", dni, "950112233");
        return cliente;
    }

    // MÉTODO hashCode y equals
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(dni, other.dni);
    }

    // MÉTODO toString
    @Override
    public String toString() {
        return String.format("%s - %s (%s)", getNombre(), getDni(), getTelefono());
    }
}
