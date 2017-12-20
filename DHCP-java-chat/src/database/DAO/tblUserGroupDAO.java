/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.TbluserGroup;
import database.Utility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblUserGroupDAO {
    public EntityManager em = project2Utility.createConnect();
    
    public void saveMessage1vsGroup(int groupid,String username,String content){
        java.util.Date time = new java.util.Date();
        TbluserGroup message = new TbluserGroup(groupid, username,time, content);
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }
    
    public List<TbluserGroup> getAllMessage1vsGroup(int group){
        try {
            List<TbluserGroup> user_group = em.createNativeQuery("SELECT * FROM tbluser_group WHERE group_id = "+group, TbluserGroup.class).getResultList();
            return user_group;
        } catch (Exception e) {
            return null;
        }
    }

}
