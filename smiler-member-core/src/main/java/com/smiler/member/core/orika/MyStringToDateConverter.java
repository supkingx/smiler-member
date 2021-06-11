package com.smiler.member.core.orika;

import com.smiler.member.constant.CommonConstant;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/6
 */
@Component
public class MyStringToDateConverter extends CustomConverter<String, Date> {

    @Override
    public Date convert(String dateStr, Type<? extends Date> type, MappingContext mappingContext) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstant.DEFAULT_DATE_FORMAT);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
