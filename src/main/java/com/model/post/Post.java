package com.model.post;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_post")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    /**
     * 所属板块编号
     */
    @Column(name = "post_boardId")
    private Integer postBoardid;

    @Column(name = "post_boardName")
    private String postBoardname;

    /**
     * 所属用户的编号
     */
    @Column(name = "post_userId")
    private Integer postUserid;

    /**
     * 所属用户的名称
     */
    @Column(name = "post_userName")
    private String postUsername;

    @Column(name = "post_topic")
    private String postTopic;

    @Column(name = "post_createTime")
    private Date postCreatetime;

    @Column(name = "post_length")
    private Integer postLength;

    @Column(name = "post_order")
    private Integer postOrder;

    /**
     * 是否是精华
     */
    @Column(name = "post_isBest")
    private String postIsbest;

    /**
     * 是否有附件
     */
    @Column(name = "post_isupload")
    private String postIsupload;

    private String zt;

    @Column(name = "post_description")
    private String postDescription;

    /**
     * 帖子类型ID
     */
    @Column(name = "post_typeId")
    private Integer postTypeid;

    /**
     * 帖子类型名称
     */
    @Column(name = "post_typeName")
    private String postTypename;

    /**
     * 是否结贴
     */
    @Column(name = "post_isEnd")
    private String postIsend;

    /**
     * 被阅读的次数
     */
    @Column(name = "post_readNum")
    private Integer postReadnum;

    /**
     * 被回答的次数
     */
    @Column(name = "post_answerNum")
    private Integer postAnswernum;

    /**
     * 是否为顶置
     */
    @Column(name = "post_isTop")
    private String postIstop;

    @Column(name = "post_content")
    private String postContent;

    /**
     * @return post_id
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * @param postId
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * 获取所属板块编号
     *
     * @return post_boardId - 所属板块编号
     */
    public Integer getPostBoardid() {
        return postBoardid;
    }

    /**
     * 设置所属板块编号
     *
     * @param postBoardid 所属板块编号
     */
    public void setPostBoardid(Integer postBoardid) {
        this.postBoardid = postBoardid;
    }

    /**
     * @return post_boardName
     */
    public String getPostBoardname() {
        return postBoardname;
    }

    /**
     * @param postBoardname
     */
    public void setPostBoardname(String postBoardname) {
        this.postBoardname = postBoardname;
    }

    /**
     * 获取所属用户的编号
     *
     * @return post_userId - 所属用户的编号
     */
    public Integer getPostUserid() {
        return postUserid;
    }

    /**
     * 设置所属用户的编号
     *
     * @param postUserid 所属用户的编号
     */
    public void setPostUserid(Integer postUserid) {
        this.postUserid = postUserid;
    }

    /**
     * 获取所属用户的名称
     *
     * @return post_userName - 所属用户的名称
     */
    public String getPostUsername() {
        return postUsername;
    }

    /**
     * 设置所属用户的名称
     *
     * @param postUsername 所属用户的名称
     */
    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }

    /**
     * @return post_topic
     */
    public String getPostTopic() {
        return postTopic;
    }

    /**
     * @param postTopic
     */
    public void setPostTopic(String postTopic) {
        this.postTopic = postTopic;
    }

    /**
     * @return post_createTime
     */
    public Date getPostCreatetime() {
        return postCreatetime;
    }

    /**
     * @param postCreatetime
     */
    public void setPostCreatetime(Date postCreatetime) {
        this.postCreatetime = postCreatetime;
    }

    /**
     * @return post_length
     */
    public Integer getPostLength() {
        return postLength;
    }

    /**
     * @param postLength
     */
    public void setPostLength(Integer postLength) {
        this.postLength = postLength;
    }

    /**
     * @return post_order
     */
    public Integer getPostOrder() {
        return postOrder;
    }

    /**
     * @param postOrder
     */
    public void setPostOrder(Integer postOrder) {
        this.postOrder = postOrder;
    }

    /**
     * 获取是否是精华
     *
     * @return post_isBest - 是否是精华
     */
    public String getPostIsbest() {
        return postIsbest;
    }

    /**
     * 设置是否是精华
     *
     * @param postIsbest 是否是精华
     */
    public void setPostIsbest(String postIsbest) {
        this.postIsbest = postIsbest;
    }

    /**
     * 获取是否有附件
     *
     * @return post_isupload - 是否有附件
     */
    public String getPostIsupload() {
        return postIsupload;
    }

    /**
     * 设置是否有附件
     *
     * @param postIsupload 是否有附件
     */
    public void setPostIsupload(String postIsupload) {
        this.postIsupload = postIsupload;
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
     * @return post_description
     */
    public String getPostDescription() {
        return postDescription;
    }

    /**
     * @param postDescription
     */
    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    /**
     * 获取帖子类型ID
     *
     * @return post_typeId - 帖子类型ID
     */
    public Integer getPostTypeid() {
        return postTypeid;
    }

    /**
     * 设置帖子类型ID
     *
     * @param postTypeid 帖子类型ID
     */
    public void setPostTypeid(Integer postTypeid) {
        this.postTypeid = postTypeid;
    }

    /**
     * 获取帖子类型名称
     *
     * @return post_typeName - 帖子类型名称
     */
    public String getPostTypename() {
        return postTypename;
    }

    /**
     * 设置帖子类型名称
     *
     * @param postTypename 帖子类型名称
     */
    public void setPostTypename(String postTypename) {
        this.postTypename = postTypename;
    }

    /**
     * 获取是否结贴
     *
     * @return post_isEnd - 是否结贴
     */
    public String getPostIsend() {
        return postIsend;
    }

    /**
     * 设置是否结贴
     *
     * @param postIsend 是否结贴
     */
    public void setPostIsend(String postIsend) {
        this.postIsend = postIsend;
    }

    /**
     * 获取被阅读的次数
     *
     * @return post_readNum - 被阅读的次数
     */
    public Integer getPostReadnum() {
        return postReadnum;
    }

    /**
     * 设置被阅读的次数
     *
     * @param postReadnum 被阅读的次数
     */
    public void setPostReadnum(Integer postReadnum) {
        this.postReadnum = postReadnum;
    }

    /**
     * 获取被回答的次数
     *
     * @return post_answerNum - 被回答的次数
     */
    public Integer getPostAnswernum() {
        return postAnswernum;
    }

    /**
     * 设置被回答的次数
     *
     * @param postAnswernum 被回答的次数
     */
    public void setPostAnswernum(Integer postAnswernum) {
        this.postAnswernum = postAnswernum;
    }

    /**
     * 获取是否为顶置
     *
     * @return post_isTop - 是否为顶置
     */
    public String getPostIstop() {
        return postIstop;
    }

    /**
     * 设置是否为顶置
     *
     * @param postIstop 是否为顶置
     */
    public void setPostIstop(String postIstop) {
        this.postIstop = postIstop;
    }

    /**
     * @return post_content
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * @param postContent
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}