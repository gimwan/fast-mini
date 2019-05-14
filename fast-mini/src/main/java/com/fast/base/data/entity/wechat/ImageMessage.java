package com.fast.base.data.entity.wechat;

/**
 * 图片消息
 * @author Nice
 *
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}