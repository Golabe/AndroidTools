package com.github.golabe.androidtools.tools;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppUtil {
    private Context context;

    private static final class Singleton {
        private static final AppUtil INSTANCE = new AppUtil();
    }

    private AppUtil() {
    }

    public static AppUtil getInstance() {
        return Singleton.INSTANCE;
    }

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 获取包名
     *
     * @return
     */
    public String getPackageName() {
        return context.getPackageName();
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public String getVersionName() {
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public long getVersionCode() {
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            return packageInfo.getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 启动应用安装程序
     */
    public void installApk(Activity activity, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    /**
     * 获取除系统应用之外的所有APP
     *
     * @return
     */
    public List<PackageInfo> getInstalledPackages() {
        PackageManager manager = getPackageManager();
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        List<PackageInfo> packageInfoList = new ArrayList<>();
        for (int i = 0; i < installedPackages.size(); i++) {
            if ((installedPackages.get(i).applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                packageInfoList.add(installedPackages.get(i));
            }
        }
        return packageInfoList;
    }

    /**
     * 获取最大内存
     *
     * @return
     */
    public Long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1024;
    }
    /**
     * 获取手机系统版本号
     * @return
     */
    public int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    private PackageManager getPackageManager() {
        return context.getPackageManager();
    }
}
