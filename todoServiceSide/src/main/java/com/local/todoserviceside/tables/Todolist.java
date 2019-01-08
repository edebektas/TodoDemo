/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.todoserviceside.tables;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Laughmare
 */
@Entity
@Table(name = "todolist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todolist.findAll", query = "SELECT t FROM Todolist t")
    , @NamedQuery(name = "Todolist.findById", query = "SELECT t FROM Todolist t WHERE t.id = :id")
    , @NamedQuery(name = "Todolist.findByOwnerId", query = "SELECT t FROM Todolist t WHERE t.ownerId = :ownerId")
    , @NamedQuery(name = "Todolist.findByNameAndUser", query = "SELECT t FROM Todolist t WHERE t.name = :name and t.ownerId = :userId")
    , @NamedQuery(name = "Todolist.findByCrtDate", query = "SELECT t FROM Todolist t WHERE t.crtDate = :crtDate")})
public class Todolist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ownerId")
    private Integer ownerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crtDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDate;

    public Todolist() {
    }

    public Todolist(Integer id) {
        this.id = id;
    }

    public Todolist(Integer id, Integer ownerId, String name, Date crtDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.crtDate = crtDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Todolist)) {
            return false;
        }
        Todolist other = (Todolist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.local.todoserviceside.tables.Todolist[ id=" + id + " ]";
    }
    
}
