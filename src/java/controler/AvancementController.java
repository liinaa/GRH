package controler;

import bean.Avancement;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import service.AvancementFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.EchelonFacade;

@Named("avancementController")
@SessionScoped
public class AvancementController implements Serializable {

    @EJB
    private service.AvancementFacade ejbFacade;
    private List<Avancement> items = null;
    private Avancement selected;
    @EJB
    private EchelonFacade echelonFacade;

    public AvancementController() {
    }
    public void findNext(){
        selected.setEchelonDestination(echelonFacade.findByNext(selected.getEmploye().getEchelon()));
    }
    public Avancement getSelected() {
         if (selected == null){
            selected = new Avancement();
        }
        return selected;
    }

    public void setSelected(Avancement selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AvancementFacade getFacade() {
        return ejbFacade;
    }

    public Avancement prepareCreate() {
        selected = new Avancement();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setEchelonSource(selected.getEmploye().getEchelon());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AvancementCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AvancementUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AvancementDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Avancement> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    int res = getFacade().save(selected);
                    if(res<0)
                    JsfUtil.addErrorMessage("Not Saved");
                    else JsfUtil.addSuccessMessage("Saved");
                } else if (persistAction == PersistAction.UPDATE) {
                    getFacade().edit(selected);
                    JsfUtil.addSuccessMessage(successMessage);
                }
                else {
                    getFacade().remove(selected);
                    JsfUtil.addSuccessMessage(successMessage);
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Avancement getAvancement(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Avancement> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Avancement> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Avancement.class)
    public static class AvancementControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AvancementController controller = (AvancementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "avancementController");
            return controller.getAvancement(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Avancement) {
                Avancement o = (Avancement) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Avancement.class.getName()});
                return null;
            }
        }

    }

}
