package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;

public class MainApp {

	public static void main(String[] args) throws Exception {
		Vista vistaTexto = new VistaTexto();
		Modelo modeloCascada = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
		Controlador controlador = new Controlador(modeloCascada, vistaTexto);
		controlador.comenzar();
	}

}
