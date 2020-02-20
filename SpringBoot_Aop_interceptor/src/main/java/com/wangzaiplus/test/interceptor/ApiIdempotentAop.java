package com.wangzaiplus.test.interceptor;

import com.wangzaiplus.test.annotation.LoginRequired;
import com.wangzaiplus.test.common.ResponseCode;
import com.wangzaiplus.test.exception.ServiceException;
import com.wangzaiplus.test.service.TokenService;
import com.wangzaiplus.test.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.rmi.ServerException;

/**
 * @date ：2020/2/19 23:15
 * @Author : 安儿
 * Description: 拦截器：记录用户操作，记录用户登录
 */
@Aspect
@Component
public class ApiIdempotentAop {
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private TokenService tokenService;

    private static final String TOKEN_NAME = "token";
    private static final Logger logger = LoggerFactory.getLogger(ApiIdempotentAop.class);


    /**
     * 定义拦截规则：拦截com.huidong.qzh.standard包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.wangzaiplus.test..*(..))" +
            " && ( @annotation(org.springframework.web.bind.annotation.RequestMapping )" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping ))")
    public void controllerMethodPointcut() {
    }

    /***
     * 解析权限注解
     * @return 返回注解的authorities值
     * @throws Exception
     */
    public  Boolean privilegeParse(Method method) throws Exception {
        //获取该方法
        if(method.isAnnotationPresent(LoginRequired.class)){
            LoginRequired annotation = method.getAnnotation(LoginRequired.class);
            return annotation.loginRequired();
        }
        return false;
    }

    /**
     * 拦截器具体实现
     *
     * @param joinPoint
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //制定拦截器规则，也可以直接写入拦截路径
    public Object Interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        //得到方法的访问权限
        boolean loginRequired = privilegeParse(targetMethod);

        //如果该方法上没有权限注解，直接调用目标方法
        if(!loginRequired) {
            return joinPoint.proceed();
        }else{
            //RequestContextHolder：持有上下文的Request容器,获取到当前请求的request
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest httpServletRequest = sra.getRequest();
            tokenService.checkToken(httpServletRequest);   // 在server 中进行验证
        }
        return ResponseCode.ILLEGAL_ARGUMENT;
    }


    public void check(HttpServletRequest request){
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }
        if (!jedisUtil.exists(token)) {
            System.out.println("存在");
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        } else {
            System.out.println("不存在");
        }

        Long del = jedisUtil.del(token);
        System.out.println("++++++++" + del.toString());
        if (del <= 0) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
