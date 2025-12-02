package es.dam.streamingcatalog.repository;

import es.dam.streamingcatalog.model.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class PeliculaRepository {

    private SessionFactory factory;

    public PeliculaRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public Long crear(Pelicula p) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        s.persist(p);
        tx.commit();
        s.close();
        return p.getId();
    }

    public Pelicula get(Long id) {
        Session s = factory.openSession();
        Pelicula p = s.get(Pelicula.class, id);
        s.close();
        return p;
    }

    public boolean actualizarAnio(Long id, int nuevoAnio) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        Pelicula p = s.get(Pelicula.class, id);
        if (p != null) {
            p.setAnioLanzamiento(nuevoAnio);
            tx.commit();
            s.close();
            return true;
        }
        s.close();
        return false;
    }

    public List<Pelicula> buscarPorAnioMayor(int anio) {
        Session s = factory.openSession();
        List<Pelicula> lista = s.createQuery(
                "SELECT p FROM Pelicula p WHERE p.anioLanzamiento > :anio",
                Pelicula.class
        ).setParameter("anio", anio).getResultList();
        s.close();
        return lista;
    }

    public boolean eliminar(Long id) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        Pelicula p = s.get(Pelicula.class, id);
        if (p != null) {
            s.remove(p);
            tx.commit();
            s.close();
            return true;
        }
        s.close();
        return false;
    }
}
