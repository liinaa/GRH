/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javafx.scene.chart.PieChart;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Lina
 */
@Entity
public class Chart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private PieChartModel model;

    public Chart(int a, int b, int c, int d) {
        model = new PieChartModel();
        model.set("Salaire entre 0 et 3000",a);
        model.set("Salaire entre 3000 et 5000",b);
        model.set("Salaire entre 5000 et 10000",c);
        model.set("Salaire superieur à 10000",d);
        
        model.setTitle("Salaire des employés");
        model.setLegendPosition("e");
        model.setFill(true);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        
    }

    public Chart() {
    }

    public PieChartModel getModel() {
        return model;
    }

    public void setModel(PieChartModel model) {
        this.model = model;
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
        if (!(object instanceof Chart)) {
            return false;
        }
        Chart other = (Chart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Chart[ id=" + id + " ]";
    }
    
}
