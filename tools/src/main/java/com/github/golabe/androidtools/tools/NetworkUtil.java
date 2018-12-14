package com.github.golabe.androidtools.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络工具类
 */
public class NetworkUtil {

    private static final class Singleton {
        private static final NetworkUtil INSTANCE = new NetworkUtil();
    }

    private NetworkUtil() {
    }

    public static NetworkUtil getInstance() {
        return Singleton.INSTANCE;
    }

    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 判断网络是否可用
     *
     * @return
     */
    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo == null || !networkInfo.isAvailable());
    }

    /**
     * 获取IP地址
     * @return
     */
    public String getLocalIpAddres() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
            while (ni.hasMoreElements()){
                Enumeration<InetAddress> addresses = ni.nextElement().getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()){
                        ip=inetAddress.getHostAddress();
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 判断是否是3g网络
     * @return
     */
    public boolean is3G(){
        ConnectivityManager manager = getConnectivityManager();
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.getType()==ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 判断是否为WIFI 网络
     * @return
     */
    public boolean isWifi(){
        ConnectivityManager manager = getConnectivityManager();
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.getType()==ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断是否为2g网络
     * @return
     */
    public  boolean is2G(){
        ConnectivityManager manager = getConnectivityManager();
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && (networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
                || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS || networkInfo
                .getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA);
    }
    private ConnectivityManager getConnectivityManager(){
       return  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}

