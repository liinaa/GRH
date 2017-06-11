package controler;

import bean.Avancement;
import bean.Chart;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import service.ChartFacade;

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
import service.EmployeFacade;

@Named("chartController")
@SessionScoped
public class ChartController implements Serializable {

    @EJB
    private service.ChartFacade ejbFacade;
    private List<Chart> items = null;
    private Chart selected;
     @EJB
    private EmployeFacade employeFacade;


    public ChartController() {
    }

    public Chart getSelected() {
        if (selected == null){
            selected = new Chart(employeFacade.findByCategorieSalaire(0,3000),employeFacade.findByCategorieSalaire(3000,5000),employeFacade.findByCategorieSalaire(5000,10000),employeFacade.findByCategorieSalaire(10000,100000));
        }
        return selected;
    }

    public void setSelected(Chart selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ChartFacade getFacade() {
        return ejbFacade;
    }

    public Chart prepareCreate() {
        selected = new Chart(employeFacade.findByCategorieSalaire(0,3000),employeFacade.findByCategorieSalaire(3000,5000),employeFacade.findByCategorieSalaire(5000,10000),employeFacade.findByCategorieSalaire(10000,100000));
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ChartCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ChartUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ChartDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Chart> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Chart getChart(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Chart> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Chart> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Chart.class)
    public static class ChartControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChartController controller = (ChartController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "chartController");
            return controller.getChart(getKey(value));
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
            if (object instanceof Chart) {
                Chart o = (Chart) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Chart.class.getName()});
                return null;
            }
        }

    }

}
