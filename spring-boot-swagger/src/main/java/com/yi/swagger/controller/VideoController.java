package com.yi.swagger.controller;

import cn.hutool.core.util.StrUtil;
import com.yi.swagger.model.MessageResult;
import com.yi.swagger.model.Videos;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录注册
 * @author YI
 * @date 2018-8-17 10:20:05
 */
@RestController
@Api(value = "视频业务接口", tags = "视频业务接口")
@RequestMapping("/video")
public class VideoController {

    /**
     * 上传视频
     * @param userId 用户id
     * @param bgmId 背景音乐id
     * @param videoSeconds 背景音乐播放长度
     * @param videoWidth 视频宽度
     * @param videoHeight 视频高度
     * @param desc 视频描述
     * @param file 视频文件
     * @return
     */
    @ApiOperation(value="上传视频", notes="上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value="用户id", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="bgmId", value="背景音乐id",
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoSeconds", value="背景音乐播放长度", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoWidth", value="视频宽度", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoHeight", value="视频高度", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="desc", value="视频描述",
                    dataType="String", paramType="form")
    })
    @RequestMapping(value="/upload", headers="content-type=multipart/form-data", method = RequestMethod.POST)
    public MessageResult upload(String userId,
                                String bgmId, double videoSeconds,
                                int videoWidth, int videoHeight,
                                String desc,
                                @ApiParam(value="短视频文件", required=true) MultipartFile file) {
        String fileName = file.getOriginalFilename();

        Videos video = new Videos();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoSeconds((float)videoSeconds);
        video.setVideoHeight(videoHeight);
        video.setVideoWidth(videoWidth);
        video.setVideoDesc(desc);
        video.setVideoPath(fileName);


        return MessageResult.ok(video);
    }

    /**
     * 上传视频封面
     * @param userId 用户id
     * @param videoId   视频id
     * @param file  文件
     * @return
     * @throws Exception
     */
    @ApiOperation(value="上传封面", notes="上传封面的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value="用户id", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoId", value="视频主键id", required=true,
                    dataType="String", paramType="form")
    })
    @RequestMapping(value="/uploadCover", headers="content-type=multipart/form-data", method = RequestMethod.POST)
    @Deprecated
    public MessageResult uploadCover(String userId, String videoId,
                                       @ApiParam(value="视频封面图片", required=true) MultipartFile file) throws Exception {
        if (StrUtil.isBlank(videoId) || StrUtil.isBlank(userId)) {
            return MessageResult.errorMsg("视频主键id和用户id不能为空...");
        }

        Videos videos = new Videos();

        String fileName = file.getOriginalFilename();

        videos.setUserId(userId);
        videos.setId(videoId);
        videos.setVideoPath(fileName);
        videos.setVideoDesc("这是一个视频是封面");

        return MessageResult.ok(videos);
    }

    /**
     * 查询视频分页接口
     * @param video 视频信息
     * @param isSaveRecord 1 - 需要保存 0 - 不需要保存 ，或者为空的时候
     * @param page 当前页
     * @return
     */
    @ApiImplicitParam(name="page", value="当前页数", required=true, dataType="Integer", paramType="query")
    @ApiOperation(value="视频分页", notes="查询视频分页接口")
    @RequestMapping(value="/showAll", method = RequestMethod.POST)
    public MessageResult showAll(@RequestBody Videos video, Integer isSaveRecord, Integer page) {
        page = page == null ? 1 : page;
        Map<String, Object> map = new HashMap<>(16);

        map.put("video", video);
        map.put("isSaveRecord", isSaveRecord);
        map.put("page", page);
        map.put("pageSize", 20);

        return MessageResult.ok(map);
    }

    /**
     * 热搜词
     * @return
     */
    @ApiOperation(value="热搜", notes="查询热搜接口")
    @RequestMapping(value="/hot", method = RequestMethod.POST)
    public MessageResult hot() {
        String[] array = {"德玛西亚", "艾泽拉斯", "兽人永不为奴", "吾将带头冲锋"};

        return MessageResult.ok(array);
    }
}
