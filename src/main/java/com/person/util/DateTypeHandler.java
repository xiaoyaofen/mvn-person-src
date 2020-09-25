package com.person.util;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedJdbcTypes({JdbcType.BIGINT, JdbcType.TIMESTAMP})
@MappedTypes(Date.class)
public class DateTypeHandler implements TypeHandler<Date> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }

    @Override
    public Date getResult(ResultSet resultSet, String s) throws SQLException {
        long time= resultSet.getLong(s);
        return new Date(time);
    }

    @Override
    public Date getResult(ResultSet resultSet, int i) throws SQLException {
        long time = resultSet.getLong(i);
        return new Date(time);
    }

    @Override
    public Date getResult(CallableStatement callableStatement, int i) throws SQLException {
        long time = callableStatement.getLong(i);
        return new Date(time);
    }
}
