/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.Tbluser;
import database.Utility.project2Utility;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import utilities.UserSimple;

/**
 *
 * @author Administrator
 */
public class ListfriendDAO {

    public EntityManager em = project2Utility.createConnect();

    public List<Tbluser> getListFriend(String username) {
        try {
            List<Tbluser> userfriend = em.createQuery("SELECT u from Tbluser u JOIN Tblfriend f WHERE (u.userName = f.tblfriendPK.userName2 AND f.tblfriendPK.userName1 = '" + username + "')"
                    + " OR (u.userName = f.tblfriendPK.userName1 AND f.tblfriendPK.userName2 = '" + username + "') ", Tbluser.class).getResultList();
            return userfriend;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UserSimple> getListFriendExcepted(String username) {
        ArrayList<UserSimple> listusersimple = new ArrayList<UserSimple>();
        try {
            List<Tbluser> userfriend = em.createNativeQuery("SELECT * from tbluser u JOIN tblfriend f WHERE ((u.user_name = f.user_name_2 AND f.user_name_1 = '" + username + "')"
                    + " OR (u.user_name = f.user_name_1 AND f.user_name_2 = '" + username + "')) AND f.status = 1", Tbluser.class).getResultList();

            for (Tbluser tbluser : userfriend) {
                listusersimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), true, true));
            }

            return listusersimple;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UserSimple> getListFriendNotExcepted(String username) {
        try {
            ArrayList<UserSimple> listusersimple = new ArrayList<UserSimple>();
            List<Tbluser> userfriendtoexcept = em.createNativeQuery("SELECT * from tbluser u JOIN tblfriend f WHERE u.user_name = f.user_name_2 AND f.user_name_1 = '" + username + "' AND f.status = 0", Tbluser.class).getResultList();
            if (userfriendtoexcept != null) {
                for (Tbluser tbluser : userfriendtoexcept) {
                    listusersimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), true, false));
                }
            }
            
            List<Tbluser> userfriendnottoexcept = em.createNativeQuery("SELECT * from tbluser u JOIN tblfriend f WHERE u.user_name = f.user_name_1 AND f.user_name_2 = '" + username + "' AND f.status = 0", Tbluser.class).getResultList();
            if (userfriendnottoexcept != null) {
                for (Tbluser tbluser : userfriendnottoexcept) {
                    listusersimple.add(new UserSimple(tbluser.getUserName(), tbluser.getFullName(), true, true));
                }
            }
            return listusersimple;
        } catch (Exception e) {
            return null;
        }
    }

}
