package cn.javass.commons.web.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConverter implements Converter<String, Date> {

	private String dateFormatPattern ;
	public StringToDateConverter(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	public Date convert(String source) {
		// TODO Auto-generated method stub
		if(!StringUtils.hasLength(source)){
			return null;
		}
		DateFormat df = new SimpleDateFormat(dateFormatPattern);
		try{
			return df.parse(source);
		}catch(Exception e){
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式%s，但格式是[%s]", dateFormatPattern, source)); 
		}
	}

}
