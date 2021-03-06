/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Echelon;
import bean.Employe;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lina
 */
@Stateless
public class EmployeFacade extends AbstractFacade<Employe> {

    @PersistenceContext(unitName = "GRHv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeFacade() {
        super(Employe.class);
    }
    
    public int findByCategorieSalaire(int min, int max){
        String req = "SELECT e FROM Employe e WHERE e.salaire>="+min+" and e.salaire<="+max;  
        List<Employe> employes = getEntityManager().createQuery(req).getResultList();
        return employes.size();
    }
    
}
