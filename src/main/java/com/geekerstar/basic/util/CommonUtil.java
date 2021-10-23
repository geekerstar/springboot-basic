package com.geekerstar.basic.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.net.*;
import java.util.Enumeration;
import java.util.Set;

/**
 * @author geekerstar
 * @date 2021/8/14 18:30
 * @description
 */
public class CommonUtil {

    public static String getErrorResult(Object o, Class<?>... groups) {
        StringBuilder errorMsg = new StringBuilder();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(o, groups);
        for (ConstraintViolation<Object> constraintViolation : set) {
            errorMsg.append(constraintViolation.getMessage()).append(" ");
        }
        return errorMsg.toString();
    }

    /**
     * 获取局域网IP地址
     *
     * @return InetAddress
     */
    public static InetAddress getInet4Address() {
        try {
            // 获取所有网络接口
            Enumeration<NetworkInterface> allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            // 遍历所有网络接口
            for (; allNetworkInterfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = allNetworkInterfaces.nextElement();
                // 如果此网络接口为 回环接口 或者 虚拟接口(子接口) 或者 未启用 或者 描述中包含VM
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp() || networkInterface.getDisplayName().contains("VM")) {
                    // 继续下次循环
                    continue;
                }
//                // 如果不是Intel与Realtek的网卡
//                if (!(networkInterface.getDisplayName().contains("Intel")) && !(networkInterface.getDisplayName().contains("Realtek"))) {
//                    // 继续下次循环
//                    continue;
//                }
                // 遍历此接口下的所有IP（因为包括子网掩码各种信息）
                for (Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses(); inetAddressEnumeration.hasMoreElements(); ) {
                    InetAddress inetAddress = inetAddressEnumeration.nextElement();
                    // 如果此IP不为空
                    if (inetAddress != null) {
                        // 如果此IP为IPV4 则返回
                        if (inetAddress instanceof Inet4Address) {
                            return inetAddress;
                        }
                        // 这样判断IPV4更快
                        if (inetAddress.getAddress().length == 4) {
                            return inetAddress;
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取端口号
     *
     * @param href
     * @return
     */
    public static int parsePort(String href) {
        URL url = null;
        try {
            url = new URL(href.replace("ws", "http"));
        } catch (Exception ignore) {
        }
        // 端口号; 如果 href 中没有明确指定则为 -1
        assert url != null;
        int port = url.getPort();
        if (port < 0) {
            // 获取对应协议的默认端口号
            port = url.getDefaultPort();
        }
        return port;
    }

    /**
     * 获取IP地址
     *
     * @param href
     * @return
     */
    public static String parseIp(String href) {
        URL url = null;
        try {
            url = new URL(href.replace("ws", "http"));
        } catch (Exception ignore) {
        }
        assert url != null;
        String host = url.getHost();
        // 根据域名查找IP地址
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(host);
        } catch (Exception ignore) {
        }
        // IP 地址
        assert inetAddress != null;
        return inetAddress.getHostAddress();
    }
}
