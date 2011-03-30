package mgi.frontend.datamodel;

import javax.persistence.*;
import java.util.List;

/**
 * ImageID
 * @author jsb
 * This extends the general AccessionID Class.  As such most of its methods
 * come from that class.
 */
@Entity
@Table (name="image_id")
public class ImageID extends AccessionID {
	private int imageKey;
	private int sequenceNum;
	private int isForOtherDbSection;

    // ================= Getters and Setters ===================== //
	
	@Column(name="image_key")
	public int getImageKey() {
		return imageKey;
	}

	@Column(name="is_for_other_db_section")
	public int getIsForOtherDbSection() {
		return isForOtherDbSection;
	}

	@Column(name="sequence_num")
	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setImageKey(int imageKey) {
		this.imageKey = imageKey;
	}

	public void setIsForOtherDbSection(int isForOtherDbSection) {
		this.isForOtherDbSection = isForOtherDbSection;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	@Override
	public String toString() {
		return "ImageID [imageKey=" + imageKey + ", sequenceNum=" + sequenceNum
				+ ", isForOtherDbSection=" + isForOtherDbSection + "]";
	}
}
