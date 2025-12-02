package es.dam.streamingcatalog.repository;

import es.dam.streamingcatalog.model.Estudio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EstudioRepository {

    private SessionFactory factory;

    public EstudioRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public Long crear(Estudio e) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        s.persist(e);
        tx.commit();
        s.close();
        return e.getId();
    }

    public Estudio get(Long id) {
        Session s = factory.openSession();
        Estudio e = s.get(Estudio.class, id);
        s.close();
        return e;
    }

    public List<Estudio> listar() {
        Session s = factory.openSession();
        List<Estudio> lista = s.createQuery("FROM Estudio", Estudio.class).list();
        s.close();
        return lista;
    }

    public boolean eliminar(Long id) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        Estudio e = s.get(Estudio.class, id);
        if (e != null) {
            s.remove(e);
            tx.commit();
            s.close();
            return true;
        }
        s.close();
        return false;
    }
}

