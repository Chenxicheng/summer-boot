package com.summer.commen.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.summer.commen.utils.*;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.utils.UserUtils;
import lombok.Data;
import org.springframework.context.annotation.DependsOn;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    protected Date createDate; // 创建日期

    protected String updateBy;	// 更新者id

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
     * 插入数据库前，处理方式
     */
    public void preInsert(User user) {
        Date now = new Date();
        if (getId() == null || StringUtils.isBlank(getId())) {
            setId(IdGen.uuid());
        }
        if (user != null && StringUtils.isNotBlank(user.getId())) {
            this.createBy = user.getId();
            this.updateBy = user.getId();
        } else {
            this.createBy = User.ROOT_ID;
            this.updateBy = User.ROOT_ID;
        }
        this.createDate = now;
        this.updateDate = now;
    }

    /**
     * 更新数据前处理方式
     */
    public void preUpdate(User user) {
        if (user != null && StringUtils.isNotBlank(user.getId())) {
            this.updateBy = user.getId();
        } else {
            this.updateBy = User.ROOT_ID;
        }
        this.updateDate = new Date();
    }
}
