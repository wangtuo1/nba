package wangtuo.core.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import wangtuo.tools.enumeration.Gender;

/**
 * 教练
 * @author wangtuo0k
 *
 */
@Entity
@Table(name = "coach", uniqueConstraints = { @UniqueConstraint(columnNames = { "team_id" }) })
public class Coach {
	@Id
	@GeneratedValue(generator = "coach-uuid")
	@GenericGenerator(strategy = "uuid", name = "coach-uuid")
	@Column(length = 36)
	private String id;

	/**
	 * 姓名 Steve Kerr
	 */
	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(min = 1, max = 255)
	private String name;

	/**
	 * 性别 MALE
	 */
	@Column(nullable = false)
	@NotNull
	private Gender gender;

	/**
	 * 出生日期 1967-09-27
	 */
	@Column(nullable = false)
	@NotNull
	private LocalDate birthday;

	/**
	 * 所属队伍 Golden State Warriors
	 */
	@ManyToOne
	@NotNull
	private Team team;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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
		Coach other = (Coach) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coach [id=" + id + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", team=" + team
				+ "]";
	}
	
}
