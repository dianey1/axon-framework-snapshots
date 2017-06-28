/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softech.flexbet.query;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "t_agent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAgent.findAll", query = "SELECT t FROM TAgent t"),
    @NamedQuery(name = "TAgent.findById", query = "SELECT t FROM TAgent t WHERE t.id = :id"),
    @NamedQuery(name = "TAgent.findByDbBalance", query = "SELECT t FROM TAgent t WHERE t.dbBalance = :dbBalance"),
    @NamedQuery(name = "TAgent.findByStrName", query = "SELECT t FROM TAgent t WHERE t.strName = :strName")})
public class TAgent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_balance", precision = 22)
    private Double dbBalance;
    @Size(max = 150)
    @Column(name = "str_Name", length = 150)
    private String strName;

    public TAgent() {
    }

    public TAgent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDbBalance() {
        return dbBalance;
    }

    public void setDbBalance(Double dbBalance) {
        this.dbBalance = dbBalance;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
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
        if (!(object instanceof TAgent)) {
            return false;
        }
        TAgent other = (TAgent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.softech.flexbet.query.TAgent[ id=" + id + " ]";
    }
    
}
