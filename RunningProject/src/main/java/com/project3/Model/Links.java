package com.project3.Model;

public class Links {

	private int linkId;
	private int userId;
	private String longUrl;
	private String shortUrl;
	private int clicks;
	/**
	 * @return the linkId
	 */
	public int getLinkId() {
		return linkId;
	}
	/**
	 * @param linkId the linkId to set
	 */
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the longUrl
	 */
	public String getLongUrl() {
		return longUrl;
	}
	/**
	 * @param longUrl the longUrl to set
	 */
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	/**
	 * @return the shortUrl
	 */
	public String getShortUrl() {
		return shortUrl;
	}
	/**
	 * @param shortUrl the shortUrl to set
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	/**
	 * @return the clicks
	 */
	public int getClicks() {
		return clicks;
	}
	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

}
