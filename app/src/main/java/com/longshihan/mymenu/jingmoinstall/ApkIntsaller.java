package com.longshihan.mymenu.jingmoinstall;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by longshihan on 2017/7/13.
 * 静默安装
 */

public class ApkIntsaller {
    private Context mContext;
    private File mFile;


    public ApkIntsaller(Context context, File file) {
        mContext = context;
        mFile = file;
    }

    public void runInstall() {
        new Thread() {
            @Override
            public void run() {
                boolean success = installByRoot();
                if (!success) {
                    //智能安装




                }


            }
        }.start();
    }

    /**
     * root权限 下静默安装
     *
     * @return
     */
    private boolean installByRoot() {
        boolean result = false;
        Process process = null;
        OutputStream os = null;
        String commond = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = process.getOutputStream();
            //pm
            os.write("pm install -r".getBytes());
            os.flush();
            os.write("exit\n".getBytes());
            process.waitFor();
            br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            if (!sb.toString().contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                os = null;
                br = null;
                process.destroy();
            }

        }
        return result;
    }
}
