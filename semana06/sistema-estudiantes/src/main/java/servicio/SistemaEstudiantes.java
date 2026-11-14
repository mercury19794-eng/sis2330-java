package servicio;

import modelo.Estudiante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class SistemaEstudiantes {

    // Aquí se guardan los estudiantes indexados por código (búsqueda O(1))
    private final HashMap<String, Estudiante> porCodigo;

    // Aquí se guarda el ranking, siempre ordenado según compareTo de Estudiante
    private final TreeSet<Estudiante> ranking;

    // Constructor: crea las dos colecciones vacías
    public SistemaEstudiantes() {
        this.porCodigo = new HashMap<>();
        this.ranking = new TreeSet<>();
    }

    public boolean registrar(Estudiante e) {
        if (porCodigo.containsKey(e.getCodigo())) return false;
        porCodigo.put(e.getCodigo(), e);
        ranking.add(e);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo);
    }

    public boolean actualizarPromedio(String codigo, double nuevoPromedio) {
        Estudiante e = porCodigo.get(codigo);
        if (e == null) return false;

        // Puntero importante: se saca del TreeSet ANTES de mutar el promedio
        ranking.remove(e);
        try {
            e.setPromedio(nuevoPromedio);
        } finally {
            ranking.add(e);
        }
        return true;
    }

    public ArrayList<Estudiante> listarRanking() {
        // Se crea una copia en ArrayList: nunca se expone el TreeSet interno
        return new ArrayList<>(ranking);
    }

    public ArrayList<Estudiante> topN(int n) {
        // Aquí se crea la lista que se devuelve con el top N
        ArrayList<Estudiante> top = new ArrayList<>();
        int i = 0;
        for (Estudiante e : ranking) {
            if (i >= n) break;
            top.add(e);
            i++;
        }
        return top;
    }

    public boolean eliminar(String codigo) {
        Estudiante e = porCodigo.remove(codigo);
        if (e == null) return false;
        ranking.remove(e);
        return true;
    }

    public int cantidad() {
        return porCodigo.size();
    }
}
