package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.AlquilerTest;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.ClienteTest;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.TurismoTest;
import negocio.AlquileresTest;
import negocio.ClientesTest;
import negocio.TurismosTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ClienteTest.class, ClientesTest.class, TurismoTest.class, TurismosTest.class, 
	AlquilerTest.class, AlquileresTest.class, ModeloTest.class })
public class AllTests { }
