package com.my.mypicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * 搜索图片请求
 * 以图搜图
 */

@Data
public class SearchPictureByPictureRequest implements Serializable {

    /**
     * 图片 id
     */
    private Long pictureId;

    private static final long serialVersionUID = 1L;
}
