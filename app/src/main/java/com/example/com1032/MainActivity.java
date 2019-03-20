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


        //attempting to make the new file directories for the images
        try{

            File source = new File("/sdcard/Source");

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



    }


    public void onClickButton(View v){

        for(int i=0;i<10;i++)
        {
            try{
                Drawable drawable = getDrawable(holding[i]);
                Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
                File file = new File("/sdcard/Source/sample"+(i+1)+".jpeg");
                OutputStream stream = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.JPEG,100,stream);
                stream.flush();
                stream.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        Move_file move = new Move_file();
        File[] files= move.getDirectories();
        for(int i=0;i<files.length;i++)
        {
            move.moveFile(files[i]);
        }




    }


}
