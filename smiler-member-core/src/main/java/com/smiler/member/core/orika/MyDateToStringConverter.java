package com.smiler.member.core.orika;

import com.smiler.member.constant.CommonConstant;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 时间格式转化
 * @Author: wangchao
 * @Date: 2021/5/30
 */
@Component
public class MyDateToStringConverter extends CustomConverter<Date, String> {
    @Override
    public String convert(Date date, Type<? extends String> type, MappingContext mappingContext) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstant.DEFAULT_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }
}
