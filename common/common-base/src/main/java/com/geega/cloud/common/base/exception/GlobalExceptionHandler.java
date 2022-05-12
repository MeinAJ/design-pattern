package com.geega.cloud.common.base.exception;

import com.geega.cloud.common.base.response.BizResult;
import com.geega.cloud.common.base.response.error.BizErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一全局日期格式
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    /**
     * 自定义的业务异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public BizResult<Void> bizExceptionHandler(BizException e) {
        log.error("发生业务异常,异常信息:", e);
        return BizResult.error(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BizResult<Void> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        if (e.getLocalizedMessage().contains("JSON parse error")) {
            return BizResult.error(BizErrorEnum.SYSTEM_ERROR.getCode(), "输入参数异常,请核实!");
        }
        return BizResult.error(BizErrorEnum.SYSTEM_ERROR.getCode(), e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(IllegalStateException.class)
    public BizResult<Void> illegalStateException(IllegalStateException e) {
        log.error(e.getMessage(), e);
        if (e.getLocalizedMessage().contains("io.undertow.server.RequestTooBigException")) {
            return BizResult.error(BizErrorEnum.FILE_UPLOAD_ERROR.getCode(), "文件大小超出最大限制");
        }
        return BizResult.error(BizErrorEnum.SYSTEM_ERROR.getCode(), e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BizResult<Void> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return BizResult.error(BizErrorEnum.SYSTEM_ERROR.getCode(), String.format("参数错误:%s不能为空", e.getParameterName()));
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public BizResult<Void> illegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return BizResult.error(BizErrorEnum.SYSTEM_ERROR.getCode(), e.getLocalizedMessage());
    }

    /**
     * 空指针的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public BizResult<Void> exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常,异常信息: ", e);
        return BizResult.error(BizErrorEnum.DATA_NOT_EXIST_ERROR);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BizResult<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("参数错误,异常信息：{}", ex.getMessage());
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        // 将所有错误信息拼接成一个字符串
        String msg = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return BizResult.error(BizErrorEnum.BODY_NOT_MATCH.getCode(), msg);
    }

    /**
     * 其他异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(Exception e) {
        log.error("未知异常！异常信息: ", e);
        String eMessage = e.getMessage();
        try {
            Throwable firstCause = e.getCause();
            Throwable secondCause = firstCause.getCause();
            if (secondCause instanceof BizException) {
                final BizException bizException = (BizException) secondCause;
                return BizResult.error(bizException.getCode(), bizException.getMsg());
            }
            eMessage = secondCause.getMessage();
        } catch (Exception exception) {
            //do nothing
        }
        return BizResult.error(BizErrorEnum.INTERNAL_SERVER_ERROR.getCode(), eMessage);
    }

}

