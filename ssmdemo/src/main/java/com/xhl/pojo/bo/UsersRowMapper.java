package com.xhl.pojo.bo;

import com.xhl.pojo.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users u = new Users();
        u.setUid(rs.getInt("uid"));
        u.setUname(rs.getString("uname"));
        u.setPwd(rs.getString("pwd"));
        u.setCdate(rs.getDate("cdate"));
        u.setMoney(rs.getInt("money"));
        return u;
    }
}
