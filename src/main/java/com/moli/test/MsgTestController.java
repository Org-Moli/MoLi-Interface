package com.moli.test;

import com.moli.test.bean.Message;
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
 * @Date 2017/3/20
 */
@RestController
@RequestMapping("/msg")
public class MsgTestController {
    @RequestMapping("/say")
    public Message say(String name) {
        Message message = new Message();
        message.setName(name);
        message.setText("hello," + name);
        return message;
    }

    @RequestMapping("/map")
    public Map map(String name) {
        Map message = new HashMap<>();
        message.put("name", name);
        message.put("helloMap", "hello," + name);
        return message;
    }
}
