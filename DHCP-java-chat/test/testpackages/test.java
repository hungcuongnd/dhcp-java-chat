/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackages;

import database.DAO.tblFriendDAO;
import database.DAO.tblUserDAO;
import database.DAO.tblUserUserDAO;
import database.Entities.Tbluser;
import database.Entities.TbluserUser;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class test {
    
    public static void main(String[] args) {
        tblUserDAO dao = new tblUserDAO();
        Tbluser user1 = new Tbluser("phuong");
        user1.setFullName("Lê Tri Phương");
        dao.updateUser(user1);
    }
    
    
}
