package com.example.com1032;

import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import static android.content.ContentValues.TAG;


public class Move_file{

    private File sourceLocation;
    private File targetLocation;
    static Semaphore semaphore = new Semaphore(1);

    public Move_file(){
        super();
    }
    public void moveFile(File file){
        sourceLocation=new File("/sdcard/Source/"+file.getName());
        targetLocation=new File("/sdcard/Destination/"+file.getName());
        try {

            if(sourceLocation.renameTo(targetLocation)){
                Log.v(TAG, "Move file successful.");
            }else{
                Log.v(TAG, "Move file failed.");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File[] getDirectories(String str){
        File file = new File(""+str);
        File[] files = file.listFiles();
        return files;

    }




}
