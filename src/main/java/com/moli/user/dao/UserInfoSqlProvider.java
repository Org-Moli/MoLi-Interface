package com.moli.user.dao;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * <p>名称</p>
 * <p/>
 * <p>wikiURL</p>
 *
 * @author zb.jiang
 * @version 1.0
 * @Date 2017/4/1
 */
public class UserInfoSqlProvider {

    public String saveUserInfo(Map<String,Object> paramsMap) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_info");

        if (paramsMap.get("mobile") != null) {
            sql.VALUES("logonId", "#{logonId,jdbcType=VARCHAR}");
            sql.VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("nickName") != null) {
            sql.VALUES("nickName", "#{nickName,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("cardImg") != null) {
            sql.VALUES("cardImg", "#{cardImg,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("userName") != null) {
            sql.VALUES("userName", "#{userName,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("cardNo") != null) {
            sql.VALUES("cardNo", "#{cardNo,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("emergencyContact") != null) {
            sql.VALUES("emergencyContact", "#{emergencyContact,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("emergencyContactTel") != null) {
            sql.VALUES("emergencyContactTel", "#{emergencyContactTel,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("headImg") != null) {
            sql.VALUES("headImg", "#{headImg,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("papersImg") != null) {
            sql.VALUES("papersImg", "#{papersImg,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("personalImg") != null) {
            sql.VALUES("personalImg", "#{personalImg,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("appVersion") != null) {
            sql.VALUES("appVersion", "#{appVersion,jdbcType=VARCHAR}");
        }

        if (paramsMap.get("sex") != null) {
            sql.VALUES("sex", "#{sex,jdbcType=INTEGER}");
        }

        return sql.toString();
    }
}
