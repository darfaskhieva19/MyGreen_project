package com.example.mygreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class DecodeImage{
    Context mContext;

    public DecodeImage(Context mContext) {
        this.mContext = mContext;
    }

    public Bitmap getUserImage(String encodedImg)
    {
        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else {
            return BitmapFactory.decodeResource(DecodeImage.this.mContext.getResources(), R.drawable.naturalfood);
        }
    }
}