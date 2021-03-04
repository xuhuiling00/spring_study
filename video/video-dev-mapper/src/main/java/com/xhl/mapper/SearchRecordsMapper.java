package com.xhl.mapper;

import com.xhl.pojo.SearchRecords;
import com.xhl.utils.MyMapper;

import java.util.List;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
    public List<String> getHotWords();
}