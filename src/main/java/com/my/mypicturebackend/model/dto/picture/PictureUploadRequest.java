package com.my.mypicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PictureUploadRequest implements Serializable {
  
    /**  
     * 图片 id（用于修改）  
     */  
    private Long id;

    /**
     * 文件地址
     */
    private String fileUrl;


    // 批量抓取图片用
    /**
     * 图片名称
     */
    private String picName;

    /**
     * 分类
     */
    private String category;

    /**
     * 标签
     */
    private List<String> tags;
    private static final long serialVersionUID = 1L;  
}
