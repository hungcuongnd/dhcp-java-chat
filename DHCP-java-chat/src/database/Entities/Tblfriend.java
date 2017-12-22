/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "tblfriend")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblfriend.findAll", query = "SELECT t FROM Tblfriend t")
    , @NamedQuery(name = "Tblfriend.findByUserName1", query = "SELECT t FROM Tblfriend t WHERE t.tblfriendPK.userName1 = :userName1")
    , @NamedQuery(name = "Tblfriend.findByUserName2", query = "SELECT t FROM Tblfriend t WHERE t.tblfriendPK.userName2 = :userName2")
    , @NamedQuery(name = "Tblfriend.findByStatus", query = "SELECT t FROM Tblfriend t WHERE t.status = :status")})
public class Tblfriend implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblfriendPK tblfriendPK;
    
    
    
    @JoinColumn(name = "user_name_2", referencedColumnName = "user_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbluser tbluser;
    @JoinColumn(name = "user_name_1", referencedColumnName = "user_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbluser tbluser1;
    
    
    @Column(name = "status")
    private Short status;

    public Tblfriend() {
    }

    public Tblfriend(TblfriendPK tblfriendPK) {
        this.tblfriendPK = tblfriendPK;
    }

    public Tblfriend(String userName1, String userName2, Short status) {
        this.tblfriendPK = new TblfriendPK(userName1, userName2);
        this.status = status;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public TblfriendPK getTblfriendPK() {
        return tblfriendPK;
    }

    public void setTblfriendPK(TblfriendPK tblfriendPK) {
        this.tblfriendPK = tblfriendPK;
    }

    public Tbluser getTbluser() {
        return tbluser;
    }

    public void setTbluser(Tbluser tbluser) {
        this.tbluser = tbluser;
    }

    public Tbluser getTbluser1() {
        return tbluser1;
    }

    public void setTbluser1(Tbluser tbluser1) {
        this.tbluser1 = tbluser1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblfriendPK != null ? tblfriendPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblfriend)) {
            return false;
        }
        Tblfriend other = (Tblfriend) object;
        if ((this.tblfriendPK == null && other.tblfriendPK != null) || (this.tblfriendPK != null && !this.tblfriendPK.equals(other.tblfriendPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tblfriend[ tblfriendPK=" + tblfriendPK + " ]";
    }
    
}
