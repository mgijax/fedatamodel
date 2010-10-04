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
@Table (name="recombinase_other_system")
public class AlleleSystemOtherSystem {

    private int alleleSystemKey;
    private String system;
    
    // ================= Getters and Setters ===================== //
    
    @Id
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
    public String getSystem() {
        return system;
    }
        
    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    @Override
    public String toString() {
        return "AlleleSystemOtherSystem [System=" + system
                + ", alleleSystemKey=" + alleleSystemKey + "]";
    }


}
