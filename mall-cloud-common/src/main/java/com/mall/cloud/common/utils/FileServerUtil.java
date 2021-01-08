package com.mall.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * <p>封装Qicloud项目FileServerUtil类.<br></p>
 * <p>文件服务FileServerUtil工具类<br></p>
 *
 * @author Powered by marklin 2020-11-19 11:39
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class FileServerUtil {
    /**
     * 判断数组中是否存在某个值
     *
     * @param sources 源参数
     * @param target  目标参数
     * @return
     */
    public static boolean checkIsExist(String[] sources, String target) {
        return Arrays.asList(sources).contains(target);
    }

    /**
     * 获取目标路径
     *
     * @param source 源参数
     * @param target 目标参数
     * @return
     */
    private static String achieveTargetPath(String source, String target) {
        return StringUtils.substringAfter(source, target);
    }


    /**
     * 获取文件拓展名
     *
     * @param target 目标参数
     * @return
     */
    public static String achieveFilePostfix(String target) {
        return target.substring(target.lastIndexOf("."));
    }

}
