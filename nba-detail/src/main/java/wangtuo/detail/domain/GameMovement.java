package wangtuo.detail.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import wangtuo.tools.enumeration.Side;

/**
 * 比赛-运动轨迹
 * @author wangtuo0k
 *
 */
@Entity
@Table(name="game_movement")
public class GameMovement {
	@Id
	@GeneratedValue(generator = "game-movement-uuid")
	@GenericGenerator(strategy = "uuid", name = "game-movement-uuid")
	@Column(length = 36)
	private String id;
	
	@Column
	private String seasonId;
	
	@Column
	private String gameId;
	
	// 球员
	@Column
	private String playerId;
	
	// 左（右）脚
	@Column
	private Side side;
	
	// 落地时间
	@Column
	private LocalTime landTime;
	
	// 抬起时间
	@Column
	private LocalTime liftTime;
	
	// x坐标
	@Column
	private Double xCoordinate;
	
	// y坐标
	@Column
	private Double yCoordinate;
	
	// 是否控球
	@Column
	private Boolean ballControl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public LocalTime getLandTime() {
		return landTime;
	}

	public void setLandTime(LocalTime landTime) {
		this.landTime = landTime;
	}

	public LocalTime getLiftTime() {
		return liftTime;
	}

	public void setLiftTime(LocalTime liftTime) {
		this.liftTime = liftTime;
	}

	public Double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public Boolean getBallControl() {
		return ballControl;
	}

	public void setBallControl(Boolean ballControl) {
		this.ballControl = ballControl;
	}

	@Override
	public String toString() {
		return "GameMovement [id=" + id + ", seasonId=" + seasonId + ", gameId=" + gameId + ", playerId=" + playerId
				+ ", side=" + side + ", landTime=" + landTime + ", liftTime=" + liftTime + ", xCoordinate="
				+ xCoordinate + ", yCoordinate=" + yCoordinate + ", ballControl=" + ballControl + "]";
	}
}
