package com.allancslima.omdbclient.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Allan Lima on 15/01/2018.
 */

public class ImageUtils {

    public static void saveToInternalStorage(Context context, Bitmap image,
                                             long objectId) throws IOException {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File directory = contextWrapper.getDir("images", Context.MODE_PRIVATE);

        File imageFile = new File(directory, objectId + ".jpg");
        FileOutputStream stream = new FileOutputStream(imageFile);

        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        stream.close();
    }

    public static Bitmap loadImageFromStorage(Context context, long objectId) {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File directory = contextWrapper.getDir("images", Context.MODE_PRIVATE);
        Bitmap image = null;

        try {
            File imageFile = new File(directory, objectId + ".jpg");
            FileInputStream stream = new FileInputStream(imageFile);

            image = BitmapFactory.decodeStream(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}