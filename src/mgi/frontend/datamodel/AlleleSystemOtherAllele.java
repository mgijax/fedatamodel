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
 * recombinase_other_allele
 * @author mhall
 *
 * Other alleles for a given allele system.
 *
 */
@Entity
@Table (name="recombinase_other_allele")
public class AlleleSystemOtherAllele {

    private String alleleID;
    private int alleleKey;
    private String alleleSymbol;
    private int alleleSystemKey;
    
    // ================= Getters and Setters ===================== //
    
    @Column(name="allele_id")
    public String getAlleleID() {
        return alleleID;
    }
    @Column(name="allele_key")
    public int getAlleleKey() {
        return alleleKey;
    }
    
    @Column(name="allele_symbol")
    public String getAlleleSymbol() {
        return alleleSymbol;
    }
    @Id
    @Column(name="allele_system_key")
    public int getAlleleSystemKey() {
        return alleleSystemKey;
    }
        
    public void setAlleleID(String alleleID) {
        this.alleleID = alleleID;
    }
    public void setAlleleKey(int alleleKey) {
        this.alleleKey = alleleKey;
    }
    public void setAlleleSymbol(String alleleSymbol) {
        this.alleleSymbol = alleleSymbol;
    }
    public void setAlleleSystemKey(int alleleSystemKey) {
        this.alleleSystemKey = alleleSystemKey;
    }
    @Override
    public String toString() {
        return "AlleleSystemOtherAllele [alleleID=" + alleleID + ", alleleKey="
                + alleleKey + ", alleleSymbol=" + alleleSymbol
                + ", alleleSystemKey=" + alleleSystemKey + "]";
    }


}
