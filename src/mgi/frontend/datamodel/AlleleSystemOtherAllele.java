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
@Table (name="recombinase_other_allele")
public class AlleleSystemOtherAllele {

    private int alleleKey;
    private int alleleSystemKey;
    private String alleleSymbol;
    private String alleleID;
    
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
        
    @Column(name="allele_symbol")
    public String getAlleleSymbol() {
        return alleleSymbol;
    }
    public void setAlleleSymbol(String alleleSymbol) {
        this.alleleSymbol = alleleSymbol;
    }
    @Column(name="allele_id")
    public String getAlleleID() {
        return alleleID;
    }
    public void setAlleleID(String alleleID) {
        this.alleleID = alleleID;
    }
    @Override
    public String toString() {
        return "AlleleSystemOtherAllele [alleleID=" + alleleID + ", alleleKey="
                + alleleKey + ", alleleSymbol=" + alleleSymbol
                + ", alleleSystemKey=" + alleleSystemKey + "]";
    }


}
