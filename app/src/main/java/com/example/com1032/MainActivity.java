package com.example.com1032;

import android.annotation.TargetApi;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";
    private int[] holding = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holding[0]=R.drawable.sample1;
        holding[1]=R.drawable.sample2;
        holding[2]=R.drawable.sample3;
        holding[3]=R.drawable.sample4;
        holding[4]=R.drawable.sample5;
        holding[5]=R.drawable.sample6;
        holding[6]=R.drawable.sample7;
        holding[7]=R.drawable.sample8;
        holding[8]=R.drawable.sample9;
        holding[9]=R.drawable.sample10;

    }


    public void onClickButton(View v){


        // the file to be moved
        File sourceLocation = new File ("/sdcard/Test_garbage/NewTextFile.txt");


        //the destination for the file to be moved to
        File targetLocation = new File ("/sdcard/Test_garbage_1/NewTextFile.txt");


        //attempting to make the new file directories for the images
        try{

            File source = new File("/sdcard/Source");
            File trach = new File("drawable-v24/sample1.JPG");
            File destination = new File("/sdcard/Destination");
            if(!source.exists())
            {
                if(!source.mkdir())
                {
                    Toast.makeText(getApplicationContext(),"Failed to make the directory",Toast.LENGTH_SHORT).show();
                }
            }
            if(!destination.exists())
            {
                if(!destination.mkdir())
                {
                    Toast.makeText(getApplicationContext(),"Failed to make the directory",Toast.LENGTH_SHORT).show();
                }
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.gc();
        Drawable drawable = getDrawable(R.drawable.sample3);
        Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
        //ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = new File("/sdcard/Source/sample"+3+".jpeg");

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
        /**
        for(int i=0;i<10;i++)
        {
            Drawable drawable = getDrawable(holding[i]);
            Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
            //ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
            File file = new File("/sdcard/Source/sample"+(i+1)+".jpeg");

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
        **/



        /**

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
         **/
    }


}
