package com.pk.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.pk.model.ExamResult;
@Component
public class ExamResultRowMapper implements RowMapper<ExamResult> {

	@Override
	public ExamResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new ExamResult(
						rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getDouble(4)
				);
	}

}
