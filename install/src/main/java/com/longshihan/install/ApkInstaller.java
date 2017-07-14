package com.longshihan.install;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @author longshihan
 * @time 2017/7/14 9:35
 * @des
 */

public class ApkInstaller {
    private Context mContext;
    private String mFilePath;


    public ApkInstaller(Context context, String pathFile) {
        mContext = context;
        mFilePath = pathFile;
    }


    public void install() {
        new Thread(){
            @Override
            public void run() {
                boolean installer=installSlient();
                if (!installer){
                    //智能安装
                    Uri uri = Uri.fromFile(new File(mFilePath));
                    Intent localIntent = new Intent(Intent.ACTION_VIEW);
                    localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                    mContext.startActivity(localIntent);
                }
            }
        }.start();

    }



    //静默安装
    private boolean installSlient() {
        boolean isSu=false;
        String cmd = "pm install -r "+mFilePath;
        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            //静默安装需要root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.write(cmd.getBytes());
            os.writeBytes("\n");
            os.writeBytes("exit\n");
            os.flush();
            //执行命令
            process.waitFor();
            //获取返回结果
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
            if (!errorMsg.toString().contains("Failure")) {
                isSu = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
           isSu=false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSu;
    }
}
