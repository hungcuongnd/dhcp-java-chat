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
import javax.persistence.Id;
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
@Table(name = "tbluser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbluser.findAll", query = "SELECT t FROM Tbluser t")
    , @NamedQuery(name = "Tbluser.findByUserName", query = "SELECT t FROM Tbluser t WHERE t.userName = :userName")
    , @NamedQuery(name = "Tbluser.findByFullName", query = "SELECT t FROM Tbluser t WHERE t.fullName = :fullName")
    , @NamedQuery(name = "Tbluser.findByPassWord", query = "SELECT t FROM Tbluser t WHERE t.passWord = :passWord")
    , @NamedQuery(name = "Tbluser.findByAvartar", query = "SELECT t FROM Tbluser t WHERE t.avartar = :avartar")})
public class Tbluser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "avartar")
    private String avartar;
    @Column(name = "slogan")
    private String slogan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbluser")
    private Collection<Tblfriend> tblfriendCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbluser1")
    private Collection<Tblfriend> tblfriendCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbluser")
    private Collection<TbluserUser> tbluserUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbluser1")
    private Collection<TbluserUser> tbluserUserCollection1;
    @OneToMany(mappedBy = "userName")
    private Collection<Tblgroup> tblgroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbluser")
    private Collection<TbluserGroup> tbluserGroupCollection;

    public Tbluser() {
    }

    public Tbluser(String userName) {
        this.userName = userName;
    }
    
    public Tbluser(String userName,String pass,String fullname) {
        this.userName = userName;
        this.fullName = fullname;
        this.passWord = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    
    @XmlTransient
    public Collection<Tblfriend> getTblfriendCollection() {
        return tblfriendCollection;
    }

    public void setTblfriendCollection(Collection<Tblfriend> tblfriendCollection) {
        this.tblfriendCollection = tblfriendCollection;
    }

    @XmlTransient
    public Collection<Tblfriend> getTblfriendCollection1() {
        return tblfriendCollection1;
    }

    public void setTblfriendCollection1(Collection<Tblfriend> tblfriendCollection1) {
        this.tblfriendCollection1 = tblfriendCollection1;
    }

    @XmlTransient
    public Collection<TbluserUser> getTbluserUserCollection() {
        return tbluserUserCollection;
    }

    public void setTbluserUserCollection(Collection<TbluserUser> tbluserUserCollection) {
        this.tbluserUserCollection = tbluserUserCollection;
    }

    @XmlTransient
    public Collection<TbluserUser> getTbluserUserCollection1() {
        return tbluserUserCollection1;
    }

    public void setTbluserUserCollection1(Collection<TbluserUser> tbluserUserCollection1) {
        this.tbluserUserCollection1 = tbluserUserCollection1;
    }

    @XmlTransient
    public Collection<Tblgroup> getTblgroupCollection() {
        return tblgroupCollection;
    }

    public void setTblgroupCollection(Collection<Tblgroup> tblgroupCollection) {
        this.tblgroupCollection = tblgroupCollection;
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
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbluser)) {
            return false;
        }
        Tbluser other = (Tbluser) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Entities.Tbluser[ userName=" + userName + " ]";
    }
    
}
