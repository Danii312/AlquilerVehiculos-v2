package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {

    SALIR("Salir") {
        public void ejecutar() {
            vista.terminar();
        }
    },

    INSERTAR_CLIENTE("Insertar cliente") {
        protected void ejecutar() {
            vista.insertarCliente();
        }
    },
    INSERTAR_TURISMO("Insertar vehiculo") {
        protected void ejecutar() {
            vista.insertarVehiculo();
        }
    },
    INSERTAR_ALQUILER("Insertar alquiler") {
        protected void ejecutar() {
            vista.insertarAlquiler();
        }
    },
    BUSCAR_CLIENTE("Buscar cliente") {
        protected void ejecutar() {
            vista.buscarCliente();
        }
    },
    BUSCAR_TURISMO("Buscar vehiculo") {
        protected void ejecutar() {
            vista.buscarVehiculo();
        }
    },
    BUSCAR_ALQUILER("Buscar alquiler") {
        protected void ejecutar() {
            vista.buscarAlquiler();
        }
    },
    MODIFICAR_CLIENTE("Modificar cliente") {
        protected void ejecutar() {
            vista.modificarCliente();
        }
    },
    DEVOLVER_ALQUILER("Devolver alquiler") {
        protected void ejecutar() {
            vista.devolverAlquiler();
        }
    },
    BORRAR_CLIENTE("Borrar cliente") {
        protected void ejecutar() {
            vista.borrarCliente();
        }
    },
    BORRAR_TURISMO("Borrar vehiculo") {
        protected void ejecutar() {
            vista.borrarVehiculo();
        }
    },
    BORRAR_ALQUILER("Borrar alquiler") {
        protected void ejecutar() {
            vista.borrarAlquiler();
        }
    },
    LISTAR_CLIENTES("Listar clientes") {
        protected void ejecutar() {
            vista.listarCliente();
        }
    },
    LISTAR_TURISMOS("Listar vehiculo") {
        protected void ejecutar() {
            vista.listarVehiculos();
        }
    },
    LISTAR_ALQUILERES("Listar alquileres") {
        protected void ejecutar() {
            vista.listarAlquiler();
        }
    },
    LISTAR_ALQUILERES_CLIENTES("Listar alquileres de clientes") {
        protected void ejecutar() {
            vista.listarAlquileresCliente();
        }
    },
    LISTAR_ALQUILERES_TURISMO("Listar alquileres de vehículos") {
        protected void ejecutar() {
            vista.listarAlquileresVehiculo();
        }
    };

    protected VistaTexto vista;
    private String cadenaAmostrar;

    Accion(String cadenaAmostrar) {
        this.cadenaAmostrar = cadenaAmostrar;
    }

    private boolean esOrdinalValido(int ordinal) {
        return (ordinal >= 0 && ordinal < Accion.values().length);

    }

    public Accion get(int ordinal) throws OperationNotSupportedException {
        if (esOrdinalValido(ordinal)) {
            return Accion.values()[ordinal];
        } else {
            throw new OperationNotSupportedException("No se ha encontrado el ordinal insertado.");
        }
    }


    protected void setVista(VistaTexto vistaTexto) {
        this.vista = vistaTexto;
    }

    protected abstract void ejecutar();

    @Override
    public String toString() {
        return this.cadenaAmostrar;
    }

}
