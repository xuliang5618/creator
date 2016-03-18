package com.sdu.receive.domain.msg;

import com.sdu.receive.domain.common.WXXmlElementName;
import org.w3c.dom.Document;


/**
 * 事件消息
 * 注意： 此消息只能是微信服务器推送过来
 * */
public class Msg4Event extends Msg {
	
	/** 订阅 */
	public static final String SUBSCRIBE = "subscribe";
	/** 取消订阅 */
	public static final String UNSUBSCRIBE = "unsubscribe";
	
	// 自定义菜单点击事件
	
	public static final String CLICK = "CLICK";
	public static final String SCAN = "SCAN";// 用户已关注时的事件推送
	public static final String LOCATION = "LOCATION";// 上报地理位置事件
	
	
	/**
	 * 乐嘟视频
	 */
	public static String KEY_101 = "res_tw@101";
	
	/**
	 * APP介绍
	 */
	public static String KEY_102 = "res_tw@102";
	
	/**
	 * DM介绍
	 */
	public static String KEY_103 = "res_tw@103";
	
	/***
	 * 暂时未用
	 */
	public static String KEY_104 = "res_tw@104";
	
	/**
	 * 暂时未用2
	 */
	public static String KEY_105 = "res_tw@105";
	
	/**
	 * 嘟嘟杂谈
	 */
	public static String KEY_201 = "res_tw@201";
	
	/**
	 * 乖乖狗课堂
	 */
	public static String KEY_202 = "res_tw@202";
	
	/**
	 * 猫咪必修课
	 */
	public static String KEY_203 = "res_tw@203";
	/***
	 * 暂时未用
	 */
	public static String KEY_204 = "res_tw@204";
	
	/**
	 * 暂时未用2
	 */
	public static String KEY_205 = "res_tw@205";
	
	/**
	 * 视频秀
	 */
	public static String KEY_301 = "res_tw@301";
	
	/**
	 * 封面定制
	 */
	public static String KEY_302 = "res_tw@302";
	
	/**
	 * 行业活动
	 */
	public static String KEY_303 = "res_tw@303";
	/**
	 * 新品推荐
	 */
	public static String KEY_304 = "res_tw@304";
	/**
	 * 产品试用
	 */
	public static String KEY_305 = "res_tw@305";
	
	
	
	
	// 事件类型subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
	private String event;
	//事件KEY值，与自定义菜单接口中KEY值对应
	private String eventKey;
	// 二维码的ticket，可用来换取二维码图片
	private String ticket;
	
	private String latitude;//地理位置纬度
	private String longitude;//地理位置经度
	private String precision;//地理位置精度
	/**
	 * 程序内部调用
	 * */
	public Msg4Event(Msg4Head head) {
		this.head = head;
	}


	/**
	 * 因为此消息都是由微信服务器发送给我们的，我们不用发给微信服务器，因此不用实现write
	 * */
	@Override
	public void write(Document document) { }
	
	
	@Override
	public void read(Document document) {
		this.event = getElementContent(document, WXXmlElementName.EVENT);
		//用户未关注时，进行关注后的事件推送
		//用户已关注时的事件推送
		if(SUBSCRIBE.equals(this.event) || UNSUBSCRIBE.equals(this.event) || SCAN.equals(this.event)){
			this.eventKey = getElementContent(document, WXXmlElementName.EVENT_KEY);
			this.ticket = getElementContent(document, WXXmlElementName.TICKET);
		}else if(LOCATION.equals(this.event)){// 上报地理位置事件
			this.latitude = getElementContent(document, WXXmlElementName.LATITUDE);
			this.longitude = getElementContent(document, WXXmlElementName.LONGITUDE); 
			this.precision = getElementContent(document, WXXmlElementName.PRECISION);
		}else if(CLICK.equals(this.event)){// 自定义菜单事件
			this.eventKey = getElementContent(document, WXXmlElementName.EVENT_KEY);
		}
	}


	
	
	
	
	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getEventKey() {
		return eventKey;
	}


	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}


	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}


	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}


	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return precision;
	}


	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	
	
	
	
}
