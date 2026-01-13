package com.my.mypicturebackend.api.imagesearch;

import com.my.mypicturebackend.api.imagesearch.model.ImageSearchResult;
import com.my.mypicturebackend.api.imagesearch.sub.GetImageFirstUrlApi;
import com.my.mypicturebackend.api.imagesearch.sub.GetImageListApi;
import com.my.mypicturebackend.api.imagesearch.sub.GetImagePageUrlApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageSearchApiFacade {

    /**
     * 搜索图片
     *
     * @param imageUrl
     * @return
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        log.info("图片页面地址：{}", imagePageUrl);
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        log.info("图片列表页面地址：{}", imageFirstUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        log.info("图片列表：{}", imageList);
        return imageList;
    }

    public static void main(String[] args) {
        // 测试以图搜图功能
        String imageUrl = "https://haowallpaper.com/link/common/file/getCroppingImg/18183258022923648";
        List<ImageSearchResult> resultList = searchImage(imageUrl);
        System.out.println("结果列表" + resultList);
    }
}
