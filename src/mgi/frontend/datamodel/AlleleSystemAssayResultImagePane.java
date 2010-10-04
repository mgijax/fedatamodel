package mgi.frontend.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * recombinase_assay_result_imagepane
 * @author mhall
 *
 * Returns the pane that will contain multiple images for a 
 * given assay result.
 *
 */
@Entity
@Table (name="recombinase_assay_result_imagepane")
public class AlleleSystemAssayResultImagePane {

    private List<Image> images;
    private String paneLabel;
    private int resultKey;
    
    // ================= Getters and Setters ===================== //
    
    @OneToMany (targetEntity=Image.class)
    @JoinColumn(name="image_key")
    public List<Image> getImages() {
        return images;
    }
    @Column(name="pane_label")
    public String getPaneLabel() {
        return paneLabel;
    }
    
    @Id
    @Column(name="result_key")
    public int getResultKey() {
        return resultKey;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    
    public void setPaneLabel(String paneLabel) {
        this.paneLabel = paneLabel;
    }
    public void setResultKey(int resultKey) {
        this.resultKey = resultKey;
    }
    
    @Override
    public String toString() {
        return "AlleleSystemAssayResultImagePane ["
                + "paneLabel=" + paneLabel + ", resultKey=" + resultKey + "]";
    }
    

}
