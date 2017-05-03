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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Lina
 */
@Entity
public class Fonction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    @ManyToMany
    private List<Employe> employes;
    @OneToMany(mappedBy = "fonction")
    private List<Fonction> sous_elements;
    @ManyToOne
    private Fonction fonction;
    @ManyToOne
    private Departement departement;

    public Departement getDepartement() {
        if(departement==null){
            departement = new Departement();
        }
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Fonction getFonction() {
        if(fonction==null){
            fonction = new Fonction();
        }
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public Fonction(String nom) {
        this.nom = nom;
    }

    public Fonction() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public List<Fonction> getSous_elements() {
        return sous_elements;
    }

    public void setSous_elements(List<Fonction> sous_elements) {
        this.sous_elements = sous_elements;
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
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Fonction[ id=" + id + " ]";
    }
    
}
