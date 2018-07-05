package com.chris.onlinepdfview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chris.onlinepdf.FileUtil;
import com.chris.onlinepdf.PDFPagerAdapter;
import com.chris.onlinepdf.RemotePDFViewPager;
import com.chris.onlinepdf.download.DownloadFile;

public class MainActivity extends AppCompatActivity implements DownloadFile.Listener{

    private static final String TAG = "MainActivity";

    private PDFPagerAdapter adapter;

    private RemotePDFViewPager remotePDFViewPager;


    String url = "https://www.maiyabank.com/static/images/disclosure/2016.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        remotePDFViewPager = new RemotePDFViewPager(this, url, this,false);
        remotePDFViewPager.setId(R.id.pdf_viewpager);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        setContentView(remotePDFViewPager);
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
