package com.moli.util;

import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>名称</p>
 * <p/>
 * <p>wikiURL</p>
 *
 * @author zb.jiang
 * @version 1.0
 * @Date 2017/3/21
 */
public class ApiAuthUtil {

    public static Map check(String reqTime, String info, String token, String sign)
    {
        Map map = new HashMap<>();
        if (StringUtils.isEmpty(reqTime)) {
            map.put("msg","头部时间为空");
            map.put("isSuccess",Boolean.FALSE);
            return map;
        }
        if (StringUtils.isEmpty(info)) {
            map.put("msg","信息为空");
            map.put("isSuccess",Boolean.FALSE);
            return map;
        }
        if (StringUtils.isEmpty(token)) {
            map.put("msg","没有授权");
            map.put("isSuccess",Boolean.FALSE);
            return map;
        }
        if (StringUtils.isEmpty(sign)) {
            map.put("msg","签名为空");
            map.put("isSuccess",Boolean.FALSE);
            return map;
        }
        long serverTime = Calendar.getInstance().getTimeInMillis();

        long clientTime = Long.parseLong(reqTime);

        long flag = serverTime - clientTime;

        //时效五分钟
        if (flag < 0 || flag > 1000 * 60 * 5) {
            map.put("msg","请求时间过期");
            map.put("isSuccess",Boolean.FALSE);
            return map;
        }
        String allStr = info + reqTime + token;

        String md5 = null;
        try
        {
            md5 = Md5Utils.md5(allStr);
            System.out.println("md5:" + md5);
        } catch (Exception e)
        {
            map.put("msg","签名错误");
            map.put("isSuccess",Boolean.FALSE);
            return map;
        }

        if (sign.equals(md5)) {
            System.out.println("服务端未签名时为:" + allStr);
            System.out.println("服务端签名之后为:" + md5);
            map.put("msg","签名成功");
            map.put("isSuccess",Boolean.TRUE);
            return map;
        }

        map.put("msg","签名错误");
        map.put("isSuccess",Boolean.FALSE);
        return map;
    }
}
