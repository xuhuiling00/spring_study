package com.xhl.serviceImpl;

import com.xhl.mapper.UsersMapper;
import com.xhl.pojo.Users;
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

	@Override
	public Boolean isUserLikeVideo(String userId, String videoId) {
		return null;
	}

	@Override
	public void userFollow(String userId, String fanId) {
		//1.粉丝id和用户id关联
		//2.更新up主的的粉丝数量
	}

	@Override
	public void userUnFollow(String userId, String fanId) {

	}

	@Override
	public boolean queryIsFollowed(String userId, String fanId) {
		return false;
	}
}
