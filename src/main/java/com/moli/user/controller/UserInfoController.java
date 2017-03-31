package com.moli.user.controller;

import com.moli.user.dao.UserInfoMapper;
import com.moli.util.Md5Utils;
import com.moli.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/login")
    public Map map(String logonId, String password) {
        Map dataMap = new HashMap<>();
        try
        {
            Map userMap = userInfoMapper.loginUser(logonId, Md5Utils.md5(password));
            if(userMap != null && !userMap.isEmpty())
            {
                dataMap.put("code", ResultCode.SUCCESS_CODE);
                dataMap.put("data",userMap);
            }
            else
            {
                dataMap.put("code", ResultCode.ERROR_CODE);
                dataMap.put("msg","用户名密码错误");
            }
        } catch (Exception e)
        {
            dataMap.put("code", ResultCode.ERROR_CODE);
            dataMap.put("msg","MD5转换异常");
        }
        return dataMap;
    }

}
