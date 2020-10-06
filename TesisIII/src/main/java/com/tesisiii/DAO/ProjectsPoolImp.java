
package com.tesisiii.DAO;


import com.tesisiii.model.ProjectPool;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public class ProjectsPoolImp implements ProjectsPoolInterface{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addPool(ProjectPool pp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pp);
        System.out.println("addPool done");
        session.getTransaction().commit();
    }

    @Override
    public void removePool(ProjectPool pp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(pp);
        session.getTransaction().commit();
    }

    @Override
    public ProjectPool getPool(Integer id) {
        Session session = sessionFactory.openSession();
        return (ProjectPool) session.createCriteria(ProjectPool.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public void editPool(ProjectPool pp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(pp);
        session.getTransaction().commit();
    }

    @Override
    public List<ProjectPool> listPool() {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de listPool");
        return session.createCriteria(ProjectPool.class).list(); 
    }

    @Override
    public ProjectPool getPoolByManager(String id) {
        Session session = sessionFactory.openSession();
        return (ProjectPool) session.createCriteria(ProjectPool.class).add(Restrictions.eq("userId",id)).uniqueResult();
    }
    
    
}
