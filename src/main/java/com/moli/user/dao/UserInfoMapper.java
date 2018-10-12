package com.moli.user.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

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
public interface UserInfoMapper {

    @Select({
            "select     nickName,",
            "           userName,",
            "           jobNumber,",
            "           mobile,",
            "           balance,",
            "           emptypeType,",
            "           tcRatio,",
            "           djCnt,",
            "           workStatus,",
            "           userStatus,",
            "           sysUnitId",
            "from user_info",
            "where  logonId = #{logonId}",
            "   and password = #{password}"
    })
    Map<String,Object> loginUser(@Param("logonId") String logonId, @Param("password") String password);

    @SelectProvider(type = UserInfoSqlProvider.class,method = "saveUserInfo")
    void saveUserInfo(Map<String,Object> paramsMap);
}
