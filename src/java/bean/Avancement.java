/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lina
 */
@Entity
public class Avancement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Employe employe;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAvancement = new Date();
    @ManyToOne
    private Echelon echelonSource;
    @ManyToOne
    private Echelon echelonDestination;

    public Avancement(Long id, Employe employe, Date dateAvancement, Echelon echelonSource, Echelon echelonDest) {
        this.id = id;
        this.employe = employe;
        this.dateAvancement = dateAvancement;
        this.echelonSource = echelonSource;
        this.echelonDestination = echelonDest;
    }

    public Avancement() {
    }

    public Employe getEmploye() {
        if(employe==null){
            employe = new Employe();
        }
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Date getDateAvancement() {
        return dateAvancement;
    }

    public void setDateAvancement(Date dateAvancement) {
        this.dateAvancement = dateAvancement;
    }

    public Echelon getEchelonSource() {
        /*if(echelonSource == null){
            echelonSource = new Echelon();
        }*/
        return echelonSource;
    }

    public void setEchelonSource(Echelon echelonSource) {
        this.echelonSource = echelonSource;
    }

    public Echelon getEchelonDestination() {
        if(echelonDestination == null){
            echelonDestination = new Echelon();
        }
        return echelonDestination;
    }

    public void setEchelonDestination(Echelon echelonDestination) {
        this.echelonDestination = echelonDestination;
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
        if (!(object instanceof Avancement)) {
            return false;
        }
        Avancement other = (Avancement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Avancement[ id=" + id + " ]";
    }
    
}
