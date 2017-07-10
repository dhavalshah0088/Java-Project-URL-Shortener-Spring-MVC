package com.project3.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project3.Model.Links;

public class LinkRowMapper implements RowMapper<Links> {
	public Links mapRow(ResultSet rs, int rowNum) throws SQLException {
		Links LinksInfo = new Links();
		// User userInfo = new User();
		System.out.println(rs.getInt("linkid"));
		LinksInfo.setLinkId(rs.getInt("linkid"));
		LinksInfo.setShortUrl(rs.getString("shorturl"));
		LinksInfo.setLongUrl(rs.getString("longurl"));
		LinksInfo.setClicks(rs.getInt("clicks"));
		return LinksInfo;
	}
}
