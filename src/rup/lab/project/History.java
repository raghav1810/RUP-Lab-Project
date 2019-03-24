/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rup.lab.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author raghav
 */
@Entity
@Table(name = "History", catalog = "javastuff", schema = "")
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h")
    , @NamedQuery(name = "History.findByHid", query = "SELECT h FROM History h WHERE h.hid = :hid")
    , @NamedQuery(name = "History.findByAid", query = "SELECT h FROM History h WHERE h.aid = :aid")
    , @NamedQuery(name = "History.findByEid", query = "SELECT h FROM History h WHERE h.eid = :eid")})
public class History implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hid")
    private Integer hid;
    @Column(name = "aid")
    private Integer aid;
    @Column(name = "eid")
    private Integer eid;

    public History() {
    }

    public History(Integer hid) {
        this.hid = hid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        Integer oldHid = this.hid;
        this.hid = hid;
        changeSupport.firePropertyChange("hid", oldHid, hid);
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        Integer oldAid = this.aid;
        this.aid = aid;
        changeSupport.firePropertyChange("aid", oldAid, aid);
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        Integer oldEid = this.eid;
        this.eid = eid;
        changeSupport.firePropertyChange("eid", oldEid, eid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hid != null ? hid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.hid == null && other.hid != null) || (this.hid != null && !this.hid.equals(other.hid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rup.lab.project.History[ hid=" + hid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
