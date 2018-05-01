package com.model.type;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bbs_type")
public class Type {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_description")
    private String typeDescription;

    @Column(name = "type_bh")
    private String typeBh;

    private String zt;

    @Column(name = "type_createTime")
    private Date typeCreatetime;

    /**
     * 帖子数量
     */
    @Column(name = "type_postNum")
    private Integer typePostnum;

    /**
     * @return type_id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return type_name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return type_description
     */
    public String getTypeDescription() {
        return typeDescription;
    }

    /**
     * @param typeDescription
     */
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    /**
     * @return type_bh
     */
    public String getTypeBh() {
        return typeBh;
    }

    /**
     * @param typeBh
     */
    public void setTypeBh(String typeBh) {
        this.typeBh = typeBh;
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
     * @return type_createTime
     */
    public Date getTypeCreatetime() {
        return typeCreatetime;
    }

    /**
     * @param typeCreatetime
     */
    public void setTypeCreatetime(Date typeCreatetime) {
        this.typeCreatetime = typeCreatetime;
    }

    /**
     * 获取帖子数量
     *
     * @return type_postNum - 帖子数量
     */
    public Integer getTypePostnum() {
        return typePostnum;
    }

    /**
     * 设置帖子数量
     *
     * @param typePostnum 帖子数量
     */
    public void setTypePostnum(Integer typePostnum) {
        this.typePostnum = typePostnum;
    }
}