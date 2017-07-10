package com.project3.service;

import java.util.List;

import com.project3.Model.Links;

public interface LinkService {
	public void insertLinks(Links links);
	public List<Links> getUrlByUser(int userId);
	public Links getLongUrl (String shortUrl);
	public Links getClicks (String shortUrl);
	public void updateClicks(int clicks, String longUrl);

}
