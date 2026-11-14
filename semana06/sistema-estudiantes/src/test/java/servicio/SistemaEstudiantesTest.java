package servicio;

import modelo.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SistemaEstudiantesTest {

    private SistemaEstudiantes sistema;

    @BeforeEach
    void preparar() {
        // Aquí se crea el sistema vacío antes de cada test: ninguno depende del anterior
        sistema = new SistemaEstudiantes();
    }

    @Test
    void registrarEstudianteNuevoDevuelveTrueYAumentaLaCantidad() {
        assertTrue(sistema.registrar(new Estudiante("E1", "Ana", 90.0)));
        assertEquals(1, sistema.cantidad());
    }

    @Test
    void registrarConCodigoDuplicadoDevuelveFalseYNoAgregaOtro() {
        sistema.registrar(new Estudiante("E1", "Ana", 90.0));
        assertFalse(sistema.registrar(new Estudiante("E1", "Otra", 50.0)));
        assertEquals(1, sistema.cantidad());
        assertEquals("Ana", sistema.buscarPorCodigo("E1").getNombre());
    }

    @Test
    void buscarPorCodigoExistenteDevuelveElEstudiante() {
        sistema.registrar(new Estudiante("E1", "Ana", 90.0));
        Estudiante e = sistema.buscarPorCodigo("E1");
        assertNotNull(e);
        assertEquals("Ana", e.getNombre());
    }

    @Test
    void buscarPorCodigoInexistenteDevuelveNull() {
        assertNull(sistema.buscarPorCodigo("NOEXISTE"));
    }

    @Test
    void actualizarPromedioDeEstudianteExistenteDevuelveTrue() {
        sistema.registrar(new Estudiante("E1", "Ana", 70.0));
        assertTrue(sistema.actualizarPromedio("E1", 95.0));
        assertEquals(95.0, sistema.buscarPorCodigo("E1").getPromedio(), 0.001);
    }

    @Test
    void actualizarPromedioDeEstudianteInexistenteDevuelveFalse() {
        assertFalse(sistema.actualizarPromedio("NOEXISTE", 80.0));
    }

    @Test
    void actualizarPromedioReordenaElRanking() {
        sistema.registrar(new Estudiante("E1", "Ana", 70.0));
        sistema.registrar(new Estudiante("E2", "Luis", 95.0));
        sistema.actualizarPromedio("E1", 99.0);
        assertEquals("E1", sistema.listarRanking().get(0).getCodigo());
    }

    @Test
    void actualizarPromedioInvalidoLanzaExcepcionYConservaAlEstudiante() {
        sistema.registrar(new Estudiante("E1", "Ana", 70.0));
        assertThrows(IllegalArgumentException.class,
                () -> sistema.actualizarPromedio("E1", 150.0));
        assertEquals(70.0, sistema.buscarPorCodigo("E1").getPromedio(), 0.001);
        assertEquals(1, sistema.listarRanking().size());
    }

    @Test
    void eliminarEstudianteExistenteDevuelveTrueYLoQuitaDelRanking() {
        sistema.registrar(new Estudiante("E1", "Ana", 90.0));
        assertTrue(sistema.eliminar("E1"));
        assertNull(sistema.buscarPorCodigo("E1"));
        assertEquals(0, sistema.listarRanking().size());
    }

    @Test
    void eliminarEstudianteInexistenteDevuelveFalse() {
        assertFalse(sistema.eliminar("NOEXISTE"));
    }

    @Test
    void listarRankingConColeccionVaciaDevuelveListaVacia() {
        ArrayList<Estudiante> ranking = sistema.listarRanking();
        assertTrue(ranking.isEmpty());
    }

    @Test
    void listarRankingOrdenaPorPromedioDescendente() {
        sistema.registrar(new Estudiante("E1", "Ana", 70.0));
        sistema.registrar(new Estudiante("E2", "Luis", 95.0));
        sistema.registrar(new Estudiante("E3", "Zoe", 80.0));

        ArrayList<Estudiante> ranking = sistema.listarRanking();
        assertEquals("E2", ranking.get(0).getCodigo());
        assertEquals("E3", ranking.get(1).getCodigo());
        assertEquals("E1", ranking.get(2).getCodigo());
    }

    @Test
    void listarRankingConPromediosIgualesDesempataPorCodigo() {
        sistema.registrar(new Estudiante("E2", "Luis", 80.0));
        sistema.registrar(new Estudiante("E1", "Ana", 80.0));

        ArrayList<Estudiante> ranking = sistema.listarRanking();
        assertEquals(2, ranking.size());
        assertEquals("E1", ranking.get(0).getCodigo());
        assertEquals("E2", ranking.get(1).getCodigo());
    }

    @Test
    void topNConNMayorAlTotalDevuelveTodosSinLanzarExcepcion() {
        sistema.registrar(new Estudiante("E1", "Ana", 70.0));
        sistema.registrar(new Estudiante("E2", "Luis", 95.0));
        assertEquals(2, sistema.topN(10).size());
    }

    @Test
    void topNDevuelveSoloLosMejoresN() {
        sistema.registrar(new Estudiante("E1", "Ana", 70.0));
        sistema.registrar(new Estudiante("E2", "Luis", 95.0));
        sistema.registrar(new Estudiante("E3", "Zoe", 80.0));

        ArrayList<Estudiante> top = sistema.topN(2);
        assertEquals(2, top.size());
        assertEquals("E2", top.get(0).getCodigo());
        assertEquals("E3", top.get(1).getCodigo());
    }

    @Test
    void crearEstudianteConPromedioFueraDeRangoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("E1", "Ana", 101.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("E1", "Ana", -1.0));
    }

    @Test
    void crearEstudianteConCodigoVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("  ", "Ana", 80.0));
    }
}
