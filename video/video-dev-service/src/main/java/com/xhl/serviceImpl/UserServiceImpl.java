package com.xhl.serviceImpl;

import com.xhl.mapper.UsersFansMapper;
import com.xhl.mapper.UsersLikeVideosMapper;
import com.xhl.mapper.UsersMapper;
import com.xhl.pojo.Users;
import com.xhl.pojo.UsersFans;
import com.xhl.pojo.UsersLikeVideos;
import com.xhl.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UsersMapper userMapper;
	@Resource
	private UsersLikeVideosMapper usersLikeVideosMapper;
	@Resource
	private UsersFansMapper userFansMapper;
	@Autowired
	private Sid sid;
	/**
	 * 判断用户是否存在 Boolean
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
    //如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务.
	@Override
	public boolean queryUsernameIsExist(String username) {
		Users user = new Users();
		user.setUsername(username);
		Users result = userMapper.selectOne(user);
		return result == null ? false : true;
	}

	/**
	 * 保存账号 插入数据库
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	//如果有事务, 那么加入事务, 没有的话新建一个(默认情况下)
	@Override
	public void saveUser(Users user) {
		String userId = sid.nextShort();
		user.setId(userId);
		userMapper.insert(user);
	}
	/**
	 * 登录查询，根据用户名密码
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String username, String password) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("username",username);
		criteria.andEqualTo("password",password);
		Users users = userMapper.selectOneByExample(userExample);
		return users;
	}

	/**
	 * 根据用户名获取用户
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users getUser(String username) {
		Users user = new Users();
		user.setUsername(username);
		Users result = userMapper.selectOne(user);
		return result;
	}

	/**
	 * 更新用户信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateUserInfo(Users user) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("id",user.getId());
		//updateByExampleSelective：那个属性有值就更新哪个
		userMapper.updateByExampleSelective(user,userExample);
	}

	/**
	 * 根据id查询用户信息
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserInfo(String userid) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("id",userid);
		Users users = userMapper.selectOneByExample(userExample);
		return users;
	}

	//查询用户是否点赞视频
	@Override
	public Boolean isUserLikeVideo(String userId, String videoId) {
		Example userExample = new Example(UsersLikeVideos.class);// 创建一个模板 条件
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("videoId", videoId);
		List<UsersLikeVideos> ls = usersLikeVideosMapper.selectByExample(userExample);// 查询excample
		if (ls != null && ls.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void userFollow(String userId, String fanId) {
		//1.粉丝id和用户id关联
		//2.更新up主的的粉丝数量
		//3.更新用户关注数量
		UsersFans userFans = new UsersFans();
		String id = Sid.next();
		userFans.setId(id);
		userFans.setUserId(userId);
		userFans.setFanId(fanId);
		userFansMapper.insert(userFans);// 增加用戶和粉絲的关系
		userMapper.addFansCount(userId); // 用户的粉丝数加1 //fanId 粉丝的关注量+1
		userMapper.followWithAdd(fanId);//增加粉丝的关注量+1
	}

	@Override
	public void userUnFollow(String userId, String fanId) {
		// 1.查询出用户
		// 2.删除相关的关系记录
		// 3.用户的粉丝数-1
		Example example=new Example(UsersFans.class);
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("userId",userId);
		criteria.andEqualTo("fanId",fanId);
		userFansMapper.deleteByExample(example);
		userMapper.reduceFansCount(userId);
		userMapper.followWithReduce(fanId);//粉丝的关注量-1
	}

	//查询是否关注用户
	@Override
	public boolean queryIsFollowed(String userId, String fanId) {
		Example userExample = new Example(UsersFans.class);// 创建一个模板 条件
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("fanId", fanId);
		List<UsersFans> ls = userFansMapper.selectByExample(userExample);// 查询excample
		if (ls != null && ls.size() > 0) {
			return true;
		}
		return false;
	}
}
