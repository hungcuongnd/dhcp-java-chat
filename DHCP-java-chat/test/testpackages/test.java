/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackages;

import database.DAO.tblUserUserDAO;
import database.Entities.TbluserUser;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class test {
    public static void main(String[] args) {
        tblUserUserDAO u = new tblUserUserDAO();
        List<TbluserUser> list = u.getAllMessage1v1("phuong", "hieu",0);
        for (TbluserUser tbluserUser : list) {
            System.out.println(tbluserUser.getContent() + tbluserUser.getTbluserUserPK().getDateTime());
        }
        
        //u.saveMassage1v1("phuong", "hieu", "đm hiếu chó", 1, "string sas");
    }
}
