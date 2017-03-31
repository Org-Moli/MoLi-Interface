package com.moli.common;

import com.moli.util.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
@RestController
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/apiKeyError")
    public Map apiKeyError(String name) {
        Map message = new HashMap<>();
        message.put("code", ResultCode.ERROR_APIKEY_CODE);
        message.put("msg","apiKey不存在或无效");
        return message;
    }

    @RequestMapping("/timeStampError")
    public Map timeStampError(String name) {
        Map message = new HashMap<>();
        message.put("code", ResultCode.ERROR_TIMESTAMP_CODE);
        message.put("msg","timeStamp不存在或无效");
        return message;
    }

    @RequestMapping("/signError")
    public Map signError(String name) {
        Map message = new HashMap<>();
        message.put("code", ResultCode.ERROR_SIGN_CODE);
        message.put("msg","sign不存在或无效");
        return message;
    }
}
