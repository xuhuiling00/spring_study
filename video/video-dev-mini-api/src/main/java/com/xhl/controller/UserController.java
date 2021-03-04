package com.xhl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.xhl.pojo.vo.Publisher;
import com.xhl.pojo.vo.UsersVo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xhl.pojo.Users;
import com.xhl.pojo.UsersFans;
import com.xhl.service.UserService;
import com.xhl.utils.VideoJsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "用户相关业务接口 ", tags = { "用户相关业务的controller" })
@RequestMapping("/user")
public class UserController extends BasicController {
	@Autowired
	private UserService userService;


	@ApiOperation(value = "用户上传头像", notes = "用户上传头像接口")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
	@PostMapping("/uploadFace")
	public VideoJsonResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) {

		
		if(StringUtils.isBlank(userId))
		{
			return VideoJsonResult.errorException("用户id不能为空...");
		}
		
		//文件保存命名空间
		String fileSpace = "D:/IdeaProjects/spring_study/video/green_videos_dev";
		// 保存到数据库路径 相对路径
		String uploadPathDB = "/" + userId + "/face";

		if (files != null && files.length > 0) {

			FileOutputStream fileOutputStream = null;
			InputStream inputStream = null;
			try {
				//获取文件名
				String fileName = files[0].getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					// 文件上传的最终保存路径
					String finalFacePath = fileSpace + "/" + uploadPathDB + "/" + fileName;
					// 数据库保存路径
					uploadPathDB += ("/" + fileName);

					File outFile = new File(finalFacePath);

					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父亲文件夹
						outFile.getParentFile().mkdirs();
					}

					fileOutputStream = new FileOutputStream(outFile);
					inputStream = files[0].getInputStream();

					IOUtils.copy(inputStream, fileOutputStream);
					 //调用接口
					
				}else{
					return VideoJsonResult.errorException("上传失败了");
				}
			} catch (IOException e) {
				e.printStackTrace();
				return VideoJsonResult.errorException("上传失败了");
			}
			finally {
                //如果不为空
				//则flush 关闭
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
//					IOUtils.closeQuietly(fileOutputStream);
//					IOUtils.closeQuietly(inputStream);

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		
		Users user=new Users();
		user.setId(userId);
		user.setFaceImage(uploadPathDB);
		userService.updateUserInfo(user);
		return VideoJsonResult.ok(user);
	}



	@ApiOperation(value = "查询信息", notes = "查询用户信息接口")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
	@PostMapping("/query")
	public VideoJsonResult uploadFace(String userId)
	{
		if(StringUtils.isBlank(userId))
		{
	       return VideoJsonResult.errorMsg("用户id不能为空");
		}
	   Users userInfo=userService.queryUserInfo(userId);
	   UsersVo userVO=new UsersVo();
	   BeanUtils.copyProperties(userInfo, userVO);
       return VideoJsonResult.ok(userVO);		
	}

	@ApiOperation(value = "查询是否关注用户", notes = "查询用户关注信息接口")
	@PostMapping("/queryIsFollowed")
	public VideoJsonResult queryIsFollowed(String userId,String fanId)
	{
		if(StringUtils.isBlank(userId)||StringUtils.isBlank(fanId))
		{
	       return VideoJsonResult.errorMsg("用户id不能为空");
		}
	   boolean isFollowed=userService.queryIsFollowed(userId, fanId);
       return VideoJsonResult.ok(isFollowed);		
	}

	@ApiOperation(value = "查询up主的信息", notes = "查询up主信息接口")
	@PostMapping("/queryPublisher")
	public VideoJsonResult queryPublisher(String loginUserId,String videoId,String publisherUserId)
	{

		if(StringUtils.isBlank(loginUserId)||StringUtils.isBlank(videoId)||StringUtils.isBlank(publisherUserId))
		{
	       return VideoJsonResult.errorMsg("");
		}

		//1.查询视频发布者的信息
		//2.查询当前登录者和视频的点赞关系
	   Users userInfo=userService.queryUserInfo(publisherUserId);
	   UsersVo publisher=new UsersVo();
	   BeanUtils.copyProperties(userInfo, publisher);
	   
	   boolean userLikeVideo =userService.isUserLikeVideo(loginUserId, videoId);
	   Publisher publish =new Publisher();
	   publish.setPulisher(publisher);
	   publish.setUserLikeVideo(userLikeVideo);
       return VideoJsonResult.ok(publish);		
	}

	@ApiOperation(value = "关注up", notes = "关注up主接口")
	@PostMapping(value = "/userFollow" )//用戶跟随
	public VideoJsonResult userFollow(String userId,String fanId)//用户的id 用户的关注的id 
	{
		userService.userFollow(userId,fanId);
		return VideoJsonResult.ok();
	}

	@ApiOperation(value = "取消关注", notes = "取关up主接口")
	@PostMapping(value = "/userUnFollow" )//用戶跟随
	public VideoJsonResult userUnFollow(String userId,String fanId)//用户的id 用户的关注的id 
	{ 
		userService.userUnFollow(userId,fanId);
		return VideoJsonResult.ok();
	}
	
}
