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
public class ListfriendDAO {
    
    public EntityManager em = project2Utility.createConnect();
    
    public List<Tbluser> getListFriend(String username){
        List<Tbluser> userfriend = em.createQuery("SELECT u from Tbluser u JOIN Tblfriend f WHERE (u.userName = f.tblfriendPK.userName2 AND f.tblfriendPK.userName1 = '"+ username+"')"
                +" OR (u.userName = f.tblfriendPK.userName1 AND f.tblfriendPK.userName2 = '"+ username+"') ", Tbluser.class).getResultList();
        return userfriend;
    }
}
