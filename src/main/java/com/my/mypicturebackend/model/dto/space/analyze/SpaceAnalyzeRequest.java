package com.my.mypicturebackend.model.dto.space.analyze;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用空间分析请求
 */
@Data
public class SpaceAnalyzeRequest implements Serializable {

    /**
     * 空间 ID
     * 仅在 queryAll 和 queryPublic 均为 false 时生效，表示对特定空间进行分析，仅空间创建者和管理员可使用。
     */
    private Long spaceId;

    /**
     * 是否查询公共图库
     * 为 true 时表示查询公共图库，仅管理员可使用。
     */
    private boolean queryPublic;

    /**
     * 全空间分析
     * 为 true 时表示查询全空间，仅管理员可使用。
     */
    private boolean queryAll;

    private static final long serialVersionUID = 1L;
}
