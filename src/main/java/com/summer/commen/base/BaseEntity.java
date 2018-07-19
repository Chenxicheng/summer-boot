package com.summer.commen.base;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
@Data
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this.id = id;
    }
}
