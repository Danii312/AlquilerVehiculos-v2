package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Clientes {

    private final String RUTA_FICHERO ="datos/clientes.xml";
    private final String RAIZ = "clientes";
    private final String CLIENTE = "cliente";
    private final String NOMBRE ="nombre";
    private final String DNI = "dni";
    private final String TELEFONO = "telefono";
    private final String TIPO_DATO = "tipodato";

    private List<Cliente> coleccionClientes = new ArrayList<>();
    private static Clientes instancia = new Clientes();

    private Clientes() {

    }

    public List<Cliente> get() {
        ArrayList<Cliente> clientesR = new ArrayList<>(coleccionClientes);
        return clientesR;
    }

    public int getCantidad() {
        try {
            return coleccionClientes.size();
        } catch (Exception e) {
            int cantidad = 0;
            return cantidad;
        }
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
        }
        if (!coleccionClientes.contains(cliente)) {
            coleccionClientes.add(new Cliente(cliente));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
        }
    }

    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            return (coleccionClientes.get(coleccionClientes.indexOf(cliente)));
        } else return null;
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            coleccionClientes.remove(coleccionClientes.indexOf(cliente));
        } else throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
        }
        if (coleccionClientes.contains(cliente)) {
            try {
                if (nombre == null || nombre.trim() == null || nombre == "") {
                    nombre = cliente.getNombre();
                }
                if (telefono == null || telefono.trim() == null || telefono == "") {
                    telefono = cliente.getTelefono();
                }
                coleccionClientes.get(coleccionClientes.indexOf(cliente)).setNombre(nombre);
                coleccionClientes.get(coleccionClientes.indexOf(cliente)).setTelefono(telefono);
            } catch (Exception e) {
                System.out.println("ERROR: No se pudo insertar el cliente");
            }

        } else throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
    }

    protected static IClientes getInstancia() {
        return (IClientes) instancia;
    }

    public void comenzar() throws OperationNotSupportedException {
        leerXml();
    }

    public void terminar() {
        escribirXml();
    }

    private void leerXml() throws OperationNotSupportedException {
        org.w3c.dom.Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
        org.w3c.dom.Element listaClientes = DOM.getDocumentElement();
        NodeList listaNodos = listaClientes.getChildNodes();
        for (int i = 0; i < listaNodos.getLength(); i++) {
            Node nodo = listaNodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Cliente cliente = elementToCliente((org.w3c.dom.Element) nodo);
                insertar(cliente);
            }
        }
    }

    private void escribirXml() {
        org.w3c.dom.Document DOM = UtilidadesXml.crearDomVacio(RAIZ);
        org.w3c.dom.Element listaClientes = DOM.getDocumentElement();
        for (Cliente c : coleccionClientes) {
            org.w3c.dom.Element clienteDOM = clienteToElement(DOM, c);
            listaClientes.appendChild(clienteDOM);
            UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
        }
    }

    private Cliente elementToCliente(org.w3c.dom.Element element) {
        org.w3c.dom.Element clienteDOM = element;
        String dniAtr = clienteDOM.getAttribute("dni");
        org.w3c.dom.Element nombre = (org.w3c.dom.Element) clienteDOM.getElementsByTagName(NOMBRE).item(0);
        org.w3c.dom.Element telefono = (org.w3c.dom.Element) clienteDOM.getElementsByTagName(TELEFONO).item(0);
        Cliente cliente = new Cliente(nombre.getTextContent(), dniAtr, telefono.getTextContent());
        return cliente;
    }

    private org.w3c.dom.Element clienteToElement(Document DOM, Cliente c) {
        org.w3c.dom.Element clienteDOM = DOM.createElement(CLIENTE);
        clienteDOM.setAttribute(DNI, c.getDni());
        org.w3c.dom.Element nombreE = DOM.createElement(NOMBRE);
        nombreE.setTextContent(c.getNombre());
        nombreE.setAttribute(TIPO_DATO, "String");
        clienteDOM.appendChild(nombreE);
        Element telefonoE = DOM.createElement(TELEFONO);
        telefonoE.setTextContent(c.getTelefono());
        telefonoE.setAttribute(TIPO_DATO, "String");
        clienteDOM.appendChild(telefonoE);
        return clienteDOM;
    }

}
