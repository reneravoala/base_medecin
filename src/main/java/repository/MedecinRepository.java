package repository;

import entity.Medecin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MedecinRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    CriteriaBuilder builder = em.getCriteriaBuilder();

    public Medecin find(int id)
    {
        return em.find(Medecin.class, id);
    }

    public List<Medecin> findAll(String search)
    {
        CriteriaQuery<Medecin> query = builder.createQuery(Medecin.class);
        Root<Medecin> m = query.from(Medecin.class);
        query.select(m);
        query.where(builder.like(m.get("nom"), "%" + search + "%"));

        em.clear();
        return em.createQuery(query).getResultList();
    }

    public List<Medecin> findAll()
    {
        return findAll("");
    }

    public Medecin save(Medecin medecin)
    {
        em.getTransaction().begin();
        em.persist(medecin);
        em.getTransaction().commit();
        return medecin;
    }

    public void delete(Medecin Medecin)
    {
        em.remove(Medecin);
    }
}
