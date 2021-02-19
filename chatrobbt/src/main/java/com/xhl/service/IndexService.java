package com.xhl.service;

import com.github.pagehelper.PageInfo;
import com.xhl.pojo.ReplyContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndexService {
    String getDefaultReply();

    String getAutoRaply();

    List<String> getRaply(String keyword);

    PageInfo getAll(Integer pageNum, Integer pageSize);

    int addOne(ReplyContent replyContent);

    int deleteOne(Integer id);
}
