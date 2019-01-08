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
@Table(name = "todo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t")
    , @NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id")
    , @NamedQuery(name = "Todo.findByName", query = "SELECT t FROM Todo t WHERE t.name = :name")
    , @NamedQuery(name = "Todo.findByDetail", query = "SELECT t FROM Todo t WHERE t.detail = :detail")
    , @NamedQuery(name = "Todo.findByStatusId", query = "SELECT t FROM Todo t WHERE t.status = :statusId")
    , @NamedQuery(name = "Todo.findByCrtdate", query = "SELECT t FROM Todo t WHERE t.crtdate = :crtdate")
    , @NamedQuery(name = "Todo.findByDueDate", query = "SELECT t FROM Todo t WHERE t.dueDate = :dueDate")
    , @NamedQuery(name = "Todo.findByListId", query = "SELECT t FROM Todo t  WHERE t.listId = :listId")
    , @NamedQuery(name = "Todo.findDependableTodoList", query = "SELECT t FROM Todo t  WHERE t.listId = :listId and t.id <> :todoId and t.dependedTodoId <> :todoId")
    , @NamedQuery(name = "Todo.findByNameAndListId", query = "SELECT t FROM Todo t WHERE t.listId = :listId and t.name=:name")
    , @NamedQuery(name = "Todo.deleteBylistId", query = "DELETE FROM Todo t WHERE t.listId = :listId")
    , @NamedQuery(name = "Todo.findExpired", query = "SELECT t FROM Todo t WHERE t.dueDate < CURRENT_DATE and t.status <> 'Completed'")
    , @NamedQuery(name = "Todo.findByDependedTodoId", query = "SELECT t FROM Todo t WHERE t.dependedTodoId = :dependedTodoId")})
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "detail")
    private String detail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statusId")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "crtdate")
    @Temporal(TemporalType.DATE)
    private Date crtdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "listId")
    private Integer listId;
    @Basic(optional = false)
    @Column(name = "dependedTodoId")
    private String dependedTodoId;

    public Todo() {
    }

    public Todo(Integer id) {
        this.id = id;
    }

    public Todo(Integer id, String name, String detail, String statusId, Date crtdate, Date dueDate, Integer listId, String dependedTodoId) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.status = statusId;
        this.crtdate = crtdate;
        this.dueDate = dueDate;
        this.listId = listId;
        this.dependedTodoId = dependedTodoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCrtdate() {
        return crtdate;
    }

    public void setCrtdate(Date crtdate) {
        this.crtdate = crtdate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getDependedTodoId() {
        return dependedTodoId;
    }

    public void setDependedTodoId(String dependedTodoId) {
        this.dependedTodoId = dependedTodoId;
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
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.local.todoserviceside.tables.Todo[ id=" + id + " ]";
    }
    
}
