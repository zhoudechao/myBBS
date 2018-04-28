package com.model.common;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_common")
public class Common {
    @Id
    @Column(name = "common_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commonId;

    /**
     * 公共信息的版面编号
     */
    @Column(name = "common_bh")
    private Integer commonBh;

    @Column(name = "common_title")
    private String commonTitle;

    @Column(name = "common_content")
    private String commonContent;

    @Column(name = "common_creatTime")
    private Date commonCreattime;

    private String zt;

    @Column(name = "common_description")
    private String commonDescription;

    /**
     * @return common_id
     */
    public Integer getCommonId() {
        return commonId;
    }

    /**
     * @param commonId
     */
    public void setCommonId(Integer commonId) {
        this.commonId = commonId;
    }

    /**
     * 获取公共信息的版面编号
     *
     * @return common_bh - 公共信息的版面编号
     */
    public Integer getCommonBh() {
        return commonBh;
    }

    /**
     * 设置公共信息的版面编号
     *
     * @param commonBh 公共信息的版面编号
     */
    public void setCommonBh(Integer commonBh) {
        this.commonBh = commonBh;
    }

    /**
     * @return common_title
     */
    public String getCommonTitle() {
        return commonTitle;
    }

    /**
     * @param commonTitle
     */
    public void setCommonTitle(String commonTitle) {
        this.commonTitle = commonTitle;
    }

    /**
     * @return common_content
     */
    public String getCommonContent() {
        return commonContent;
    }

    /**
     * @param commonContent
     */
    public void setCommonContent(String commonContent) {
        this.commonContent = commonContent;
    }

    /**
     * @return common_creatTime
     */
    public Date getCommonCreattime() {
        return commonCreattime;
    }

    /**
     * @param commonCreattime
     */
    public void setCommonCreattime(Date commonCreattime) {
        this.commonCreattime = commonCreattime;
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
     * @return common_description
     */
    public String getCommonDescription() {
        return commonDescription;
    }

    /**
     * @param commonDescription
     */
    public void setCommonDescription(String commonDescription) {
        this.commonDescription = commonDescription;
    }
}