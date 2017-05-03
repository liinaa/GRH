/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Echelon;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lina
 */
@Stateless
public class EchelonFacade extends AbstractFacade<Echelon> {

    @PersistenceContext(unitName = "GRHv1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EchelonFacade() {
        super(Echelon.class);
    }
    public Echelon findByNext(Echelon echelon){
        
        String req = "SELECT e FROM Echelon e WHERE e.numero="+(echelon.getNumero()+1)+" and e.echelle.id="+echelon.getEchelle().getId();   
        List<Echelon> echelonList = getEntityManager().createQuery(req).getResultList();
        
            if(echelonList.size()==1){
                return echelonList.get(0);
            } else if(echelonList.size()==0){
                req = "SELECT e FROM Echelon e WHERE e.echelle.numero="+(echelon.getEchelle().getNumero()+1)+" and e.numero=1";
                 echelonList = getEntityManager().createQuery(req).getResultList();
                 if(echelonList.size()==1)
                 return echelonList.get(0);
                 else return echelon;
            }
            else return null;
    }

    
}
