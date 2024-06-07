package com.example.simplytdl;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class fileHelper {

    public static final String FileName = "TaskListInfo.dat";

    public static void writeData(ArrayList<String> item, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FileName, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<String> readData(Context context){
        ArrayList<String> itemList = null;

        try {
            FileInputStream fis = context.openFileInput(FileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<String>) ois.readObject();


        }catch (FileNotFoundException e) {
            itemList = new ArrayList<>();
            writeData(itemList, context);
        }catch (IOException | ClassNotFoundException e) {
            itemList = new ArrayList<>();
            throw new RuntimeException(e);
        }

        return itemList;
    }

}
