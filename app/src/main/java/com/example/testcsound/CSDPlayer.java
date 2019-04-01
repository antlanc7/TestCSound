package com.example.testcsound;

import android.content.Context;

import com.csounds.CsoundObj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSDPlayer extends CsoundObj {
    public void startCsound(Context context, int csdRawId){

        StringBuilder str = new StringBuilder();

        InputStream is = context.getResources().openRawResource(csdRawId);
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        String line;

        try {
            while ((line = r.readLine()) != null) {
                str.append(line).append("\n");
            }
        } catch (IOException ios) {

        }

        File f;

        try {
            f = File.createTempFile("temp", ".csd", context.getCacheDir());
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(str.toString().getBytes());
            fos.close();
            super.startCsound(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}