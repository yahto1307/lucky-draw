package com.yahto.hydra.lucky.draw.framework.handler;

import com.alibaba.fastjson.JSON;
import com.yahto.hydra.lucky.draw.common.exception.BusinessException;
import com.yahto.hydra.lucky.draw.model.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yahto on 2018/9/10 9:53 PM
 *
 * @author yahto
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.error("", ex);
        ModelAndView mv = null;
        PrintWriter writer = null;
        if (request.getRequestURI().startsWith("/skye-ward/index")) {
            mv = new ModelAndView("error");
        } else if (request.getRequestURI().endsWith(".ajax")) {
            try {
                writer = response.getWriter();
                response.setContentType("text/html;charset=UTF-8");
                if (ex instanceof BusinessException) {
                    writer.print(JSON.toJSONString(new Result().exception(ex.getMessage(), ex)));
                } else {
                    writer.print(JSON.toJSONString(new Result().exception("未知错误", ex)));
                }
                writer.flush();
            } catch (IOException e) {
                LOGGER.error("", e);
            } finally {
                if (null != writer) {
                    writer.close();
                    writer = null;
                }
            }
        }
        return mv;
    }
}
