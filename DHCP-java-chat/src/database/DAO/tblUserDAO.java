/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.Tbluser;
import database.Utility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblUserDAO {

    public EntityManager em = project2Utility.createConnect();

    public boolean createUser(Tbluser user) {
        em.getTransaction().begin();
        Tbluser userfind = em.find(Tbluser.class, user.getUserName());
        em.getTransaction().commit();
        if (userfind == null) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("Tao User thanh cong");
            return true;
        } else {
            System.out.println("Username da ton tai");
            return false;
        }
    }

    public List<Tbluser> findByFullName(String target,String username) {
        try {
            List<Tbluser> list = null;
            list = em.createNativeQuery("Select * from tbluser t where (t.user_name like '%"+target+"%' OR t.full_name like '%"+target+"%') AND t.user_name != '"+username+
                    "' AND t.user_name NOT IN (SELECT user_name_1 FROM tblfriend WHERE user_name_2 = '"+username+
                    "') AND t.user_name NOT IN (SELECT user_name_2 FROM tblfriend WHERE user_name_1 = '"+username+"')", Tbluser.class).getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public Tbluser findByName(String username) {
        em.getTransaction().begin();
        Tbluser user = em.find(Tbluser.class, username);
        em.getTransaction().commit();
        return user;
    }

    public List<Tbluser> findAll() {
        try {
            List<Tbluser> list = em.createNamedQuery("Tbluser.findAll", Tbluser.class).getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public void updateUser(Tbluser user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteUser(String username) {
        em.getTransaction().begin();
        Tbluser user = em.find(Tbluser.class, username);
        em.remove(user);
        em.getTransaction().commit();
    }

}
