package com.scau.rent.utils.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
/**
 * Date类型的转换器
 * @author Administrator
 *
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String resource) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(resource);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
     
}
