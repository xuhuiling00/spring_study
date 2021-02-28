package com.xhl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhl.pojo.Videos;
import com.xhl.pojo.vo.VideosVo;
import com.xhl.utils.MyMapper;

public interface VideosVoMapper extends MyMapper<Videos> {
	
	public List<VideosVo> queryAllVideos(@Param("videoDesc") String videoDesc,@Param("videoCategory") String videoCategory);
	public void addVideoLikeCount(String videoId);
	public void reduceAddVideoLikeCount(String videoId);
	public List<VideosVo> queryVideosByUser(String userId);
	

}