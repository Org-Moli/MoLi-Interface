package com.moli.user.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface UserSafeMapper {

    @Select({
            "select * from user_safe where api_key = #{api_key}"
    })
    Map getUserSafeByApiKey(@Param("api_key") String api_key);
}
