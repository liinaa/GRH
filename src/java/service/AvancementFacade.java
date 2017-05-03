/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Avancement;
import javax.ejb.EJB;
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
     @EJB
    private EmployeFacade employeFacade;
    @EJB
    private EchelonFacade echelonFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvancementFacade() {
        super(Avancement.class);
    }
    public int save(Avancement avancement){
        if (avancement==null){
            return -1;
        }
        else if(avancement.getEmploye()==null){
            return -2;
        }
        else if(avancement.getEmploye().getEchelon()==null){
            return -3;
        }
        else if(avancement.getEmploye().getEchelon().getEchelle()==null){
            return -4;
        }
        else {
            echelonFacade.findByNext(avancement.getEmploye().getEchelon());               
            create(avancement);
            avancement.getEmploye().setEchelon(avancement.getEchelonDestination());
            employeFacade.edit(avancement.getEmploye());
                return 1;         
                }
    }

    
}
