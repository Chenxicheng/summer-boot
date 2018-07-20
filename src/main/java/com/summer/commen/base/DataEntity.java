package com.summer.commen.base;

import com.summer.commen.utils.IdGen;
import com.summer.commen.utils.StringUtils;
import lombok.Data;

import java.util.Date;

/**
 * 数据基础类
 * @param <T>
 */
@Data
public class DataEntity<T> extends BaseEntity<T> {

    /**
     * 删除标记（0：正常；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    /**
     * 删除标记（1：删除；）
     */
    public static final String DEL_FLAG_DELETE = "1";

    protected String createBy; // 创建者id

    protected Date createDate; // 创建日期

    protected String updateBy;	// 更新者id
    protected Date updateDate;	// 更新日期

    protected String remarks; // 备注

    protected String delFlag; // 删除标识

    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public DataEntity(String id) {
        super(id);
        this.delFlag = DEL_FLAG_NORMAL;
    }

    /**
     *
     */
    public void preInsert() {
        if (getId() == null || StringUtils.isBlank(getId())) {
            setId(IdGen.uuid());
        }
        Date now = new Date();
        this.createBy = "1";
        this.createDate = now;
        this.updateBy = "1";
        this.updateDate = now;
    }

    public void preUpdate() {
        this.updateBy = "1";
        this.updateDate = new Date();
    }
}
