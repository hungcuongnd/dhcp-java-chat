/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackages;

import database.DAO.ListfriendDAO;
import database.DAO.tblFriendDAO;
import database.DAO.tblUserDAO;
import database.DAO.tblUserUserDAO;
import database.Entities.Tbluser;
import database.Entities.TbluserUser;
import java.util.List;
import java.util.Vector;
import utilities.UserSimple;

/**
 *
 * @author Administrator
 */
public class test {
    
    public static void main(String[] args) {
        tblUserDAO dao = new tblUserDAO();
        List<Tbluser> ew = dao.findByFullName("C");
        
        for (Tbluser tbluser : ew) {
            System.out.println(tbluser.getFullName());
        }
    }
    
    
}
