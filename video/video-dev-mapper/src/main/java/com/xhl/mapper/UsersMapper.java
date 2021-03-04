package com.xhl.mapper;

import com.xhl.pojo.Users;
import com.xhl.utils.MyMapper;


public interface UsersMapper extends MyMapper<Users> {
    /**
     * 累加
     * @param userId
     */
    public void addReceiveLikeCount(String userId);
    /**
     * 累减
     * @param userId
     */
    public void reduceReceiveLikeCount(String userId);
    /**
     * 粉丝累加
     */
    public void addFansCount(String userId);
    /**
     * 粉丝累减
     * @param userId
     */
    public void reduceFansCount(String userId);


    /**
     * 用户关注的数量增加
     * @param userId
     */
    public void followWithAdd(String userId);

    /**
     * 用户关注的数量减少
     * @param userId
     */

    public void followWithReduce(String userId);
}