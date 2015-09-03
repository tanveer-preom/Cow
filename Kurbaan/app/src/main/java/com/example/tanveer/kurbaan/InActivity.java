package com.example.tanveer.kurbaan;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class InActivity extends Activity implements View.OnClickListener{

    private FrameLayout frameL;
    private View view;
    private TextView next,previous,page,message,title;
    private int pageNum;
    private ImageView image;

    private AdRequest adRequest;
    private AdView mAdView;
    private ScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        scroll = (ScrollView) findViewById(R.id.ll);
        next= (TextView) findViewById(R.id.next);
        page= (TextView) findViewById(R.id.pageNum);
        previous = (TextView) findViewById(R.id.prev);
        pageNum=getIntent().getExtras().getInt("page",1);
        message = (TextView) findViewById(R.id.txt);
        image = (ImageView)  findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);

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
                    page.setText("Page 4/9");
                    image.setBackgroundResource(R.drawable.nirdeshona);
                    message.setText(R.string.in_page1);
                    title.setText(R.string.in_page_1_title);

                    // frameL.addView(view);

                }
                else if(i==2)
                {
                    page.setText("Page 5/9");
                    image.setBackgroundResource(R.drawable.niomaboli);
                    message.setText(R.string.in_page2);
                    title.setText(R.string.in_page_2_title);

                }
                else if(i==3)
                {
                    page.setText("Page 6/9");
                    image.setBackgroundResource(R.drawable.butching);
                    message.setText(R.string.in_page3);
                    title.setText(R.string.in_page_3_title);

                }
                else
                {
                    page.setText("Page 7/9");
                    image.setBackgroundResource(R.drawable.distribute);
                    message.setText(R.string.in_page4);
                    title.setText(R.string.in_page_4_title);

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
            pageNum--;
            if(pageNum==0)
            {
                Intent i =new Intent(InActivity.this,PreActivity.class);
                i.putExtra("page",3);
                startActivity(i);
                finish();
                return;
            }



            setBackground(pageNum);
        }
        else
        {
            pageNum++;
            if(pageNum==5)
            {
                Intent i =new Intent(InActivity.this,PostActivity.class);
                i.putExtra("page",1);
                startActivity(i);
                finish();
                return;
            }
            setBackground(pageNum);


        }
    }
}
