package com.bwei.MoNi_San;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * QQ登入
 * Created by 房国伟 on 2018/8/18.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //umeng配置
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        //QQ配置
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        UMConfigure.setLogEnabled(true);
    }
}
