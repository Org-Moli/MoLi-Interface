package com.moli.util;

/**
 * <p>名称</p>
 * <p/>
 * <p>wikiURL</p>
 *
 * @author zb.jiang
 * @version 1.0
 * @Date 2017/3/31
 */
public class ResultCode {

    public static final String SUCCESS_CODE = "0000";//访问成功

    public static final String ERROR_CODE = "9999";//获取数据失败

    public static final String ERROR_APIKEY_CODE = "0001";//apiKey不存在或无效

    public static final String ERROR_TIMESTAMP_CODE = "0002";//timestamp不存在或无效

    public static final String ERROR_SIGN_CODE = "0003";//sign不存在或无效

}
