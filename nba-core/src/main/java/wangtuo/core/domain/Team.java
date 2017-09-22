package wangtuo.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 球队
 * @author wangtuo0k
 *
 */
@Entity
@Table(name = "team", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Team {
	@Id
	@GeneratedValue(generator = "team-uuid")
	@GenericGenerator(strategy = "uuid", name = "team-uuid")
	@Column(length = 36)
	private String id;

	/**
	 * 名称 Golden State Warriors
	 */
	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(min = 1, max = 255)
	private String name;

	/**
	 * 积分 
	 * 常规赛胜场数/系列赛胜场数
	 */
	@Column(nullable = false)
	@NotNull
	private Integer score;

	/**
	 * 名次 1
	 */
	@Column(nullable = false)
	@NotNull
	private Integer rank;

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
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
		Team other = (Team) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", score=" + score + ", rank=" + rank + "]";
	}
}
