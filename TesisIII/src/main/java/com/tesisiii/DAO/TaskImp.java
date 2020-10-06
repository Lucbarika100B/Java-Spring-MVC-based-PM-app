
package com.tesisiii.DAO;


import com.tesisiii.model.Task;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public class TaskImp implements TaskInterface{

    @Autowired
    private SessionFactory sessionFactory;
       
    @Override
    public void addTask(Task t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
    }

    @Override
    public void removeTask(Task t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        t.setStatus("REMOVE");
        session.update(t);
        session.getTransaction().commit();
    }

    @Override
    public Task getTask(Integer id) {
        Session session = sessionFactory.openSession();
        return (Task) session.createCriteria(Task.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public void editTask(Task t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }

    @Override
    public List<Task> listTasks() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Task.class).list();    
    }

    @Override
    public List<Task> UserProjectListTasks(Integer pId , int uId) {
        Session session = sessionFactory.openSession();
        System.out.println("UserProjectListTasks taskimp");
        return session.createCriteria(Task.class)
                .add(Restrictions.eq("taskProject",pId))
                .add(Restrictions.eq("userTask",uId))
                .list();   
    }

    @Override
    public List<Task> listTasksByProyect(Integer ProjectId) {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Task.class)
                .add(Restrictions.eq("taskProject",ProjectId))
                .add(Restrictions.eq("status","IN PROGRESS"))
                .list();  
    }
    @Override
    public void mergeTask(Task t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(t);
        session.getTransaction().commit();
    }


    @Override
    public List<Task> ListTasksByAUser(int uId) {
        Session session = sessionFactory.openSession();
        System.out.println("UserProjectListTasks taskimp");
        return session.createCriteria(Task.class)
                .add(Restrictions.eq("userTask",uId))
                .add(Restrictions.eq("status","IN PROGRESS"))
                .list();   
    }
}
