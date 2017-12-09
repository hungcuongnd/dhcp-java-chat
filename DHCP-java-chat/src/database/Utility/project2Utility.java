/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrator
 */
public class project2Utility {
    public static EntityManager em;
    public static EntityManagerFactory emf;
    
    public static EntityManager createConnect(){
        emf = Persistence.createEntityManagerFactory("chat-multiple-clientPU");
        em = emf.createEntityManager();
        return em;
    }
}
