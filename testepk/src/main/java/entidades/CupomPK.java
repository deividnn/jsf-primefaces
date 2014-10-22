/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Deivid
 */
@Embeddable
public class CupomPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "loja_fk", nullable = false)
    private long lojaFk;

    public CupomPK() {
    }

    public CupomPK(long id, long lojaFk) {
        this.id = id;
        this.lojaFk = lojaFk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLojaFk() {
        return lojaFk;
    }

    public void setLojaFk(long lojaFk) {
        this.lojaFk = lojaFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) lojaFk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CupomPK)) {
            return false;
        }
        CupomPK other = (CupomPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.lojaFk != other.lojaFk) {
            return false;
        }
        return true;
    }

    
}
