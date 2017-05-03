/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Fonction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lina
 */
@Stateless
public class FonctionFacade extends AbstractFacade<Fonction> {

    @PersistenceContext(unitName = "GRHv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FonctionFacade() {
        super(Fonction.class);
    }
    
}
