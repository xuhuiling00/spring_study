package com.xhl.mapper;

import com.xhl.pojo.Users;
import com.xhl.pojo.bo.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapper {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Users selectByUphoneAndUpwd(String uphone,String upwd){
        String sql = "select * from users where uphone = ? and upwd = ?";
        Users u = jdbcTemplate.queryForObject(sql,new UserRowMapper(),uphone,upwd);
        return u;
    }
}
