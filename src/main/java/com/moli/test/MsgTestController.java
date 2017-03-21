package com.moli.test;

import com.moli.test.bean.Message;
import com.moli.util.ApiAuthUtil;
import com.moli.util.Signature;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
 * @Date 2017/3/20
 */
@RestController
@RequestMapping("/msg")
public class MsgTestController {
    @RequestMapping(value = "/say/{name}",method= RequestMethod.GET)
    public Map say(@PathVariable("name") String name,
                       HttpServletRequest request) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        String reqTime = request.getHeader("ReqTime-Time");
        String sign = request.getHeader("sign");
        String token = "abcd123456";
        Map checkMap = ApiAuthUtil.check(reqTime, name, token, sign);
        if((Boolean)checkMap.get("isSuccess"))
        {
            Message message = new Message();
            message.setName(name);
            message.setText("hello," + name);
            checkMap.put("message",message);
            return checkMap;
        }
        else
        {
            long endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("time:" + (endTime - startTime));
            return checkMap;
        }
    }

    @RequestMapping("/map")
    public Map map(String name) {
        Map message = new HashMap<>();
        message.put("name", name);
        message.put("helloMap", "hello," + name);
        return message;
    }
}
