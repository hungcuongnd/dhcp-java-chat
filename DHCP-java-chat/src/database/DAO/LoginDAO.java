/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.Tbluser;
import database.Utility.project2Utility;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class LoginDAO {

    public EntityManager em = project2Utility.createConnect();

    public Tbluser checkLogin(String username, String password) {
        try {
            Tbluser userlogin = em.createQuery("SELECT t FROM Tbluser t WHERE t.userName = '"
                    + username + "' AND t.passWord = '" + password + "'", Tbluser.class).getSingleResult();
            if (userlogin != null) {
                return userlogin;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Login fail!");
            return null;
        }
    }

}
