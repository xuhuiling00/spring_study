package com.xhl.mapper;

import com.xhl.pojo.Bgm;
import com.xhl.pojo.BgmExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BgmMapper {
    int countByExample(BgmExample example);

    int deleteByExample(BgmExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bgm record);

    int insertSelective(Bgm record);

    List<Bgm> selectByExample(BgmExample example);

    Bgm selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Bgm record, @Param("example") BgmExample example);

    int updateByExample(@Param("record") Bgm record, @Param("example") BgmExample example);

    int updateByPrimaryKeySelective(Bgm record);

    int updateByPrimaryKey(Bgm record);
}