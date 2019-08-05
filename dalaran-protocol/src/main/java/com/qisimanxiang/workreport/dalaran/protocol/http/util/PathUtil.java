package com.qisimanxiang.workreport.dalaran.protocol.http.util;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
public class PathUtil {

    /**
     * path 检查
     */
    private static final Pattern PATTERN = Pattern.compile("^[-a-zA-Z0-9+&@#/%=~_|]+$");

    public static boolean checkPath(String path) {
        return PATTERN.matcher(path).matches();
    }

    /**
     * path 优化
     */
    private static final String SEPARATOR = "/";

    public static String pathOptimizat(String path) {
        if (StringUtils.isEmpty(path) || SEPARATOR.equals(path)) {
            return "/";
        }
        if (!path.startsWith(SEPARATOR)) {
            path = SEPARATOR + path;
        }
        if (path.endsWith(SEPARATOR)) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }
}
