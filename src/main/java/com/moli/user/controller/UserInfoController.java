package com.moli.user.controller;

import com.moli.user.dao.UserInfoMapper;
import com.moli.util.Md5Utils;
import com.moli.util.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map map(String logonId, String password,HttpServletRequest request) {
        Map dataMap = new HashMap<>();
        try
        {
            if(StringUtils.isBlank(logonId) || StringUtils.isBlank(password))
            {
                dataMap.put("code", ResultCode.ERROR_CODE);
                dataMap.put("msg","用户名密码不能为空");
                return dataMap;
            }
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
            e.printStackTrace();
            dataMap.put("code", ResultCode.ERROR_CODE);
            dataMap.put("msg","MD5转换异常");
        }
        return dataMap;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Map register(String mobile, String nickName,String cardImg,String userName, String cardNo, String emergencyContact,
                        String emergencyContactTel, String headImg, String papersImg, String personalImg,
                        String appVersion, Integer sex) {
        Map dataMap = new HashMap<>();
        Map<String,Object> paramsMap = new HashMap<>();
        if(StringUtils.isNotBlank(mobile))
        {
            paramsMap.put("mobile",mobile);
        }
        if(StringUtils.isNotBlank(nickName))
        {
            paramsMap.put("nickName",nickName);
        }
        if(StringUtils.isNotBlank(cardImg))
        {
            paramsMap.put("cardImg",cardImg);
        }
        if(StringUtils.isNotBlank(userName))
        {
            paramsMap.put("userName",userName);
        }
        if(StringUtils.isNotBlank(cardNo))
        {
            paramsMap.put("cardNo",cardNo);
        }
        if(StringUtils.isNotBlank(emergencyContact))
        {
            paramsMap.put("emergencyContact",emergencyContact);
        }
        if(StringUtils.isNotBlank(emergencyContactTel))
        {
            paramsMap.put("emergencyContactTel",emergencyContactTel);
        }
        if(StringUtils.isNotBlank(headImg))
        {
            paramsMap.put("headImg",headImg);
        }
        if(StringUtils.isNotBlank(papersImg))
        {
            paramsMap.put("papersImg",papersImg);
        }
        if(StringUtils.isNotBlank(personalImg))
        {
            paramsMap.put("personalImg",personalImg);
        }
        if(StringUtils.isNotBlank(appVersion))
        {
            paramsMap.put("appVersion",appVersion);
        }
        if(sex != null)
        {
            paramsMap.put("sex",sex);
        }
        userInfoMapper.saveUserInfo(paramsMap);
        Integer id = (Integer) paramsMap.get("id");
        System.out.println("id::" + id);
        dataMap.put("code",ResultCode.SUCCESS_CODE);
        dataMap.put("data",id);
        return dataMap;
    }

}
