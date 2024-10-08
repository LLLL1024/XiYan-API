package com.xiyan.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 发布和下线请求
 *
 * @author xiyan
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}