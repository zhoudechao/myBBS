package com.model.mark;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_mark")
public class Mark {
    @Id
    @Column(name = "mark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer markId;

    @Column(name = "mark_userId")
    private Integer markUserid;

    @Column(name = "mark_userName")
    private String markUsername;

    @Column(name = "mark_createTime")
    private Date markCreatetime;

    private String zt;

    @Column(name = "mark_postId")
    private Integer markPostid;

    @Column(name = "mark_postTopic")
    private String markPosttopic;

    /**
     * @return mark_id
     */
    public Integer getMarkId() {
        return markId;
    }

    /**
     * @param markId
     */
    public void setMarkId(Integer markId) {
        this.markId = markId;
    }

    /**
     * @return mark_userId
     */
    public Integer getMarkUserid() {
        return markUserid;
    }

    /**
     * @param markUserid
     */
    public void setMarkUserid(Integer markUserid) {
        this.markUserid = markUserid;
    }

    /**
     * @return mark_userName
     */
    public String getMarkUsername() {
        return markUsername;
    }

    /**
     * @param markUsername
     */
    public void setMarkUsername(String markUsername) {
        this.markUsername = markUsername;
    }

    /**
     * @return mark_createTime
     */
    public Date getMarkCreatetime() {
        return markCreatetime;
    }

    /**
     * @param markCreatetime
     */
    public void setMarkCreatetime(Date markCreatetime) {
        this.markCreatetime = markCreatetime;
    }

    /**
     * @return zt
     */
    public String getZt() {
        return zt;
    }

    /**
     * @param zt
     */
    public void setZt(String zt) {
        this.zt = zt;
    }

    /**
     * @return mark_postId
     */
    public Integer getMarkPostid() {
        return markPostid;
    }

    /**
     * @param markPostid
     */
    public void setMarkPostid(Integer markPostid) {
        this.markPostid = markPostid;
    }

    /**
     * @return mark_postTopic
     */
    public String getMarkPosttopic() {
        return markPosttopic;
    }

    /**
     * @param markPosttopic
     */
    public void setMarkPosttopic(String markPosttopic) {
        this.markPosttopic = markPosttopic;
    }
}