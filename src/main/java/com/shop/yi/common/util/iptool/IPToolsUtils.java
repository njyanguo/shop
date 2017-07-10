package com.shop.yi.common.util.iptool;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: IPToolsUtils 
 * @Description: TODO
 * @author: yanguo(****@email.com)
 * @date: 2017年5月28日 下午3:10:38  
 * @Version: 1.0.0
 */
public class IPToolsUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(IPToolsUtils.class);
	
	public static String hexByte(byte b) {
        String s = "000000" + Integer.toHexString(b);
        return s.substring(s.length() - 2);
    }
	/**
	 * 获取客户端MAC地址
	 * @Title: getMAC 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
    public static String getMAC() {
        Enumeration<NetworkInterface> el;
        String mac_s = "";
        try {
            el = NetworkInterface.getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;
                mac_s = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"
                        + hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"
                        + hexByte(mac[4]) + "-" + hexByte(mac[5]);
                if(LOGGER.isDebugEnabled())
    				LOGGER.debug(mac_s + "MAC地址");

            }
        } catch (SocketException e) {
        	if(LOGGER.isDebugEnabled())
				LOGGER.debug("", e);
        }
        return mac_s;
    }
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     */
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        String ip = request.getHeader("X-Forwarded-For");
 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
    /*public static void main(String[] args) {
        MacAddress m = new MacAddress();
        m.getMAC();

    }*/
	
}
