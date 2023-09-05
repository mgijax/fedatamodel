package org.jax.mgi.fe.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * recombinase_assay_result_imagepane
 * @author mhall, jsb
 *
 * An image pane represents the portion of a figure that is associated
 * with a recombinase assay result.
 *
 */
@Entity
@Table (name="recombinase_assay_result_imagepane")
public class AlleleSystemAssayResultImagePane {

	private int uniqueKey;
    private Image image;
    private String paneLabel;
    private int resultKey;
    private int sequenceNum;
    
    // ================= Getters and Setters ===================== //
    
    @ManyToOne (targetEntity=Image.class)
    @JoinColumn(name="image_key")
    public Image getImage() {
        return image;
    }
 
    @Column(name="pane_label")
    public String getPaneLabel() {
        return paneLabel;
    }
    
	@Column(name="result_key")
    public int getResultKey() {
        return resultKey;
    }
	
	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}
	
	@Id
    @Column(name="unique_key")
    public int getUniqueKey() {
		return uniqueKey;
	}
	
	public void setImage(Image image) {
        this.image = image;
    }
    
    public void setPaneLabel(String paneLabel) {
        this.paneLabel = paneLabel;
    }
    public void setResultKey(int resultKey) {
        this.resultKey = resultKey;
    }
    
    public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
    public void setUniqueKey(int uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String toString() {
		return "AlleleSystemAssayResultImagePane [uniqueKey=" + uniqueKey
				+ ", image=" + image + ", paneLabel=" + paneLabel
				+ ", resultKey=" + resultKey + ", sequenceNum=" + sequenceNum
				+ "]";
	}
}
