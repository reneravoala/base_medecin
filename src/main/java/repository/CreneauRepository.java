package repository;

import entity.Client;
import entity.Creneau;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CreneauRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    CriteriaBuilder builder = em.getCriteriaBuilder();
    public List<Creneau> findAll()
    {
        CriteriaQuery<Creneau> query = builder.createQuery(Creneau.class);
        Root<Creneau> m = query.from(Creneau.class);
        query.select(m);

        return em.createQuery(query).getResultList();
    }

    public Creneau save(Creneau creneau)
    {
        em.getTransaction().begin();
        em.persist(creneau);
        em.getTransaction().commit();
        return creneau;
    }
}
