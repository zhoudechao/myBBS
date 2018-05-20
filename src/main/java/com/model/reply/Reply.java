package com.model.reply;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_reply")
public class Reply {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyId;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_userId")
    private Integer replyUserid;

    @Column(name = "reply_userName")
    private String replyUsername;

    @Column(name = "reply_userIp")
    private String replyUserip;

    @Column(name = "reply_postId")
    private Integer replyPostid;

    @Column(name = "reply_Time")
    private Date replyTime;

    private String zt;

    @Column(name = "reply_isEnd")
    private String replyIsend;

    /**
     * @return reply_id
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * @param replyId
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * @return reply_content
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * @param replyContent
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * @return reply_userId
     */
    public Integer getReplyUserid() {
        return replyUserid;
    }

    /**
     * @param replyUserid
     */
    public void setReplyUserid(Integer replyUserid) {
        this.replyUserid = replyUserid;
    }

    /**
     * @return reply_userName
     */
    public String getReplyUsername() {
        return replyUsername;
    }

    /**
     * @param replyUsername
     */
    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }

    /**
     * @return reply_userIp
     */
    public String getReplyUserip() {
        return replyUserip;
    }

    /**
     * @param replyUserip
     */
    public void setReplyUserip(String replyUserip) {
        this.replyUserip = replyUserip;
    }

    /**
     * @return reply_postId
     */
    public Integer getReplyPostid() {
        return replyPostid;
    }

    /**
     * @param replyPostid
     */
    public void setReplyPostid(Integer replyPostid) {
        this.replyPostid = replyPostid;
    }

    /**
     * @return reply_Time
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * @param replyTime
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
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
     * @return reply_isEnd
     */
    public String getReplyIsend() {
        return replyIsend;
    }

    /**
     * @param replyIsend
     */
    public void setReplyIsend(String replyIsend) {
        this.replyIsend = replyIsend;
    }
}