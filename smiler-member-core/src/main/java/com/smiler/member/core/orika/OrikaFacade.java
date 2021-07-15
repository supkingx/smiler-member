package com.smiler.member.core.orika;

import com.github.pagehelper.Page;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/7
 */
@Component
public class OrikaFacade {
    private MapperFacade mapperFacade = null;
    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Autowired(required = false)
    private List<OrikaRegistry> orikaRegistries = new ArrayList<>();

    @PostConstruct
    private void init() {
        if (CollectionUtils.isNotEmpty(orikaRegistries)) {
            for (OrikaRegistry orikaRegistry : orikaRegistries) {
                orikaRegistry.register(mapperFactory);
            }
        }
        mapperFactory.getConverterFactory().registerConverter(new MyDateToStringConverter());
        mapperFactory.getConverterFactory().registerConverter(new MyStringToDateConverter());
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public <S, T> T map(S source, Class<T> target) {
        return mapperFacade.map(source, target);
    }

    public <S, T> List<T> mapAsList(List<S> source, Class<T> target) {
        List<T> list;
        if (CollectionUtils.isEmpty(source)) {
            list = Collections.emptyList();
        } else {
            list = mapperFacade.mapAsList(source, target);
        }

        if (source instanceof Page) {
            Page<S> sourcePage = (Page<S>) source;
            Page<T> page = new Page<>();
            page.setPageSize(sourcePage.getPageSize());
            page.setPages(sourcePage.getPages());
            page.setPageNum(sourcePage.getPageNum());
            page.setTotal(sourcePage.getTotal());
            page.addAll(list);
            return page;
        }
        return mapperFacade.mapAsList(source, target);
    }
}
