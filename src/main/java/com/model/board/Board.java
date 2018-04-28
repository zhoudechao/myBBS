package com.model.board;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_board")
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_postNum")
    private Integer boardPostnum;

    @Column(name = "board_description")
    private String boardDescription;

    @Column(name = "board_createTime")
    private Date boardCreatetime;

    @Column(name = "board_zt")
    private String boardZt;

    /**
     * 今日发帖数
     */
    @Column(name = "board_todayNum")
    private Integer boardTodaynum;

    @Column(name = "board_img")
    private byte[] boardImg;
    
    @Column(name="board_bh")
    private Integer boardBh;

    /**
     * @return board_id
     */
    public Integer getBoardId() {
        return boardId;
    }

    /**
     * @param boardId
     */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * @return board_name
     */
    public String getBoardName() {
        return boardName;
    }

    /**
     * @param boardName
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * @return board_postNum
     */
    public Integer getBoardPostnum() {
        return boardPostnum;
    }

    /**
     * @param boardPostnum
     */
    public void setBoardPostnum(Integer boardPostnum) {
        this.boardPostnum = boardPostnum;
    }

    /**
     * @return board_description
     */
    public String getBoardDescription() {
        return boardDescription;
    }

    /**
     * @param boardDescription
     */
    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }

    /**
     * @return board_createTime
     */
    public Date getBoardCreatetime() {
        return boardCreatetime;
    }

    /**
     * @param boardCreatetime
     */
    public void setBoardCreatetime(Date boardCreatetime) {
        this.boardCreatetime = boardCreatetime;
    }

    /**
     * @return board_zt
     */
    public String getBoardZt() {
        return boardZt;
    }

    /**
     * @param boardZt
     */
    public void setBoardZt(String boardZt) {
        this.boardZt = boardZt;
    }

    /**
     * 获取今日发帖数
     *
     * @return board_todayNum - 今日发帖数
     */
    public Integer getBoardTodaynum() {
        return boardTodaynum;
    }

    /**
     * 设置今日发帖数
     *
     * @param boardTodaynum 今日发帖数
     */
    public void setBoardTodaynum(Integer boardTodaynum) {
        this.boardTodaynum = boardTodaynum;
    }

    /**
     * @return board_img
     */
    public byte[] getBoardImg() {
        return boardImg;
    }

    /**
     * @param boardImg
     */
    public void setBoardImg(byte[] boardImg) {
        this.boardImg = boardImg;
    }

	public Integer getBoardBh() {
		return boardBh;
	}

	public void setBoardBh(Integer boardBh) {
		this.boardBh = boardBh;
	}
    
}