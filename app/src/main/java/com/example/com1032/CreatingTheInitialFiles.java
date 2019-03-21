package com.example.com1032;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CreatingTheInitialFiles implements Runnable {

    private File[] file;
    private String name;

    public CreatingTheInitialFiles(File[] files, String name){
        super();
        this.file=files;
        this.name=name;
    }

    @Override
    public void run() {

        while(MainActivity.position<file.length)
        {
            while(!MainActivity.accessing)
            {
                try{
                    MainActivity.tv.setText(MainActivity.position+"");
                    MainActivity.accessing=true;
                    Move_file move = new Move_file();
                    move.moveFile(file[MainActivity.position]);
                    MainActivity.position++;
                    MainActivity.accessing=false;
                    System.out.println("Thread "+name+" "+MainActivity.position);
                    try{
                        Thread.currentThread().sleep(1000);
                        System.out.println("sleeping");
                    }catch(Exception e){

                    }
                }
                catch(Exception e){

                }
            }

        }

    }
}
