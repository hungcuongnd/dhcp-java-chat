/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.Tblgroup;
import database.Entities.Tbluser;
import database.Entities.TbluserGroup;
import database.Utility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblGroupDAO {
    public EntityManager em = project2Utility.createConnect();
    public tblUserDAO userDAO = new tblUserDAO();
    
    public Tblgroup createGroup(String groupname, String username){
        //tao group co username chu phong
        Tbluser user = userDAO.findByName(username);
        Tblgroup group = new Tblgroup(groupname, user);
        em.getTransaction().begin();
        em.persist(group);
        em.flush();
        em.getTransaction().commit();
        
        //luu bang trung gian voi loi nhan tạm
        java.util.Date time = new java.util.Date();
        TbluserGroup usergroup = new TbluserGroup(group.getGroupId(), username,time, "HI!!!");
        em.getTransaction().begin();
        em.persist(usergroup);
        em.getTransaction().commit();
        return group;
    }
    
    public Tblgroup updateGroup(int id,String groupname){
        Tblgroup group = em.find(Tblgroup.class, id);
        group.setGroupName(groupname);
        em.getTransaction().begin();
        em.merge(group);
        em.flush();
        em.getTransaction().commit();
        return group;
    }
    
    public Tblgroup findById(int id){
        return em.find(Tblgroup.class, id);
    }
    
    public void deleteGroup(int id){
        Tblgroup group = em.find(Tblgroup.class, id);
        em.getTransaction().begin();
        em.remove(group);
        em.getTransaction().commit();
    }
    
    public List<Tblgroup> getGroupListByUsername(String username){
    //List<Tblgroup> list = em.createQuery("SELECT g FROM Tblgroup g JOIN TbluserGroup ug WHERE ",Tblgroup.class).getResultList();
        return null;
    }
}
