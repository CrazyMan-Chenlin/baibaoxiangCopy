package com.baibaoxiang.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenlin
 */
@Data
public class ArticleType implements Serializable {
    private Integer id;

    private Integer sequenceNum;

    private String type;

}