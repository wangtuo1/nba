package wangtuo.core.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import wangtuo.tools.enumeration.GameType;

/**
 * 比赛
 * @author wangtuo0k
 *
 */
@Entity
@Table(name = "game")
public class Game {
	@Id
	@GeneratedValue(generator = "game-uuid")
	@GenericGenerator(strategy = "uuid", name = "game-uuid")
	@Column(length = 36)
	private String id;

	/**
	 * 比赛日期 2017-06-01
	 */
	@Column(nullable = false)
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	/**
	 * 主队 Golden State Warriors
	 */
	@OneToOne
	@NotNull
	private Team homeTeam;

	/**
	 * 客队 Cleveland Cavaliers
	 */
	@OneToOne
	@NotNull
	private Team awayTeam;

	/**
	 * 球馆 Oracle Arena
	 */
	@OneToOne
	@NotNull
	private Court court;

	/**
	 * 赛季 2016-2017
	 */
	@ManyToOne
	@NotNull
	private Season season;

	/**
	 * 类型 finals
	 */
	@Column(nullable = false, length = 1)
	@NotNull
	private GameType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
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
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", court="
				+ court + ", season=" + season + ", type=" + type + "]";
	}
}
