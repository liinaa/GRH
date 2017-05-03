/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Lina
 */
@Entity
public class Echelle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numero;
    private String nomEchelle;
    @OneToMany(mappedBy = "echelle")
    private List<Echelon> echelons;


    public Echelle() {
    }

    public Echelle(Long id, int numero, String nomEchelle) {
        this.id = id;
        this.setNumero(numero);
        this.nomEchelle = nomEchelle;
    }

    public String getNomEchelle() {
        return nomEchelle;
    }

    public void setNomEchelle(String nomEchelle) {
        this.nomEchelle = nomEchelle;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero>=1 && numero<=11)
        this.numero = numero;
    }

    public List<Echelon> getEchelons() {
        return echelons;
    }

    public void setEchelons(List<Echelon> echelons) {
        this.echelons = echelons;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Echelle)) {
            return false;
        }
        Echelle other = (Echelle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Echelle[ id=" + id + " ]";
    }
    
}
