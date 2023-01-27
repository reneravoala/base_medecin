package repository;

import entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    CriteriaBuilder builder = em.getCriteriaBuilder();

    public Client find(int id)
    {
        return em.find(Client.class, id);
    }

    public List<Client> findAll(String search)
    {
        List<Client> Clients = null;
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> m = query.from(Client.class);
        query.select(m);
        query.where(builder.like(m.get("nom"), "%" + search + "%"));

        return em.createQuery(query).getResultList();
    }

    public Client save(Client Client)
    {
        em.getTransaction().begin();
        em.persist(Client);
        em.getTransaction().commit();
        return Client;
    }

    public void delete(Client Client)
    {
        em.remove(Client);
    }
}
