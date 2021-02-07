package com.xhl.pojo.bo;

import com.xhl.pojo.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users u = new Users();
        u.setId(rs.getInt("id"));
        u.setUphone(rs.getString("uphone"));
        u.setUemail(rs.getString("uemail"));
        u.setUpwd(rs.getString("upwd"));
        u.setUname(rs.getString("uname"));
        u.setCreat_time(rs.getDate("creat_time"));
        u.setUpdate_time(rs.getDate("update_time"));

        return u;
    }
}
