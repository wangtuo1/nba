package wangtuo.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 球馆
 * @author wangtuo0k
 *
 */
@Entity
@Table(name = "court")
public class Court{
	@Id
	@GeneratedValue(generator = "court-uuid")
	@GenericGenerator(strategy = "uuid", name = "court-uuid")
	@Column(length = 36)
	private String id;

	/**
	 * Oracle Arena
	 */
	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(min = 1, max = 255)
	private String name;

	/**
	 * Oakland, California, United States
	 */
	@Column(nullable = false, length = 1000)
	@NotBlank
	@Size(min = 1, max = 1000)
	private String location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Court other = (Court) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Court [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
}
