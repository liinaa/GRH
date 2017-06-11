/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lina
 */
@ManagedBean
@SessionScoped
public class OrgaEmployeController implements Serializable {

    public String departem = "1";

    public String depart() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        departem = params.get("departement");
        return "/listEmploye/nosEmploye?faces-redirect=true";
    }

    public String getDepartem() {
        return departem;
    }

    public void setDepartem(String departem) {
        this.departem = departem;
    }

}
