package com.project3.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project3.Model.Links;

public class publicLinkRowMapper implements RowMapper<Links>{
	public Links mapRow(ResultSet rs, int rowNum) throws SQLException {
		Links LinksInfo = new Links();
		// User userInfo = new User();
		//LinksInfo.setLinkId(rs.getInt("linkid"));
		System.out.println("SHORTURL:"+rs.getString("shorturl"));
		LinksInfo.setShortUrl(rs.getString("shorturl"));
		LinksInfo.setLongUrl(rs.getString("longurl"));
		return LinksInfo;
	}
}
