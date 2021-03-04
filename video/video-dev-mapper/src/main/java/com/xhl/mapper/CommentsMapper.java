package com.xhl.mapper;

import com.xhl.pojo.Comments;
import com.xhl.pojo.vo.CommentsVo;
import com.xhl.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsMapper extends MyMapper<Comments> {
    List<CommentsVo> queryAllByVideoId(@Param("videoId") String videoId);
}