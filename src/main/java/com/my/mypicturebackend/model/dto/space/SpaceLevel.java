package com.my.mypicturebackend.model.dto.space;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 空间级别
 */
@Data
@AllArgsConstructor
public class SpaceLevel {

    /**
     * 0-普通版 1-专业版 2-旗舰版
     */
    private int value;

    /**
     * 描述，普通版 专业版 旗舰版
     */
    private String text;

    /**
     * 最大数量
     */
    private long maxCount;

    /**
     * 最大空间大小
     */
    private long maxSize;
}
