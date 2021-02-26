package com.xhl.controller;

import java.util.UUID;

import com.xhl.utils.VideoJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.xhl.pojo.Users;
import com.xhl.service.UserService;
import com.xhl.utils.MD5Utils;

@RestController
@Api(value = "用户注册的接口 ", tags = { "注册和登陆的controller" })
public class RegistLoginController{
	@Autowired
	private UserService userService;

	/**
	 * 用户注册接口
	 */
	@ApiOperation(value = "注册", notes = "用户注册的接口")
	@ApiImplicitParam(name = "userId", value = "用户id",
			required = true, dataType = "String", paramType = "query")
	@PostMapping("/regist")
	public VideoJsonResult regist(@RequestBody Users user) {
		// 1.用户和密码是否为空 不能为空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return VideoJsonResult.errorMsg("小主,用户名和密码不能为空哦");
		}
		// 2.用户名是否存在数据库里 ，如果存在 则抛出
		boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
		// 3.保存用户，注册信息
		if (!usernameIsExist) {
			try {
				user.setNickname(user.getUsername());
				user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
				user.setReceiveLikeCounts(0);
				user.setFansCounts(0);
				user.setFollowCounts(0);
				user.setFaceImage(null);
				userService.saveUser(user);// 调用插入数据库

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return VideoJsonResult.errorMsg("用户已经存在,换一下吧 (●'◡'●)");
		}

		//为了安全性考虑不设置密码为空
		user.setPassword("");
		return VideoJsonResult.ok(user);
	}


}
