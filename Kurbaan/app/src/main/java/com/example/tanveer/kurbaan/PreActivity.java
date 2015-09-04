package com.example.tanveer.kurbaan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class PreActivity extends Activity implements OnClickListener{

    private FrameLayout frameL;
    private View view;
    private TextView next,previous,page,message,title;
    private int pageNum;
    private ImageView image;

    private InterstitialAd mInterstitialAd;
    private AdRequest adRequest;
    private AdView mAdView;
    private ScrollView scroll;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7427451240190586/8132409352");
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("simul", "get add");
                flag = true;

            }

            @Override
            public void onAdClosed() {
                Destroy();
            }
        });

        next= (TextView) findViewById(R.id.next);
        page= (TextView) findViewById(R.id.pageNum);
        previous = (TextView) findViewById(R.id.prev);
        pageNum=getIntent().getExtras().getInt("page",1);
        message = (TextView) findViewById(R.id.txt);
        image = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        scroll = (ScrollView) findViewById(R.id.ll);
        setBackground(pageNum);
        mAdView = (AdView) findViewById(R.id.add);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d("AdExample", "onAdClose() called");
                mAdView.destroy();
                mAdView.setVisibility(View.GONE);
            }

        });


    }

    public void setBackground(final int i)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(i==1)
                {
                    previous.setTextColor(Color.GRAY);
                    page.setText("Page 1/9");
                    image.setBackgroundResource(R.drawable.syagotom);
                    message.setText(R.string.pre_page1);
                    title.setText("");

                   // frameL.addView(view);

                }
                else if(i==2)
                {
                    previous.setTextColor(Color.WHITE);
                    page.setText("Page 2/9");
                    image.setBackgroundResource(R.drawable.pre_page2);
                    message.setText(R.string.pre_page2);
                    title.setText(R.string.pre_page2_title);

                }
                else if(i==3)
                {
                    page.setText("Page 3/9");
                    image.setBackgroundResource(R.drawable.pre_page3);
                    message.setText(R.string.pre_page3);
                    title.setText(R.string.pre_page3_title);

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pre, menu);
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
    public void onClick(View view) {
        scroll.fullScroll(ScrollView.FOCUS_UP);
        if(view.getId()==R.id.prev)
        {
            Log.i("tanvy","prev");
            if(pageNum==1) {

                return;
            }

            pageNum--;
            setBackground(pageNum);

        }
        else
        {
            pageNum++;
            if(pageNum==4)
            {
                Intent i =new Intent(PreActivity.this,InActivity.class);
                i.putExtra("page",1);
                startActivity(i);
                finish();
                return;

            }
            //previous.setTextColor(Color.WHITE);
            setBackground(pageNum);

        }
    }

    private void Destroy() {
        this.finish();
    }

    private void requestNewInterstitial() {
        Log.d("simul", "request add");
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if(flag == true) {
            mInterstitialAd.show();
            requestNewInterstitial();
        } else
            Destroy();
    }
}
