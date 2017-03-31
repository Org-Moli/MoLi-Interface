package com.moli.interceptor;

import com.moli.user.dao.UserSafeMapper;
import com.moli.util.HMacSha256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Map;

/**
 * <p>名称</p>
 * <p/>
 * <p>wikiURL</p>
 *
 * @author zb.jiang
 * @version 1.0
 * @Date 2017/3/31
 */
public class ControllerLogIntercept extends HandlerInterceptorAdapter {

    private static final String LOGIN_URL = "/moLi/user/login";

    private static final String ERROR_APIKEY_URL = "/moLi/common/apiKeyError";

    private static final String ERROR_TIMESTAMP_URL = "/moLi/common/timeStampError";

    private static final String ERROR_SIGN_URL = "/moLi/common/signError";

    private static final long invalidTime = 5 * 60 * 1000;//时效五分钟

    @Autowired
    private UserSafeMapper userSafeMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        boolean success = super.preHandle(request, response, handler);

        String requestURL = request.getRequestURI();

        if(requestURL.equalsIgnoreCase(ControllerLogIntercept.LOGIN_URL) || requestURL.contains("/moLi/common/"))
        {
            return true;
        }
        else
        {
            String api_key = request.getParameter("api_key");
            if(StringUtils.isBlank(api_key))
            {
                response.sendRedirect( ControllerLogIntercept.ERROR_APIKEY_URL);
                return false;
            }

            String timestamp = request.getParameter("timestamp");
            if(StringUtils.isBlank(timestamp))
            {
                response.sendRedirect( ControllerLogIntercept.ERROR_TIMESTAMP_URL);
                return false;
            }

            String sign = request.getParameter("sign");
            if(StringUtils.isBlank(sign))
            {
                response.sendRedirect( ControllerLogIntercept.ERROR_SIGN_URL);
                return false;
            }

            //判断是否存在此api_key
            Map keyMap = userSafeMapper.getUserSafeByApiKey(api_key);
            if(keyMap == null || keyMap.isEmpty())
            {
                response.sendRedirect( ControllerLogIntercept.ERROR_APIKEY_URL);
                return false;
            }

            long serverTime = Calendar.getInstance().getTimeInMillis();
            long clientTime = Long.parseLong(timestamp);
            long flag = serverTime - clientTime;
            if (flag < 0 || flag > ControllerLogIntercept.invalidTime)
            {
                response.sendRedirect( ControllerLogIntercept.ERROR_TIMESTAMP_URL);
                return false;
            }

            //开始校验sign
            //校验规则:apikey + security_key + timestamp + requestURL [hmacsha256加密] = sign
            String security_key = (String) keyMap.get("security_key");
            //生成sign
            String value = api_key + timestamp + requestURL;
            String serviceSign = HMacSha256Util.encryptHMAC(value,security_key);
            if(!sign.equalsIgnoreCase(serviceSign))
            {
                response.sendRedirect( ControllerLogIntercept.ERROR_SIGN_URL);
                return false;
            }
        }
        return success;
    }
}
