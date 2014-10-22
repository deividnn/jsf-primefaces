/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Deivid
 */
@Entity
@Table(catalog = "testepk", schema = "public")
public class Cupom implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CupomPK cupomPK;
    @Size(max = 20)
    @Column(length = 20)
    private String serie;
    @JoinColumn(name = "loja_fk", referencedColumnName = "id",nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Loja loja;
    @JoinColumns({
        @JoinColumn(name = "movimento_fk", referencedColumnName = "id",nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "loja_fk", referencedColumnName = "loja_fk",nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Movimento movimento;

    public Cupom() {
    }

    public Cupom(CupomPK cupomPK) {
        this.cupomPK = cupomPK;
    }

    public Cupom(long id, long lojaFk) {
        this.cupomPK = new CupomPK(id, lojaFk);
    }

    public CupomPK getCupomPK() {
        return cupomPK;
    }

    public void setCupomPK(CupomPK cupomPK) {
        this.cupomPK = cupomPK;
    }


    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cupomPK != null ? cupomPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cupom)) {
            return false;
        }
        Cupom other = (Cupom) object;
        if ((this.cupomPK == null && other.cupomPK != null) || (this.cupomPK != null && !this.cupomPK.equals(other.cupomPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cupom{" + "cupomPK=" + cupomPK.getId()+ "-" + cupomPK.getLojaFk()+ ", serie=" + serie + ", loja=" + loja + ", movimento=" + movimento + '}';
    }
    
    
}
