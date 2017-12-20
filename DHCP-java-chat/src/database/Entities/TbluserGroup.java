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
@Table(name = "tbluser_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbluserGroup.findAll", query = "SELECT t FROM TbluserGroup t")
    , @NamedQuery(name = "TbluserGroup.findByGroupId", query = "SELECT t FROM TbluserGroup t WHERE t.tbluserGroupPK.groupId = :groupId")
    , @NamedQuery(name = "TbluserGroup.findByUserName", query = "SELECT t FROM TbluserGroup t WHERE t.tbluserGroupPK.userName = :userName")
    , @NamedQuery(name = "TbluserGroup.findByDateTime", query = "SELECT t FROM TbluserGroup t WHERE t.tbluserGroupPK.dateTime = :dateTime")
    , @NamedQuery(name = "TbluserGroup.findByContent", query = "SELECT t FROM TbluserGroup t WHERE t.content = :content")})
public class TbluserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbluserGroupPK tbluserGroupPK;
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tbluser tbluser;
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tblgroup tblgroup;

    public TbluserGroup() {
    }

    public TbluserGroup(TbluserGroupPK tbluserGroupPK) {
        this.tbluserGroupPK = tbluserGroupPK;
    }

    public TbluserGroup(int groupId, String userName, Date dateTime, String content) {
        this.tbluserGroupPK = new TbluserGroupPK(groupId, userName, dateTime);
        this.content= content;
    }

    public TbluserGroupPK getTbluserGroupPK() {
        return tbluserGroupPK;
    }

    public void setTbluserGroupPK(TbluserGroupPK tbluserGroupPK) {
        this.tbluserGroupPK = tbluserGroupPK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tbluser getTbluser() {
        return tbluser;
    }

    public void setTbluser(Tbluser tbluser) {
        this.tbluser = tbluser;
    }

    public Tblgroup getTblgroup() {
        return tblgroup;
    }

    public void setTblgroup(Tblgroup tblgroup) {
        this.tblgroup = tblgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbluserGroupPK != null ? tbluserGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbluserGroup)) {
            return false;
        }
        TbluserGroup other = (TbluserGroup) object;
        if ((this.tbluserGroupPK == null && other.tbluserGroupPK != null) || (this.tbluserGroupPK != null && !this.tbluserGroupPK.equals(other.tbluserGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Entities.TbluserGroup[ tbluserGroupPK=" + tbluserGroupPK + " ]";
    }
    
}
