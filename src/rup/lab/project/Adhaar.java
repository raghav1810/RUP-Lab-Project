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
@Table(name = "Adhaar", catalog = "javastuff", schema = "")
@NamedQueries({
    @NamedQuery(name = "Adhaar.findAll", query = "SELECT a FROM Adhaar a")
    , @NamedQuery(name = "Adhaar.findByName", query = "SELECT a FROM Adhaar a WHERE a.name = :name")
    , @NamedQuery(name = "Adhaar.findByAid", query = "SELECT a FROM Adhaar a WHERE a.aid = :aid")
    , @NamedQuery(name = "Adhaar.findByAddr", query = "SELECT a FROM Adhaar a WHERE a.addr = :addr")
    , @NamedQuery(name = "Adhaar.findByDob", query = "SELECT a FROM Adhaar a WHERE a.dob = :dob")
    , @NamedQuery(name = "Adhaar.findByOther", query = "SELECT a FROM Adhaar a WHERE a.other = :other")})
public class Adhaar implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Id
    @Basic(optional = false)
    @Column(name = "aid")
    private Integer aid;
    @Column(name = "addr")
    private String addr;
    @Column(name = "dob")
    private String dob;
    @Column(name = "other")
    private String other;

    public Adhaar() {
    }

    public Adhaar(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        Integer oldAid = this.aid;
        this.aid = aid;
        changeSupport.firePropertyChange("aid", oldAid, aid);
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        String oldAddr = this.addr;
        this.addr = addr;
        changeSupport.firePropertyChange("addr", oldAddr, addr);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        String oldDob = this.dob;
        this.dob = dob;
        changeSupport.firePropertyChange("dob", oldDob, dob);
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        String oldOther = this.other;
        this.other = other;
        changeSupport.firePropertyChange("other", oldOther, other);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adhaar)) {
            return false;
        }
        Adhaar other = (Adhaar) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rup.lab.project.Adhaar[ aid=" + aid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
