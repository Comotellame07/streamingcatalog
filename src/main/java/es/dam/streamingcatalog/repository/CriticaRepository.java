package es.dam.streamingcatalog.repository;

import es.dam.streamingcatalog.model.Critica;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CriticaRepository {

    private SessionFactory factory;

    public CriticaRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public Long crear(Critica c) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        s.persist(c);
        tx.commit();
        s.close();
        return c.getId();
    }

    public List<Critica> listar() {
        Session s = factory.openSession();
        List<Critica> lista = s.createQuery("FROM Critica", Critica.class).list();
        s.close();
        return lista;
    }

    public boolean eliminar(Long id) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        Critica c = s.get(Critica.class, id);
        if (c != null) {
            s.remove(c);
            tx.commit();
            s.close();
            return true;
        }
        s.close();
        return false;
    }
}
