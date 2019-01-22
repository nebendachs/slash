package de.sharknoon.slash.MemeGenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.provider.MediaStore;

public class MemeGeneration {

    public static Bitmap createMeme(Bitmap memeBitmap, String upperMessage, String bottomMessage, Context ctx){

        // Get Meme Template as bitmap
        Bitmap dest = Bitmap.createBitmap(memeBitmap.getWidth(), memeBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        // Create canvas to draw text to image
        Canvas cs = new Canvas(dest);

        Paint tPaint = new Paint();
        tPaint.setTextSize(125);
        tPaint.setColor(Color.WHITE);

        Typeface memeFont = Typeface.createFromAsset(ctx.getAssets(), "fonts/impact.ttf");
        tPaint.setTypeface(memeFont);
        tPaint.setStyle(Paint.Style.FILL);

        cs.drawBitmap(memeBitmap, 0f, 0f, null);
        float height = tPaint.measureText("yY");

        // Get width of message and calculate the x coordinate
        float widthUpperMessage = tPaint.measureText(upperMessage);
        float imageHeight = memeBitmap.getHeight();
        float heightUpperMessage = height + (imageHeight * 0.125f);
        float xCoordinateUpperMessage = (memeBitmap.getWidth() - widthUpperMessage)/2;
        cs.drawText(upperMessage, xCoordinateUpperMessage, heightUpperMessage, tPaint); // 15f is to put space between top edge and the text, if you want to change it, you can

        float widthBottomMessage = tPaint.measureText(bottomMessage);
        float heightBottomMessage = height + (imageHeight * 0.75f);
        float xCoordinateBottomMessage = (memeBitmap.getWidth() - widthBottomMessage)/2;
        cs.drawText(bottomMessage, xCoordinateBottomMessage, heightBottomMessage, tPaint); // 15f is to put space between top edge and the text, if you want to change it, you can

        MediaStore.Images.Media.insertImage(ctx.getContentResolver(), dest ,"test" , "test");
        return dest;
    }
}
