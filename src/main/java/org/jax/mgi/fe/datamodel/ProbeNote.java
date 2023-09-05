package org.jax.mgi.fe.datamodel;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Object representing probe notes
 * This inherits almost all of its methods from the note object.
 */
@Entity
@Table (name="probe_note")
public class ProbeNote extends Note {
	protected Integer probeKey;

    // ================= Transient methods ===================== //
	
	// examines the note, splits up any contiguous strings of letters into 60-character chunks
	// (separated by HTML line breaks -- yes, it's ugly, but it works for now) so they can wrap well,
	// and returns the resulting string
	@Transient
	public String getNoteWithSplitSequence() {
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("([A-Za-z]{60})");
		Matcher matcher = pattern.matcher(getNote());
		boolean foundOne = false;
		String openTag = "<div style='font-family: Courier, monospace'>";
		String closeTag = "</div>";
		while (matcher.find()) {
			matcher.appendReplacement(sb, "<br/>" + openTag + matcher.group(1));
			foundOne = true;
			openTag = "";
		}
		if (foundOne) {
			sb.append("<br/>");
		}
		matcher.appendTail(sb);
		if (foundOne) {
			sb.append(closeTag);
		}
		return sb.toString();
	}

    // ================= Getters and Setters ===================== //
	
	@Column(name="probe_key")
	public Integer getProbeKey() {
		return probeKey;
	}

	public void setProbeKey(Integer probeKey) {
		this.probeKey = probeKey;
	}

	@Override
    public String toString() {
        return "ProbeNote [probeKey=" + probeKey + ", note=" + note
                + ", noteType=" + noteType + ", uniqueKey=" + uniqueKey + "]";
    }
}
