package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.*;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Vehiculos {

    private final String RUTA_FICHERO = "vehiculos.xml";
    private final String RAIZ = "vehiculos";
    private final String VEHICULO = "vehiculo";
    private final String MARCA = "marca";
    private final String MODELO = "modelo";
    private final String MATRICULA = "matricula";
    private final String CILINDRADA = "cilindrada";
    private final String PLAZAS = "plazas";
    private final String PMA = "pma";
    private final String TIPO = "tipo";
    private final String TURISMO = "turismo";
    private final String AUTOBUS = "autobus";
    private final String FURGONETA = "furgoneta";
    private final String TIPO_DATO = "tipodato";

    private List<Vehiculo> coleccionVehiculos = new ArrayList<>();
    private static Vehiculos instancia = new Vehiculos();

    private Vehiculos() {
    }

    public List<Vehiculo> get() {
        ArrayList<Vehiculo> vehiculosR = new ArrayList<>(coleccionVehiculos);
        return vehiculosR;
    }

    public int getCantidad() {
        return coleccionVehiculos.size();
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
        }
        if (!coleccionVehiculos.contains(vehiculo)) {
            coleccionVehiculos.add(vehiculo);
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matrícula.");
        }
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");
        }
        Vehiculo vehiculoB = null;
        if (coleccionVehiculos.contains(vehiculo)) {
            vehiculoB = (coleccionVehiculos.get(coleccionVehiculos.indexOf(vehiculo)));
        }
        return vehiculoB;
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
        }
        if (coleccionVehiculos.contains(vehiculo)) {
            coleccionVehiculos.remove(vehiculo);
        } else throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
    }

    protected static IVehiculos getInstancia() {
        return (IVehiculos) instancia;
    }

    public void comenzar() {
        try {
            leerXml();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void terminar() {
        try {
            escribirXml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leerXml() throws OperationNotSupportedException {
        Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
        org.w3c.dom.Element listaVehiculos = DOM.getDocumentElement();
        NodeList listaNodos = listaVehiculos.getChildNodes();
        for (int i = 0; i < listaNodos.getLength(); i++) {
            Node nodo = listaNodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Vehiculo vehiculo = elementToVehiculo((org.w3c.dom.Element) nodo);
                insertar(vehiculo);
            }
        }
    }

    private void escribirXml() {
        Document DOM = UtilidadesXml.crearDomVacio(RAIZ);
        org.w3c.dom.Element listaVehiculos = DOM.getDocumentElement();
        for (Vehiculo v : coleccionVehiculos) {
            org.w3c.dom.Element vehiculoDOM = vehiculoToElement(DOM, v);
            listaVehiculos.appendChild(vehiculoDOM);
            UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
        }
    }

    private Vehiculo elementToVehiculo(org.w3c.dom.Element element) {
        Vehiculo vehiculo = null;
        org.w3c.dom.Element vehiculoDOM = element;
        String matriculaAtr = vehiculoDOM.getAttribute(MATRICULA);
        String tipoAtr = vehiculoDOM.getAttribute(TIPO);
        org.w3c.dom.Element marca = (org.w3c.dom.Element) vehiculoDOM.getElementsByTagName(MARCA).item(0);
        org.w3c.dom.Element modelo = (org.w3c.dom.Element) vehiculoDOM.getElementsByTagName(MODELO).item(0);
        if (tipoAtr.equalsIgnoreCase(TURISMO)) {
            org.w3c.dom.Element turismoDOM = (org.w3c.dom.Element) vehiculoDOM.getElementsByTagName(TURISMO).item(0);
            org.w3c.dom.Element cilindrada = (org.w3c.dom.Element) turismoDOM.getElementsByTagName(CILINDRADA).item(0);
            vehiculo = new Turismo(marca.getTextContent(), modelo.getTextContent(), Integer.parseInt(cilindrada.getTextContent()), matriculaAtr);
        }
        if (tipoAtr.equalsIgnoreCase(FURGONETA)) {
            org.w3c.dom.Element furgonetaDOM = (org.w3c.dom.Element) vehiculoDOM.getElementsByTagName(FURGONETA).item(0);

            org.w3c.dom.Element pma = (org.w3c.dom.Element) furgonetaDOM.getElementsByTagName(PMA).item(0);
            org.w3c.dom.Element plazas = (org.w3c.dom.Element) furgonetaDOM.getElementsByTagName(PLAZAS).item(0);
            vehiculo = new Furgoneta(marca.getTextContent(), modelo.getTextContent(), Integer.parseInt(pma.getTextContent()), Integer.parseInt(plazas.getTextContent()), matriculaAtr);
        }
        if (tipoAtr.equalsIgnoreCase(AUTOBUS)) {
            org.w3c.dom.Element autobusDOM = (org.w3c.dom.Element) vehiculoDOM.getElementsByTagName(AUTOBUS).item(0);
            org.w3c.dom.Element plazas = (org.w3c.dom.Element) autobusDOM.getElementsByTagName(PLAZAS).item(0);
            vehiculo = new Autobus(marca.getTextContent(), modelo.getTextContent(), Integer.parseInt(plazas.getTextContent()), matriculaAtr);
        }
        return vehiculo;
    }

    private org.w3c.dom.Element vehiculoToElement(Document DOM, Vehiculo vehiculo) {
        String tipoVehiculo = null;
        if (vehiculo instanceof Turismo) {
            tipoVehiculo = TURISMO;
        }
        if (vehiculo instanceof Autobus) {
            tipoVehiculo = AUTOBUS;
        }
        if (vehiculo instanceof Furgoneta) {
            tipoVehiculo = FURGONETA;
        }
        org.w3c.dom.Element vehiculoDOM = DOM.createElement(VEHICULO);
        vehiculoDOM.setAttribute(MATRICULA, vehiculo.getMatricula());
        vehiculoDOM.setAttribute(TIPO, tipoVehiculo);
        org.w3c.dom.Element marcaE = DOM.createElement(MARCA);
        marcaE.setTextContent(vehiculo.getMarca());
        marcaE.setAttribute(TIPO_DATO, "String");
        vehiculoDOM.appendChild(marcaE);
        org.w3c.dom.Element modeloE = DOM.createElement(MODELO);
        modeloE.setTextContent(vehiculo.getModelo());
        modeloE.setAttribute(TIPO_DATO, "String");
        vehiculoDOM.appendChild(modeloE);
        if (vehiculo instanceof Turismo) {
            org.w3c.dom.Element turismoE = DOM.createElement(TURISMO);
            vehiculoDOM.appendChild(turismoE);

            org.w3c.dom.Element cilindradaE = DOM.createElement(CILINDRADA);
            cilindradaE.setTextContent(Integer.toString(((Turismo) vehiculo).getCilindrada()));
            cilindradaE.setAttribute(TIPO_DATO, "Integer");
            turismoE.appendChild(cilindradaE);
        }
        if (vehiculo instanceof Autobus) {
            org.w3c.dom.Element autobusE = DOM.createElement(AUTOBUS);
            vehiculoDOM.appendChild(autobusE);
            org.w3c.dom.Element plazasE = DOM.createElement(PLAZAS);
            plazasE.setTextContent(Integer.toString(((Autobus) vehiculo).getPlazas()));
            plazasE.setAttribute(TIPO_DATO, "Integer");
            autobusE.appendChild(plazasE);
        }
        if (vehiculo instanceof Furgoneta) {
            org.w3c.dom.Element furgonetaE = DOM.createElement(FURGONETA);
            vehiculoDOM.appendChild(furgonetaE);

            org.w3c.dom.Element plazasE = DOM.createElement(PLAZAS);
            plazasE.setTextContent(Integer.toString(((Furgoneta) vehiculo).getPlazas()));
            plazasE.setAttribute(TIPO_DATO, "Integer");
            furgonetaE.appendChild(plazasE);
            Element pmaE = DOM.createElement(PMA);
            pmaE.setTextContent(Integer.toString(((Furgoneta) vehiculo).getPma()));
            pmaE.setAttribute(TIPO_DATO, "Integer");
            furgonetaE.appendChild(pmaE);
        }
        return vehiculoDOM;
    }

}
