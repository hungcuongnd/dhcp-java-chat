/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "tblicon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblicon.findAll", query = "SELECT t FROM Tblicon t")
    , @NamedQuery(name = "Tblicon.findByIconText", query = "SELECT t FROM Tblicon t WHERE t.iconText = :iconText")
    , @NamedQuery(name = "Tblicon.findByIconImage", query = "SELECT t FROM Tblicon t WHERE t.iconImage = :iconImage")})
public class Tblicon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "icon_text")
    private String iconText;
    @Column(name = "icon_image")
    private String iconImage;

    public Tblicon() {
    }

    public Tblicon(String iconText) {
        this.iconText = iconText;
    }

    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText) {
        this.iconText = iconText;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iconText != null ? iconText.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblicon)) {
            return false;
        }
        Tblicon other = (Tblicon) object;
        if ((this.iconText == null && other.iconText != null) || (this.iconText != null && !this.iconText.equals(other.iconText))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Entities.Tblicon[ iconText=" + iconText + " ]";
    }
    
}
