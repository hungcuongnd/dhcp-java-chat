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
public class TbluserGroupPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "group_id")
    private int groupId;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;

    public TbluserGroupPK() {
    }

    public TbluserGroupPK(int groupId, String userName) {
        this.groupId = groupId;
        this.userName = userName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) groupId;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbluserGroupPK)) {
            return false;
        }
        TbluserGroupPK other = (TbluserGroupPK) object;
        if (this.groupId != other.groupId) {
            return false;
        }
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Entities.TbluserGroupPK[ groupId=" + groupId + ", userName=" + userName + " ]";
    }
    
}
