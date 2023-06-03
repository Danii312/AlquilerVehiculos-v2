package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.*;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Alquileres {

    private final String RUTA_FICHERO = "alquileres.xml";
    private final String FORMATO_FECHA = "dd/MM/yyyy";
    private final String RAIZ = "alquileres";
    private final String DNI_CLIENTE = "dni";
    private final String MATRICULA_VEHICULO = "matricula";
    private final String FECHA_ALQUILER = "fechaAlquiler";
    private final String FECHA_DEVOLUCION = "fechaDevolución";
    private final String FORMATO = "formato";
    private final String TIPO_DATO = "tipodato";

    private List<Alquiler> coleccionAlquileres;
    private static Alquileres instancia = new Alquileres();

    private Alquileres() {

    }

    public static Alquileres getInstancia() {
        return instancia;
    }

    public void comenzar() {
        try {
            coleccionAlquileres = new LinkedList<>();
            leerXml();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void leerXml() throws IOException {
        File file = new File(RUTA_FICHERO);
        if (!file.exists()) {
            throw new FileNotFoundException("ERROR: El archivo que intenta leer no existe");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("ERROR: El archivo que intenta es incorrecto");
        }
        if (!file.canRead()) {
            throw new IOException("ERROR: no se puede leer el archivo.");
        }

        Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
        Element listaAlquileres = DOM.getDocumentElement();
        NodeList listaNodos = listaAlquileres.getChildNodes();

        for (int i = 0; i < listaNodos.getLength(); i++) {
            Node nodo = listaNodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Alquiler alquiler = elementToAlquiler((Element) nodo);
                try {
                    insertar(alquiler);
                } catch (OperationNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Alquiler elementToAlquiler(Element element) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        Cliente cliente = null;
        Vehiculo vehiculo = null;
        Element alquilerDOM = element;
        String matriculaAtributo = alquilerDOM.getAttribute(MATRICULA_VEHICULO);
        String dniAtributo = alquilerDOM.getAttribute(DNI_CLIENTE);
        NodeList fechaAlquiler = alquilerDOM.getElementsByTagName(FECHA_ALQUILER);
        NodeList fechaDevolucion = alquilerDOM.getElementsByTagName(FECHA_DEVOLUCION);
        List<Cliente> listaClientes = Clientes.getInstancia().get();
        Iterator<Cliente> clienteIterador = listaClientes.iterator();
        while (clienteIterador.hasNext()) {
            Cliente clienteI = clienteIterador.next();
            if (clienteI.getDni().equalsIgnoreCase(dniAtributo)) {
                cliente = clienteI;
                break;
            }
        }
        List<Vehiculo> listaVehiculos = Vehiculos.getInstancia().get();
        Iterator<Vehiculo> vehiculoIterador = listaVehiculos.iterator();
        while (vehiculoIterador.hasNext()) {
            Vehiculo vehiculoI = vehiculoIterador.next();
            if (vehiculoI.getMatricula().equalsIgnoreCase(matriculaAtributo)) {
                vehiculo = vehiculoI;
                break;
            }
        }
        Alquiler alquiler = new Alquiler(cliente, vehiculo, LocalDate.parse(fechaAlquiler.item(0).getTextContent(), formatoFecha));
        if (fechaDevolucion.item(0).getTextContent() != null && !fechaDevolucion.item(0).getTextContent().isEmpty()) {
            try {
                alquiler.devolver(LocalDate.parse(fechaDevolucion.item(0).getTextContent(), formatoFecha));
            } catch (OperationNotSupportedException | DOMException e) {
                e.printStackTrace();
            }
        }
        return alquiler;
    }

    public void terminar() {
        try {
            escribirXml();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void escribirXml() throws ParserConfigurationException, TransformerException {
        Document DOM = UtilidadesXml.crearDomVacio(RAIZ);
        Element alquileres = DOM.getDocumentElement();
        Iterator<Alquiler> iterator = coleccionAlquileres.iterator();
        while (iterator.hasNext()) {
            Alquiler alquiler = iterator.next();
            try {
                Element alquilerDOM = alquilerToElement(DOM, alquiler);
                alquileres.appendChild(alquilerDOM);
            } catch (DOMException e) {
                e.printStackTrace();
            }
        }
        UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
    }

    private Element alquilerToElement(Document DOM, Alquiler alquiler) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        LocalDate fechaAlquiler = alquiler.getFechaAlquiler();
        String fechaAlquilerFormateada = fechaAlquiler.format(formatoFecha);
        LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
        String fechaDevolucionFormateada = null;
        if (fechaDevolucion != null) {
            fechaDevolucionFormateada = fechaDevolucion.format(formatoFecha);
        }
        String ALQUILER = "alquiler";
        Element alquilerDOM = DOM.createElement(ALQUILER);
        alquilerDOM.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());
        alquilerDOM.setAttribute(MATRICULA_VEHICULO, alquiler.getVehiculo().getMatricula());
        Element fechaAlquilerD = DOM.createElement(FECHA_ALQUILER);
        fechaAlquilerD.setAttribute(FORMATO, FORMATO_FECHA);
        fechaAlquilerD.setAttribute(TIPO_DATO, "LocalDate");
        fechaAlquilerD.setTextContent(fechaAlquilerFormateada);
        alquilerDOM.appendChild(fechaAlquilerD);
        Element fechaDevolucionD = DOM.createElement(FECHA_DEVOLUCION);
        if (fechaDevolucion != null) {
            fechaDevolucionD.setTextContent(fechaDevolucionFormateada);
        } else {
            fechaDevolucionD.setTextContent("");
        }
        fechaDevolucionD.setAttribute(FORMATO, FORMATO_FECHA);
        fechaDevolucionD.setAttribute(TIPO_DATO, "LocalDate");
        alquilerDOM.appendChild(fechaDevolucionD);
        return alquilerDOM;
    }

    public List<Alquiler> get() {
        List<Alquiler> copiaAlquileres = new LinkedList<>(coleccionAlquileres);
        return copiaAlquileres;
    }

    public List<Alquiler> get(Cliente cliente) {
        List<Alquiler> alquilerCliente = new LinkedList<>();
        for (Alquiler alquiler : coleccionAlquileres) {
            if (alquiler.getCliente().equals(cliente)) {
                alquilerCliente.add(alquiler);
            }
        }
        return alquilerCliente;
    }

    public List<Alquiler> get(Vehiculo vehiculo) {
        List<Alquiler> alquilerTurismo = new LinkedList<>();
        for (Alquiler alquiler : coleccionAlquileres) {
            if (alquiler.getVehiculo().equals(vehiculo)) {
                alquilerTurismo.add(alquiler);
            }
        }
        return alquilerTurismo;
    }

    public int getCantidad() {
        return coleccionAlquileres.size();
    }

    private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
        for (Alquiler alquiler : coleccionAlquileres) {
            if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
                throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
            }
            if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
                throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
            }
            if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() != null &&
                    alquiler.getFechaAlquiler().isAfter(fechaAlquiler)) {
                throw new OperationNotSupportedException("ERROR: El vehiculo tiene un alquiler posterior.");
            }
            if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() != null &&
                    alquiler.getFechaAlquiler().isAfter(fechaAlquiler)) {
                throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
            }
        }
    }

    public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
        }
        for (Alquiler a : coleccionAlquileres) {
            if (a.getVehiculo().equals(alquiler.getVehiculo()) && a.getCliente().equals(alquiler.getCliente())) {
                throw new OperationNotSupportedException("ERROR: El alquiler ya existe.");
            }
        }
        comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
        coleccionAlquileres.add(alquiler);
    }


    public Alquiler buscar(Alquiler alquiler) {
        Alquiler alquilerB = null;
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
        }
        for (Alquiler buscaAlquiler : coleccionAlquileres) {
            if (buscaAlquiler.equals(alquiler)) {
                buscaAlquiler = alquilerB;
            }
        }
        return coleccionAlquileres.get(coleccionAlquileres.indexOf(alquilerB));
    }

    public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
        }
        if (!coleccionAlquileres.contains(alquiler)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
        } else {
            coleccionAlquileres.remove(alquiler);
        }
    }

    public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
        Alquiler alquiler = null;
        if (vehiculo == null || fechaDevolucion == null) {
            throw new NullPointerException("ERROR: No se puede devolver un alquiler Nulo");
        }
        alquiler = getAlquilerAbierto(vehiculo);
        if (alquiler == null) {
            throw new OperationNotSupportedException("ERROR: El vehículo no registra alquiler abierto actualmente");
        }
        alquiler.devolver(fechaDevolucion);
    }

    private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
        Alquiler alquilerAbierto = null;
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: el automóvil no puede ser nulo");
        }
        for (Alquiler alquiler : coleccionAlquileres) {
            if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
                alquilerAbierto = alquiler;
            }
        }
        return alquilerAbierto;
    }

    public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
        if (cliente == null || fechaDevolucion == null) {
            throw new NullPointerException("ERROR: el cliente no puede ser Nulo");
        }
        Alquiler alquiler = getAlquilerAbierto(cliente);

        if (alquiler == null) {
            throw new OperationNotSupportedException("ERROR: El cliente no registra alquiler abierto");
        }
        alquiler.devolver(fechaDevolucion);
    }

    private Alquiler getAlquilerAbierto(Cliente cliente) {
        Alquiler alquilerAbierto = null;
        if (cliente == null) {
            throw new NullPointerException("ERROR: el automóvil no puede ser nulo");
        }
        for (Alquiler alquiler : coleccionAlquileres) {
            if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
                alquilerAbierto = alquiler;
            }
        }
        return alquilerAbierto;
    }

}
