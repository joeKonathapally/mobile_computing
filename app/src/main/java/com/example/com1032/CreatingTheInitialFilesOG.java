package com.example.com1032;

import android.widget.Toast;

import java.io.File;

public class CreatingTheInitialFilesOG implements Runnable {

    private File[] file;
    private String name;

    public CreatingTheInitialFilesOG(File[] files, String name){
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