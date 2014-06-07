package com.sample.album.imgutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;
import com.sample.album.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DeveloprSteel on 6/7/2014.
 */
public class ImageDownloader {

    ExecutorService execService;
    Handler handler;

    public ImageDownloader(){
        execService = Executors.newFixedThreadPool(2);
        handler = new Handler();
    }

    /**
     * Function to download image and show it inside image view
     * */
    public void ShowImage(String url, ImageView imgView){
        // download image
        execService.submit(new ImageURLDownloader(url, imgView));
        // show placeholder while loading image
        imgView.setImageResource(R.drawable.placeholder);
    }

    class ImageURLDownloader implements Runnable {
        String url;
        Bitmap bmp;
        ImageView imgView;

        public ImageURLDownloader(String url, ImageView imgView) {
            this.url = url;
            this.imgView = imgView;
            this.bmp = null;
        }

        @Override
        public void run() {
            try {
                URL imageUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setConnectTimeout(30000);
                connection.setReadTimeout(30000);
                connection.setInstanceFollowRedirects(true);
                InputStream is = connection.getInputStream();
                try {
                    bmp = BitmapFactory.decodeStream(is);
                } finally {
                    is.close();
                }

                if(bmp != null){
                    handler.post(new Runnable(){

                        @Override
                        public void run() {
                            imgView.setImageBitmap(bmp);
                        }
                    });
                } else {
                    imgView.setImageResource(R.drawable.placeholder);
                }

            } catch (Throwable ex){
                ex.printStackTrace();
            }
        }
    }
}