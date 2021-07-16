package com.smiler.member.core.interceptor;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.smiler.member.common.So;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @description: MyBatis拦截器，增强 com.smiler.member.common.So
 * @Author: wangchao
 * @Date: 2021/7/15
 * @see So
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class SoInterceptor implements Interceptor {
    private static final Joiner JOINER_SORT = Joiner.on(",");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object param = invocation.getArgs()[1];
        if (param instanceof So) {
            So so = (So) param;
            Page<Object> page = PageHelper.startPage(so.getCurrentPage(), so.getPageSize());
            page.setCount(so.isEnableCount());
            page.setOrderByOnly(so.isOrderByOnly());
            page.setReasonable(false);
            if (CollectionUtils.isNotEmpty(so.getSortList())) {
                String order = JOINER_SORT.join(so.getSortList());
                page.setOrderBy(order);
            }
            try {
                return invocation.proceed();
            } finally {
                PageHelper.clearPage();
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // NOP
    }
}
