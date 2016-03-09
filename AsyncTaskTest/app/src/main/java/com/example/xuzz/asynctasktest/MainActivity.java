package com.example.xuzz.asynctasktest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private ProgressDialog progress;

    private static final String imageUrl = "http://pic50.nipic.com/file/20141009/19386681_093235725000_2.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.iv_image);

        progress=new ProgressDialog(this);
        progress.setTitle("下载进度");
        progress.setMessage("正在下载");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        ((Button)findViewById(R.id.btnDownload)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnDownload)
        {
            (new DownloadTask()).execute(imageUrl);
        }

    }

    class DownloadTask extends AsyncTask<String,Integer,Bitmap>
    {
        @Override
        public Bitmap doInBackground(String... params)
        {
            Bitmap bitmap=null;
            HttpURLConnection connection;
            InputStream inputStream;
            ByteArrayOutputStream bos;
            int len ;       //单次写入字节内存的大小
            float count=0,total;    //count为依据下载的大小,total为总大小
            try
            {
                connection = (HttpURLConnection) (new URL(params[0]).openConnection());
                total =  connection.getContentLength();
                inputStream=connection.getInputStream();
                bos=new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                while((len = inputStream.read(data))!=-1)
                {
                    count+=len;
                    bos.write(data,0,len);
                    publishProgress((int) (count / total * 100));
                    Thread.sleep(500);
                }

                bitmap = BitmapFactory.decodeByteArray(bos.toByteArray(),0,bos.toByteArray().length);
                inputStream.close();
                bos.close();
            }
            catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected  void onPostExecute(Bitmap img)
        {
            //下载完成后
            super.onPostExecute(img);

            progress.hide();
            image.setImageBitmap(img);
            image.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPreExecute()
        {
            //开始下载前
            super.onPreExecute();
            if(image!=null)
            {
                image.setVisibility(View.GONE);
            }
            progress.show();
            progress.setProgress(0);
        }
        @Override
        public void onCancelled()
        {
            super.onCancelled();
            progress.hide();
        }
        @Override
        public void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
        }
    }
}
