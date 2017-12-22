/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "tbluser_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbluserUser.findAll", query = "SELECT t FROM TbluserUser t")
    , @NamedQuery(name = "TbluserUser.findByUserName1", query = "SELECT t FROM TbluserUser t WHERE t.tbluserUserPK.userName1 = :userName1")
    , @NamedQuery(name = "TbluserUser.findByUserName2", query = "SELECT t FROM TbluserUser t WHERE t.tbluserUserPK.userName2 = :userName2")
    , @NamedQuery(name = "TbluserUser.findByDateTime", query = "SELECT t FROM TbluserUser t WHERE t.tbluserUserPK.dateTime = :dateTime")})
public class TbluserUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbluserUserPK tbluserUserPK;
    @Lob
    @Column(name = "content")
    private String content;
    
    @Column(name = "status")
    private Short status;

    @Lob
    @Column(name = "sas")
    private String sas;

    
    @JoinColumn(name = "user_name_1", referencedColumnName = "user_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbluser tbluser;
    @JoinColumn(name = "user_name_2", referencedColumnName = "user_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbluser tbluser1;

    public TbluserUser() {
    }

    public TbluserUser(TbluserUserPK tbluserUserPK, String content, Tbluser tbluser, Tbluser tbluser1, String sas) {
        this.tbluserUserPK = tbluserUserPK;
        this.content = content;
        this.tbluser = tbluser;
        this.tbluser1 = tbluser1;
        this.sas = sas;
    }
    
    

    public TbluserUser(TbluserUserPK tbluserUserPK) {
        this.tbluserUserPK = tbluserUserPK;
    }

    public TbluserUser(String userName1, String userName2, long dateTime, String content,Short status,String sas) {
        this.tbluserUserPK = new TbluserUserPK(userName1, userName2, dateTime);
        this.content = content;
        this.status = status;
        this.sas = sas;
    }

    public TbluserUserPK getTbluserUserPK() {
        return tbluserUserPK;
    }

    public void setTbluserUserPK(TbluserUserPK tbluserUserPK) {
        this.tbluserUserPK = tbluserUserPK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
    
    public String getSas() {
        return sas;
    }

    public void setSas(String sas) {
        this.sas = sas;
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
        hash += (tbluserUserPK != null ? tbluserUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbluserUser)) {
            return false;
        }
        TbluserUser other = (TbluserUser) object;
        if ((this.tbluserUserPK == null && other.tbluserUserPK != null) || (this.tbluserUserPK != null && !this.tbluserUserPK.equals(other.tbluserUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TbluserUser[ tbluserUserPK=" + tbluserUserPK + " ]";
    }
    
}
