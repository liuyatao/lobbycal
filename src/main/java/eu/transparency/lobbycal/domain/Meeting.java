package eu.transparency.lobbycal.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.transparency.lobbycal.domain.util.CustomDateTimeDeserializer;
import eu.transparency.lobbycal.domain.util.CustomDateTimeSerializer;

/**
 * A Meeting.
 */
@Entity
@Table(name = "MEETING")
@Document(indexName = "meeting")
public class Meeting implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "title", length=1000)
	private String title;

	@Column(name = "submitter")
	private String submitter;

	@Column(name = "alias_used")
	private String aliasUsed;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	@Column(name = "start_date")
	private DateTime startDate;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	@Column(name = "end_date")
	private DateTime endDate;

	@Column(name = "u_id")
	private String uid;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "MEETING_TAG", joinColumns = @JoinColumn(name = "meetings_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "ID"))
	private Set<Tag> tags = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "MEETING_PARTNER", joinColumns = @JoinColumn(name = "meetings_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "partners_id", referencedColumnName = "ID"))
	private Set<Partner> partners = new HashSet<>();

	@ManyToOne
	private User user;

	/**
	 * @since lobbcal v.2
	 */
	@Column(name = "mTag")
	private String mTag;
	
	@Column(name = "mPartner")
	private String mPartner;

	public String getmTag() {
		return mTag;
	}

	public void setmTag(String mTag) {
		this.mTag = mTag;
	}

	public String getmPartner() {
		return mPartner;
	}

	public void setmPartner(String mPartner) {
		this.mPartner = mPartner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getAliasUsed() {
		return aliasUsed;
	}

	public void setAliasUsed(String aliasUsed) {
		this.aliasUsed = aliasUsed;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Partner> getPartners() {
		return partners;
	}

	public void setPartners(Set<Partner> partners) {
		this.partners = partners;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Meeting meeting = (Meeting) o;

		if (!Objects.equals(id, meeting.id))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", title=" + title + ", submitter="
				+ submitter + ", aliasUsed=" + aliasUsed + ", startDate="
				+ startDate + ", endDate=" + endDate + ", uid=" + uid
				+ ", tags=" + tags + ", partners=" + partners + ", user="
				+ user + ", mTag=" + mTag + ", mPartner=" + mPartner + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}