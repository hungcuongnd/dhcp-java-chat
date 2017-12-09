/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrator
 */
@Embeddable
public class TblfriendPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_name_1")
    private String userName1;
    @Basic(optional = false)
    @Column(name = "user_name_2")
    private String userName2;

    public TblfriendPK() {
    }

    public TblfriendPK(String userName1, String userName2) {
        this.userName1 = userName1;
        this.userName2 = userName2;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName1 != null ? userName1.hashCode() : 0);
        hash += (userName2 != null ? userName2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblfriendPK)) {
            return false;
        }
        TblfriendPK other = (TblfriendPK) object;
        if ((this.userName1 == null && other.userName1 != null) || (this.userName1 != null && !this.userName1.equals(other.userName1))) {
            return false;
        }
        if ((this.userName2 == null && other.userName2 != null) || (this.userName2 != null && !this.userName2.equals(other.userName2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Entities.TblfriendPK[ userName1=" + userName1 + ", userName2=" + userName2 + " ]";
    }
    
}
