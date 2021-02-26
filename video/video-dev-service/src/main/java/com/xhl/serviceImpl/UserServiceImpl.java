package com.xhl.serviceImpl;

import com.xhl.mapper.UsersMapper;
import com.xhl.pojo.Users;
import com.xhl.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
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

	@Override
	public Users getUser(String username) {
		Users user = new Users();
		user.setUsername(username);
		Users result = userMapper.selectOne(user);
		return result;
	}

	@Override
	public void updateUserInfo(Users user) {

	}

	@Override
	public Users queryUserInfo(String userid) {
		return null;
	}

	@Override
	public Boolean isUserLikeVideo(String userId, String videoId) {
		return null;
	}

	@Override
	public void userFollow(String userId, String fanId) {

	}

	@Override
	public void userUnFollow(String userId, String fanId) {

	}

	@Override
	public boolean queryIsFollowed(String userId, String fanId) {
		return false;
	}
}
