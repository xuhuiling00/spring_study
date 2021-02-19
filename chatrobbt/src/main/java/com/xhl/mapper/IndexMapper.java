package com.xhl.mapper;

import com.xhl.pojo.ReplyContent;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IndexMapper {
//    动态代理四个原则：
//    1.接口中的方法名和映射文件中的id名相同
//    2.接口中的传参类型与映射文件的传参类型一致
//    3.接口中的返回值类型与映射文件中的返回值类型一致
//    4.映射文件命名空间与接口绑定

    String getDefaultReply();

    String getAutoRaply();

    List<String> getReplyByKeyWord(String keyword);

    List<String> getReplyByWords(String words);

    List<ReplyContent> getAll();

    int addOne(ReplyContent replyContent);

    int deleteOne(Integer id);
}
