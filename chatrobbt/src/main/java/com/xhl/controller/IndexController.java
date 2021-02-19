package com.xhl.controller;

import com.github.pagehelper.PageInfo;
import com.xhl.pojo.ReplyContent;
import com.xhl.service.IndexService;
import com.xhl.service.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/index/")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 获取默认回复
     * @return
     */
    @RequestMapping("getDefaultReply.do")
    public ModelAndView getDefaultRaply(){

        ModelAndView mav = new ModelAndView();
        //获取默认回复
        String defaultReply = indexService.getDefaultReply();
        //把数据封装
        mav.addObject("dl",defaultReply);
        //定位页面
        mav.setViewName("chat");

        return mav;
    }
    /**
     * 获取自动回复
     * @return
     */
    public String getAutoRaply(){
        return indexService.getAutoRaply();
    }
    /**
     * 根据输入内容获取回复
     * @return
     */
    @RequestMapping("getReply.do")
    @ResponseBody
    public List<String> getRaply(String keyword){
        return indexService.getRaply(keyword);
    }

    //    =============后台管理需求==============
    /**
     * 获取所有输入内容
     *
     * @return
     */
    @RequestMapping("getAll.do")
    public ModelAndView getAll(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "3") Integer pageSize){
        ModelAndView mav = new ModelAndView();
        PageInfo pageInfo = indexService.getAll(pageNum,pageSize);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("listdemo");
        return mav;
    }

    /**
     * 添加回复内容
     */
    @RequestMapping("addone.do")
    public ModelAndView addOne(ReplyContent replyContent){
        ModelAndView mav = null;
        int i = indexService.addOne(replyContent);
        if(i>0){
            mav = getAll(1,3);
        }else{
            mav = new ModelAndView();
            mav.addObject("message","增加回复失败");
            mav.setViewName("error");
        }
        return mav;
    }
    /**
     * 删除一条数据
     *
     * @param
     * @return
     */
    @RequestMapping("deleteOne.do")
    public ModelAndView deleteOne(Integer id) {
        ModelAndView mav = null;
        int i = indexService.deleteOne(id);
        if (i > 0) {
            mav = getAll(1,3);
        } else {
            mav = new ModelAndView();
            mav.addObject("message", "删除回复失败");
            mav.setViewName("error");
        }
        return mav;
    }
}
