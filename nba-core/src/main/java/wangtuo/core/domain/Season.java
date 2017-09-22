package wangtuo.core.domain;

import java.time.LocalDate;

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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 赛季
 * @author wangtuo0k
 *
 */
@Entity
@Table(name = "season", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Season {
	@Id
	@GeneratedValue(generator = "season-uuid")
	@GenericGenerator(strategy = "uuid", name = "season-uuid")
	@Column(length = 36)
	private String id;

	/**
	 * 名称 2016-2017
	 */
	@Column(nullable = false, length = 255)
	@NotBlank
	@Size(min = 1, max = 255)
	private String name;

	/**
	 * 开始时间 2016-10-26 (常规赛揭幕战日期)
	 */
	@Column(nullable = false)
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;

	/**
	 * 结束时间 2017-06-19 (总决赛最晚结束日期)
	 */
	@Column(nullable = false)
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
		Season other = (Season) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Season [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
