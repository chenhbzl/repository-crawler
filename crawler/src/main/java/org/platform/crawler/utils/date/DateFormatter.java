package org.platform.crawler.utils.date;

import java.util.HashMap;
import java.util.Map;

public enum DateFormatter {
	
	TIME("yyyyMMdd HH:mm:ss"), 
	DATE("yyyyMMdd"), 
	MONTH("yyyyMM");

	private String formatString;
	private static Map<String, ThreadLocal<java.text.SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<java.text.SimpleDateFormat>>();

	private DateFormatter(String formatString) {
		this.formatString = formatString;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public java.text.SimpleDateFormat get() {
		ThreadLocal<java.text.SimpleDateFormat> res = (ThreadLocal) sdfMap.get(this.formatString);
		if (null == res) {
			synchronized (sdfMap) {
				if (null == res) {
					res = new ThreadLocal() {
						protected java.text.SimpleDateFormat initialValue() {
							return new java.text.SimpleDateFormat(DateFormatter.this.formatString);
						}
					};
					sdfMap.put(this.formatString, res);
				}
			}
		}
		return (java.text.SimpleDateFormat) res.get();
	}
}
