package com.example.com1032;

import android.annotation.TargetApi;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.com1032.Move_file.semaphore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";
    private int[] holding = new int[10];
    public static int position = 0;
    public static boolean accessing = false;
    static Semaphore semaphore = new Semaphore(1);
    private Move_file move = new Move_file();
    public static TextView tv;
    private File[] files = move.getDirectories("/sdcard/Source");
    public Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);

        holding[0] = R.drawable.sample1;
        holding[1] = R.drawable.sample2;
        holding[2] = R.drawable.sample3;
        holding[3] = R.drawable.sample4;
        holding[4] = R.drawable.sample5;
        holding[5] = R.drawable.sample6;
        holding[6] = R.drawable.sample7;
        holding[7] = R.drawable.sample8;
        holding[8] = R.drawable.sample9;
        holding[9] = R.drawable.sample10;


        //attempting to make the new file directories for the images
        try {

            File source = new File("/sdcard/Source");

            File destination = new File("/sdcard/Destination");
            if (!source.exists()) {
                if (!source.mkdir()) {
                    Toast.makeText(getApplicationContext(), "Failed to make the directory", Toast.LENGTH_SHORT).show();
                }
            }
            if (!destination.exists()) {
                if (!destination.mkdir()) {
                    Toast.makeText(getApplicationContext(), "Failed to make the directory", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


/*
        final Runnable mUIUpdater = new Runnable() {
            @Override
            public void run() {
                try{
                    if(position==0)
                    {
                        tv.setText(position+"");
                    }
                    else{
                        tv.setText(""+position);
                    }
                    handler.postDelayed(this,1);
                }catch (Exception e){

                }
            }
        };


        handler.post(mUIUpdater);



*/

    }


    public void onClickButton(View v) {

        for (int i = 0; i < 10; i++) {
            try {
                Bitmap bm = BitmapFactory.decodeResource(getResources(), holding[i]);
                File file = new File("/sdcard/Source/sample" + (i + 1) + ".jpeg");
                FileOutputStream stream1 = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                stream1.flush();
                stream1.close();
                holding[i] = 0;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        files = move.getDirectories("/sdcard/Source");


    }

    public void transferFiles(View v) {


        CreatingTheInitialFiles A = new CreatingTheInitialFiles(files, "1");
        CreatingTheInitialFiles B = new CreatingTheInitialFiles(files, "2");

        Thread AThread = new Thread(A);
        Thread BThread = new Thread(B);

        AThread.start();
        BThread.start();


    }


    public class CreatingTheInitialFiles implements Runnable {

        private File[] file;
        private String name;

        public CreatingTheInitialFiles(File[] files, String name) {
            super();
            this.file = files;
            this.name = name;
        }

        @Override
        public void run() {
            try {

            while (MainActivity.position < file.length) {
                while (!MainActivity.accessing) {


                                            try{
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(" "+position);



                                }
                            });
                        }catch(Exception e){}



                        MainActivity.accessing = true;
                        Move_file move = new Move_file();
                        move.moveFile(file[MainActivity.position]);
                        MainActivity.position++;
                        MainActivity.accessing = false;
                        System.out.println("Thread " + name + " " + MainActivity.position);
                        Thread.currentThread().sleep(1000);
                        System.out.println("sleeping");

                }
            }
            } catch (Exception e) {

            }

        }

    }

}


