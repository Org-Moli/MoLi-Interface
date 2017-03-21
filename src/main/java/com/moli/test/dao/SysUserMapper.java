package com.moli.test.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
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
public interface SysUserMapper {

    @Select({
            "select * from sys_user"
    })
    List<Map> listSysUser();

}
