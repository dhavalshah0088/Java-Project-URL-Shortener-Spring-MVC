package com.project3.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.project3.Model.Links;
import com.project3.service.LinkService;
@Service("LinkService")
public class LinkServiceImplementation implements LinkService {

	private JdbcTemplate jdbctemplate;
	
	@Override
	public void insertLinks(Links links) {
		// TODO Auto-generated method stub
		System.out.println("links jdbc t:  " + getJdbctemplate());

		System.out.println(links);
		String sql = "INSERT INTO links" + "(USERID,LONGURL,SHORTURL,CLICKS) VALUES (?,?,?,?)";
		getJdbctemplate().update(sql,
				new Object[] { links.getUserId(), links.getLongUrl(), links.getShortUrl(), links.getClicks() });


	}
	public List<Links> getUrlByUser(int id) {
		List<Links> linkUser = null;
		System.out.println("USERID:" + id);
		String loginQuery = "SELECT linkid,shorturl,longurl,clicks FROM links WHERE userid= ?";
		try {
			linkUser = getJdbctemplate().query(loginQuery, new LinkRowMapper(), new Object[] { id });
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		return linkUser;	
		
	}

	@Override
	public Links getLongUrl(String shortUrl) {
		// TODO Auto-generated method stub
		Links longUrl = new Links();
		String getLongUrlQuery = "SELECT longurl,shorturl FROM links WHERE shorturl= ?";
		try {
			System.out.println("HERE"+shortUrl);
			longUrl = (Links) getJdbctemplate().queryForObject(getLongUrlQuery,new publicLinkRowMapper(), new Object[] { shortUrl });
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		return longUrl;

	}

	@Override
	public Links getClicks(String shortUrl) {
		// TODO Auto-generated method stub
		Links longrerouteUrl = new Links();
		String getLongUrlQuery = "SELECT longurl,shorturl, clicks FROM links WHERE shorturl= ?";
		try {
			System.out.println("HERE"+shortUrl);	
			System.out.println(longrerouteUrl = (Links) getJdbctemplate().queryForObject(getLongUrlQuery,new publicClicksRowMapper(), new Object[] { shortUrl }));
		System.out.println("NOTHERE");
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error" + e);
		}
		
		return longrerouteUrl;

	}

	@Override
	public void updateClicks(int clicks, String longUrl) {
		// TODO Auto-generated method stub
		System.out.println("links update jdbc t:  " + getJdbctemplate());

		String sql="UPDATE Links SET CLICKS= ? Where LONGURL=?";
		getJdbctemplate().update(sql,new Object[] {clicks,longUrl});
	System.out.println("Problem");

	}
	/**
	 * @return the jdbctemplate
	 */
	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	/**
	 * @param jdbctemplate the jdbctemplate to set
	 */
	@Autowired
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}


}
