package com.xhl.mapper;

import com.xhl.pojo.Users;
import com.xhl.pojo.bo.UsersRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

//数据层
@Repository
public class UserMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;
//    更新用户金额
    public int updateByUname(String uname,int money,int type){
        String sql;
        if(type==1) sql = "UPDATE users SET cdate=NOW(), money=money-? WHERE uname=?";
        else sql = "UPDATE users SET cdate=NOW(), money=money+? WHERE uname=?";
        int i = jdbcTemplate.update(sql, money, uname);
        return i;
    }

//     根据用户名密码查询用户信息
    public Users selectByUnameAndPwd(String uname,String pwd){
        String sql="SELECT uid, uname, pwd, cdate, money FROM users WHERE uname = ? and pwd = ?";
        Users u = jdbcTemplate.queryForObject(sql, new UsersRowMapper(),uname,pwd);
        return u;
    }
}
