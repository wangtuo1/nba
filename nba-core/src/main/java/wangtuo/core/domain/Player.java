package wangtuo.core.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import wangtuo.tools.enumeration.Gender;

/**
 * 球员
 * @author wangtuo0k
 *
 */
@Entity
@Table(name = "player", uniqueConstraints = { @UniqueConstraint(columnNames = { "team_id", "number" }) })
public class Player {
	@Id
	@GeneratedValue(generator = "player-uuid")
	@GenericGenerator(strategy = "uuid", name = "player-uuid")
	@Column(length = 36)
	private String id;

	/**
	 * 姓名 Stephen Curry
	 */
	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(min = 1, max = 255)
	private String name;

	/**
	 * 性别 MALE
	 */
	@Column(nullable = false, length = 1)
	@NotNull
	private Gender gender;

	/**
	 * 生日 1988-03-14
	 */
	@Column(nullable = false)
	@NotNull
	private LocalDate birthday;

	/**
	 * 身高 1.91
	 */
	@Column(columnDefinition = "decimal(6,2)")
	@NotNull
	@Digits(integer = 6, fraction = 2)
	private Double height;

	/**
	 * 体重 83.9
	 */
	@Column(columnDefinition = "decimal(6,2)")
	@NotNull
	@Digits(integer = 6, fraction = 2)
	private Double weight;

	/**
	 * 所属队伍 Golden State Warriors
	 */
	@ManyToOne
	@NotNull
	private Team team;

	/**
	 * 号码 30
	 */
	@Column
	@NotNull
	private Integer number;

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

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", height="
				+ height + ", weight=" + weight + ", team=" + team + ", number=" + number + "]";
	}
}
