package com.chris.onlinepdfview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chris.onlinepdf.FileUtil;
import com.chris.onlinepdf.PDFPagerAdapter;
import com.chris.onlinepdf.OnlinePDFViewPager;
import com.chris.onlinepdf.download.DownloadFile;

public class MainActivity extends AppCompatActivity implements DownloadFile.Listener{

    private static final String TAG = "MainActivity";

    private PDFPagerAdapter adapter;

    private OnlinePDFViewPager onlinePDFViewPager;


    String url = "https://static.miduo.com/group1/M00/01/38/CgECC1loNzWABdLYAAK4Lz7W2pc376.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onlinePDFViewPager = new OnlinePDFViewPager(this, url, this,false);
        onlinePDFViewPager.setId(R.id.pdf_viewpager);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        onlinePDFViewPager.setAdapter(adapter);
        setContentView(onlinePDFViewPager);
    }

    @Override
    public void onFailure(Exception e) {
        Log.i(TAG,"onFailure");
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        Log.i(TAG,"onProgressUpdate: "+progress+" /"+total);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.close();
    }
}
