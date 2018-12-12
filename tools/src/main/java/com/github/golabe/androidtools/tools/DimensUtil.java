package com.github.golabe.androidtools.tools;

import android.content.Context;

/**
 * 尺寸转换
 */
public class DimensUtil {

    /**
     * DP 转PX
     * @param ctx
     * @param dp
     * @return
     */
    public static int dp2px(Context ctx,float dp){
      return (int) (ctx.getResources().getDisplayMetrics().density*dp+0.5F);
    }

    /**
     * PX 转 DP
     * @param ctx
     * @param px
     * @return
     */
    public static int px2p(Context ctx,float px){
        return (int) (px/ctx.getResources().getDisplayMetrics().density+0.5F);
    }

    /**
     * SP 转 PX
     * @param ctx
     * @param sp
     * @return
     */
    public static int sp2px(Context ctx,float sp){
        return (int) (ctx.getResources().getDisplayMetrics().scaledDensity*sp+0.5F);
    }

    /**
     * PX 转 SP
     * @param ctx
     * @param px
     * @return
     */
    public static int px2sp(Context ctx,float px){
        return (int) (px/ctx.getResources().getDisplayMetrics().scaledDensity+0.5F);
    }



}
