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
@Table (name="recombinase_allele_system")
public class AlleleSystem {

    private int alleleKey;
    private int alleleSystemKey;
    private String system;
    private List<AlleleSystemAssayResult> alleleSystemAssayResults;
    private List<Image> images;
    
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }
    public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }
    
    @Id
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }
    
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    
    @OneToMany (targetEntity=AlleleSystemAssayResult.class)
    @JoinColumn(name="allele_system_key")
    public List<AlleleSystemAssayResult> getAlleleSystemAssayResults() {
        return alleleSystemAssayResults;
    }
    
    public void setAlleleSystemAssayResults(List<AlleleSystemAssayResult> a) {
        this.alleleSystemAssayResults = a;
    }    
    
    @OneToMany
    @JoinTable (name="recombinase_allele_system_to_image",
            joinColumns=@JoinColumn(name="allele_system_key"),
            inverseJoinColumns=@JoinColumn(name="image_key")
            )
//    @OrderBy("sequence_num")
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    
    @Override
    public String toString() {
        return "AlleleSystem [alleleKey=" + alleleKey
                + ", alleleSystemKey=" + alleleSystemKey + ", system=" + system
                + "]";
    }


}
