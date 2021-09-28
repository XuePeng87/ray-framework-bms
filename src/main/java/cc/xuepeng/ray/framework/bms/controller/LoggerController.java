package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/logger")
@Slf4j
public class LoggerController {

    @PutMapping("/v1/open")
    public HttpResult<String> openDebug() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.getConfiguration().getLoggerConfig("ROOT").setLevel(Level.DEBUG);
        ctx.updateLoggers();
        log.debug("日期开启debug模式。");
        return DefaultHttpResultFactory.success("日志开启debug模式。", StringUtils.EMPTY);
    }

    @PutMapping("/v1/close")
    public HttpResult<String> closeDebug() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.getConfiguration().getLoggerConfig("ROOT").setLevel(Level.INFO);
        ctx.updateLoggers();
        log.info("日期关闭debug模式。");
        return DefaultHttpResultFactory.success("日志关闭debug模式。", StringUtils.EMPTY);
    }

}
