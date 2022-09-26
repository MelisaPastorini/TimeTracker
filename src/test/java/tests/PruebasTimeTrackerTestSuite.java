package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses( {
        PruebaAgregarTiempo.class,
        PruebaGenerarReporte.class,
        PruebaEliminarHora.class,
        PruebaVerificarProyecto.class,
        PruebaVerificarUsuario.class
} )
public class PruebasTimeTrackerTestSuite {

}
