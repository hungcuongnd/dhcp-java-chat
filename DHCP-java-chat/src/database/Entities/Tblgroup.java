/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "tblgroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblgroup.findAll", query = "SELECT t FROM Tblgroup t")
    , @NamedQuery(name = "Tblgroup.findByGroupId", query = "SELECT t FROM Tblgroup t WHERE t.groupId = :groupId")
    , @NamedQuery(name = "Tblgroup.findByGroupName", query = "SELECT t FROM Tblgroup t WHERE t.groupName = :groupName")})
public class Tblgroup implements Serializable { 

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "group_name")
    private String groupName;
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    @ManyToOne
    private Tbluser userName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblgroup")
    private Collection<TbluserGroup> tbluserGroupCollection;

    public Tblgroup() {
    } 

    public Tblgroup(String groupName, Tbluser userName) {
        this.groupName = groupName;
        this.userName = userName;
    }

    public Tblgroup(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Tbluser getUserName() {
        return userName;
    }

    public void setUserName(Tbluser userName) {
        this.userName = userName;
    }

    @XmlTransient
    public Collection<TbluserGroup> getTbluserGroupCollection() {
        return tbluserGroupCollection;
    }

    public void setTbluserGroupCollection(Collection<TbluserGroup> tbluserGroupCollection) {
        this.tbluserGroupCollection = tbluserGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblgroup)) {
            return false;
        }
        Tblgroup other = (Tblgroup) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tblgroup[ groupId=" + groupId + " ]";
    }
    
}
