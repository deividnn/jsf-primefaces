/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Deivid
 */
@Entity
@Table(catalog = "testepk", schema = "public")
public class Movimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovimentoPK movimentoPK;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimento")
    private List<Cupom> cupomList;
    @JoinColumn(name = "loja_fk", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Loja loja;
    

    public Movimento() {
    }

    public Movimento(MovimentoPK movimentoPK) {
        this.movimentoPK = movimentoPK;
    }

    public Movimento(long id, long lojaFk) {
        this.movimentoPK = new MovimentoPK(id, lojaFk);
    }

    public MovimentoPK getMovimentoPK() {
        return movimentoPK;
    }

    public void setMovimentoPK(MovimentoPK movimentoPK) {
        this.movimentoPK = movimentoPK;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

   

    public List<Cupom> getCupomList() {
        return cupomList;
    }

    public void setCupomList(List<Cupom> cupomList) {
        this.cupomList = cupomList;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimentoPK != null ? movimentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimento)) {
            return false;
        }
        Movimento other = (Movimento) object;
        if ((this.movimentoPK == null && other.movimentoPK != null) || (this.movimentoPK != null && !this.movimentoPK.equals(other.movimentoPK))) {
            return false;
        }
        return true;
    }

}
