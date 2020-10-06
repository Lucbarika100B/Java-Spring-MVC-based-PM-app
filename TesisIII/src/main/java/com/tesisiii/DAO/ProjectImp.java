
package com.tesisiii.DAO;


import com.tesisiii.model.Project;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class ProjectImp implements ProjectInterface{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addProject(Project p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        p.setpStatus("IN RPOGRESS");
        session.save(p);
        session.getTransaction().commit();    
    }

    @Override
    public void removeProject(Project p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //session.remove(p);
        //session.delete(p);
        System.out.println("projecto ID" + p.getId());
        p.setpStatus("DELETED");
        session.update(p);
        System.out.println("estoy en el DAO project imp remove project");
        session.getTransaction().commit();
    }

    @Override
    public Project getProject(Integer id) {
        Session session = sessionFactory.openSession();
        return (Project) session.createCriteria(Project.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Project getProjectByManager(String pmlogin) {
        Session session = sessionFactory.openSession();
        return (Project) session.createCriteria(Project.class).add(Restrictions.eq("manager", pmlogin)).uniqueResult();
    }

    @Override
    public void editProject(Project p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
    }

    @Override
    public List<Project> listProject() {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de listProject");
        return session.createCriteria(Project.class)
                .add(Restrictions.ne("pStatus", "Initial"))
                .add(Restrictions.ne("pStatus", "DELETED"))
                .add(Restrictions.ne("pStatus", "COMPLETED"))
                .list(); 
    }
    
    @Override
    public List<Project> projectListByManager(String manager ) {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de projectListByManager");
        System.out.println("String manager : " + manager);
        List<Project> projectListByManager = session.createCriteria(Project.class)
                .add(Restrictions.eq("manager", manager))
                .add(Restrictions.ne("pStatus", "DELETED"))
                .add(Restrictions.ne("pStatus", "COMPLETED"))
                .list(); 
        System.out.println("tengo la lista projectListByManager");
        return projectListByManager;
    }

    @Override
    public List<Project> listProjectDeleted() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Project.class)
                .add(Restrictions.eq("pStatus", "DELETED"))
                .list();
    }

    @Override
    public List<Project> listProjectCompleted() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Project.class)
                .add(Restrictions.eq("pStatus", "COMPLETED"))
                .list();
    }
    @Override
    public void mergeProject(Project p) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(p);
        session.getTransaction().commit();
    }
}
