/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Avancement;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lina
 */
@Stateless
public class AvancementFacade extends AbstractFacade<Avancement> {

    @PersistenceContext(unitName = "GRHv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvancementFacade() {
        super(Avancement.class);
    }
    
}
