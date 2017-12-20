/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.Tblfriend;
import database.Utility.project2Utility;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblFriendDAO {

    public EntityManager em = project2Utility.createConnect();

    public void saveFriend(String username1, String username2) {

        //luu yeu cau ket ban voi mac dinh là chưa accept
        Short status = 0;
        Tblfriend friend = new Tblfriend(username1, username2, status);
        em.getTransaction().begin();
        em.persist(friend);
        em.getTransaction().commit();
    }

    public List<Tblfriend> checkUnAcceptFriend(String username1) {
        //kiem tra co yeu cau ket ban nao ko 
        try {
            List<Tblfriend> list = em.createQuery("SELECT f FROM Tblfriend WHERE f.tblfriendPK.userName2 = '"
                    + username1 + "' AND t.status = 0").getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public void updateFriend(String username1, String username2, int status) {
        //update accept hay ko
        Tblfriend friend = new Tblfriend(username1, username2, (short) status);
        em.getTransaction().begin();
        em.merge(friend);
        em.getTransaction().commit();
    }

    public boolean deleteFriend(String username1, String username2) {
        //xoa yeu cau ket ban
        try {
            em.getTransaction().begin();
            em.createQuery("delete FROM Tblfriend t where t.tblfriendPK.userName1 = '" + username1 + "' AND t.tblfriendPK.userName2 = '" + username2 + "'").executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
