package com.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // "routing" 在路由时
        // "post" 在routing 和 error 之后
        // "error" 请求处理发生错误
        return "pre";//在路由前
    }

    @Override
    public int filterOrder() {
        return 0;//数值越小优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true;//返回true,则执行本过滤器,否则不执行.在业务上可以通过此返回值来决定过滤器的有效范围
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        }
        log.info("pre filter......");
        return null;
    }
}
