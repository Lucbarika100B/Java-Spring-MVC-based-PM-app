
package com.tesisiii.DAO;


import com.tesisiii.model.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class UserImp implements UserInterface{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addUser(Usuario u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        u.setPooPId("0");
        System.out.println(" user id : " + u.getId());
        session.save(u);
        System.out.println("aqui estamos");
        session.getTransaction().commit();
    }

    @Override
    public void removeUser(Usuario u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        u.setStatus("N");
        session.update(u);
        System.out.println("aqui estamos en el removeUser del UserImp");
        session.getTransaction().commit();
    }

    @Override
    public Usuario getUser(Integer id) {
        Session session = sessionFactory.openSession();
        return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public void editUser(Usuario u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(u);
        System.out.println("aqui estamos en el edit del UserImp");
        session.getTransaction().commit();
    }

    @Override
    public List<Usuario> listUser() {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de managers");
        return session.createCriteria(Usuario.class).add(Restrictions.eq("status", "Y")).list();   
    }
    
    @Override
     public List<Usuario> listManager() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Usuario.class)
                    .add(Restrictions.eq("status", "Y"))    
                    .add(Restrictions.eq("profile", "PM"))
                    .list();   
    }
     
    @Override
    public List<Usuario> listUserDeleted() {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de USURIOS DELETED");
        return session.createCriteria(Usuario.class).add(Restrictions.eq("status", "N")).list();   
    } 
    
    @Override
    public List<Usuario> listAllUser() {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de ALL USERS");
        return session.createCriteria(Usuario.class)
                .add(Restrictions.ne("profile","Initial"))
                .list();   
    }
    
    @Override
     public Usuario getUserIdByLogin(String login) {
        Session session = sessionFactory.openSession();
        System.out.println("buscando id getUserIdByLogin : " + login);
        return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("login",login)).uniqueResult();
    }

    @Override
    public List<Usuario> listUserIdByManager(Integer managerId) {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de listUserIdByManager");
        System.out.println(" managerId : " + managerId);
        return session.createCriteria(Usuario.class)
                .add(Restrictions.ne("profile","Initial"))
                .add(Restrictions.eq("pooPId","0"))
                .add(Restrictions.eq("managerId", Integer.toString(managerId)))
                .list();  
    }
    
    @Override
    public List<Usuario> listUserAssigned(Integer managerId, String pooPIdValor) {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de listUserIdByManager");
        System.out.println(" managerId : " + managerId);
        return session.createCriteria(Usuario.class)
                .add(Restrictions.ne("profile","Initial"))
                .add(Restrictions.eq("pooPId",pooPIdValor))
                .add(Restrictions.eq("managerId", Integer.toString(managerId)))
                .list();  
    }        

    @Override
    public List<Usuario> listUsuariosByPool(String poolid) {
        Session session = sessionFactory.openSession();
        System.out.println("estoy en la lista de listUsuariosByPool");
        return session.createCriteria(Usuario.class).add(Restrictions.eq("pooPId",poolid)).list();  
    }

    @Override
    public void mergeUser(Usuario u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(u);
        System.out.println("aqui estamos en el Merge del UserImp");
        session.getTransaction().commit();
    }
    
    
}
