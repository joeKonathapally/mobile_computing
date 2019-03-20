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

import static android.content.ContentValues.TAG;


public class Move_file extends AppCompatActivity {

    private File sourceLocation;
    private File targetLocation;

    public Move_file(){
        super();
    }
    public void moveFileInitial(String file,String targetFolder){
        sourceLocation=new File(""+file);
        targetLocation=new File("/sdcard/"+targetFolder+"/"+file);
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
    public void moveFile(String sourceFolder, String file,String targetFolder){
        sourceLocation=new File("/"+sourceFolder+"/"+file);
        targetLocation=new File("/sdcard/"+targetFolder+"/"+file);
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
    //

    public void garbage(){
        Drawable drawable = getDrawable(R.drawable.sample4);
        Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("sdcard",MODE_PRIVATE);
        file = new File(file, "sample1"+".jpg");

        //File file = new File("/sdcard/Source/sample1.JPG");
        try{
            OutputStream stream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }


}
