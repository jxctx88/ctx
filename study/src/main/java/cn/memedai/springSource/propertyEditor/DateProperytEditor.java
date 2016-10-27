package cn.memedai.springSource.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProperytEditor extends PropertyEditorSupport {
	private String format = "yyyy-MM-dd";
	
	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("agr: " + text);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d;
		try {
			d = sdf.parse(text);
			this.setValue(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		super.setAsText(text);
	}
}
