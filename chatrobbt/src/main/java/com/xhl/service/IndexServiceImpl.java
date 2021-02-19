package com.xhl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import com.xhl.mapper.IndexMapper;
import com.xhl.pojo.ReplyContent;
import com.xhl.utils.SqlSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService{

    @Autowired
    private IndexMapper indexMapper;

    @Override
    public String getDefaultReply() {
        return indexMapper.getDefaultReply();
    }

    @Override
    public String getAutoRaply() {
        return indexMapper.getAutoRaply();
    }

    @Override
    public List<String> getRaply(String keyword) {
        // 创建空集合用来接受数据
        List<String> li = null;

        // 参数非空判断
        if (StringUtils.isNullOrEmpty(keyword)) {
            return li;
        }
        //根据关键词获取数据
        li = indexMapper.getReplyByKeyWord(keyword);
        if (li.size() > 0) {
            return li;
        }
//        处理参数
        String words = "%"+keyword+"%";

        //根据模糊字获取数据
        li = indexMapper.getReplyByWords(words);
        if(li.size() > 0){
            return li;
        }

        //如果数据为空，返回自动回复内容
        if (li.size() == 0) {
            li.add(this.getAutoRaply());
            return li;
        }

        return li;
    }

    @Override
    public PageInfo getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ReplyContent> all = indexMapper.getAll();
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }

    @Override
    public int addOne(ReplyContent replyContent) {
        int i = indexMapper.addOne(replyContent);
        return i;
    }

    @Override
    public int deleteOne(Integer id) {
        int i = indexMapper.deleteOne(id);
        return i;
    }


}
