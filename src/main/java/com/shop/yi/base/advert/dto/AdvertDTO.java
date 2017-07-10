package com.shop.yi.base.advert.dto;

/**
 * @ClassName: AdvertDTO
 * @Description: TODO
 * @author: yanguo(****@email.com)
 * @date: 2017年6月21日 下午9:55:00
 * @Version: 1.0.0
 */
public class AdvertDTO {
	private String advId;
	private String advType;
	private String advSeq;
	private String advTitle;
	private String advImg;
	private String advStatus;
	private String advContent;
	
	public AdvertDTO(){
		
	}
	public String getAdvId() {
		return advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public String getAdvType() {
		return advType;
	}
	public void setAdvType(String advType) {
		this.advType = advType;
	}
	public String getAdvSeq() {
		return advSeq;
	}
	public void setAdvSeq(String advSeq) {
		this.advSeq = advSeq;
	}
	public String getAdvTitle() {
		return advTitle;
	}
	public void setAdvTitle(String advTitle) {
		this.advTitle = advTitle;
	}
	public String getAdvImg() {
		return advImg;
	}
	public void setAdvImg(String advImg) {
		this.advImg = advImg;
	}
	public String getAdvStatus() {
		return advStatus;
	}
	public void setAdvStatus(String advStatus) {
		this.advStatus = advStatus;
	}
	public String getAdvContent() {
		return advContent;
	}
	public void setAdvContent(String advContent) {
		this.advContent = advContent;
	}
}
