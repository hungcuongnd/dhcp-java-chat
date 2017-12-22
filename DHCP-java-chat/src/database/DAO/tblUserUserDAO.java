/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.DAO;

import database.Entities.TbluserUser;
import database.Utility.project2Utility;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class tblUserUserDAO {

    public EntityManager em = project2Utility.createConnect();

    public void saveMassage1v1(String username1, String username2, String content, int status, String sas) {
        //lay thoi diem hien tai luu vao
        long millisStart = Calendar.getInstance().getTimeInMillis();
        TbluserUser message = new TbluserUser(username1, username2, millisStart, content, (short) status, sas);

        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }

    public List<TbluserUser> checkUnreadMessage1v1(String username) {
        try {
            List<TbluserUser> list = em.createQuery("SELECT t FROM TbluserUser t WHERE t.tbluserUserPK.userName2 = '"
                    + username + "' AND t.status = 0").getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<TbluserUser> getAllMessage1v1(String username1, String username2, int so) {
        try {
            // lay tat ca cac tin nhan trong 1 cuoc hoi thoai 1 v 1
            List<TbluserUser> list = em.createNativeQuery("SELECT * FROM Tbluser_User t WHERE (t.user_name_1 = '"
                    + username1 + "' AND t.user_name_2 = '" + username2 + "') OR (t.user_name_1 = '"
                    + username2 + "' AND t.user_name_2 = '" + username1 + "') ORDER BY t.date_time DESC LIMIT " + so + ",10", TbluserUser.class).getResultList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

}
