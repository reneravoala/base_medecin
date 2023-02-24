package repository;

import entity.RV;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RvRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    CriteriaBuilder builder = em.getCriteriaBuilder();

    public RV find(int id)
    {
        return em.find(RV.class, id);
    }

    public List<RV> findAll(String search)
    {
        CriteriaQuery<RV> query = builder.createQuery(RV.class);
        Root<RV> rv = query.from(RV.class);
        query.select(rv);
        rv.join("creneau");

        em.clear();
        return em.createQuery(query).getResultList();
    }

    public List<RV> findAll()
    {
        return findAll("");
    }

    public List<RV> findByClient(Integer clientId)
    {
        CriteriaQuery<RV> query = builder.createQuery(RV.class);
        Root<RV> rv = query.from(RV.class);
        query.select(rv);
        rv.join("creneau");
        query.where(builder.equal(rv.get("client"), clientId));

        em.clear();
        return em.createQuery(query).getResultList();
    }

    public RV save(RV rv)
    {
        em.getTransaction().begin();
        em.persist(rv);
        em.getTransaction().commit();
        return rv;
    }

    public void delete(RV rv)
    {
        em.getTransaction().begin();
        em.remove(rv);
        em.getTransaction().commit();
    }
}
