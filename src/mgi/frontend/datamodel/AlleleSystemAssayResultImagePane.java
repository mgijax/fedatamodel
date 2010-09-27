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

@Entity
@Table (name="recombinase_assay_result_imagepane")
public class AlleleSystemAssayResultImagePane {

    private int resultKey;
    private String paneLabel;
    private List<Image> images;
    
    @Id
    @Column(name="result_key")
    public int getResultKey() {
        return resultKey;
    }
    public void setResultKey(int resultKey) {
        this.resultKey = resultKey;
    }
    
    @Column(name="pane_label")
    public String getPaneLabel() {
        return paneLabel;
    }
    public void setPaneLabel(String paneLabel) {
        this.paneLabel = paneLabel;
    }
    
    @OneToMany (targetEntity=Image.class)
    @JoinColumn(name="image_key")
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    
    @Override
    public String toString() {
        return "AlleleSystemAssayResultImagePane ["
                + "paneLabel=" + paneLabel + ", resultKey=" + resultKey + "]";
    }
    

}
