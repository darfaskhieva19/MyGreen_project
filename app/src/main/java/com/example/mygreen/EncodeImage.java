package com.example.mygreen;

import android.graphics.Bitmap;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class EncodeImage {
    private String encodeImage(Bitmap bitmap) {
        int prevW = 150;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();
        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(bytes);
        }
        else{
            return "";
        }
    }

    public String Image(Bitmap bitmap){
        if(bitmap==null){
            return null;
        }
        else{
            String img = encodeImage(bitmap);
            return img;
        }
    }
}