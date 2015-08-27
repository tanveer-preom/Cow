package com.example.tanveer.kurbaan;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends Activity {

    private FrameLayout frameL;
    private View view;
    private TextView next,previous,page,message,title;
    private int pageNum;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);
        frameL = (FrameLayout) findViewById(R.id.frame);
        view = LayoutInflater.from(this).inflate(R.layout.textview_for_frame,null,false);

        next= (TextView) findViewById(R.id.next);
        page= (TextView) findViewById(R.id.pageNum);
        previous = (TextView) findViewById(R.id.prev);
        pageNum=1;
        message = (TextView) view.findViewById(R.id.txt);
        image = (ImageView) view.findViewById(R.id.img);
        page.setText("Page "+pageNum+"/2");
        image.setBackgroundResource(R.drawable.leather);
        message.setText(R.string.end_page1);
        title = (TextView) view.findViewById(R.id.title);
        title.setText(R.string.end_page_1_title);
        frameL.addView(view);


    }
    public void onPrev(View v)
    {
        if(pageNum==1)
            return;
        next.setTextColor(Color.WHITE);
        previous.setTextColor(Color.WHITE);
        pageNum--;
        if(pageNum==1)
            previous.setTextColor(Color.GRAY);
        setBackground(pageNum);


    }
    public void onNext(View v)
    {
        if(pageNum==2)
            return;
        next.setTextColor(Color.WHITE);
        previous.setTextColor(Color.WHITE);
        pageNum++;
        if(pageNum==2)
            next.setTextColor(Color.GRAY);
        setBackground(pageNum);



    }
    public void setBackground(final int i)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(i==1)
                {
                    page.setText("Page 1/2");
                    image.setBackgroundResource(R.drawable.leather);
                    message.setText(R.string.end_page1);
                    title.setText(R.string.end_page_1_title);

                    // frameL.addView(view);

                }
                else if(i==2)
                {
                    page.setText("Page 2/2");
                    image.setBackgroundResource(R.drawable.city_clean);
                    message.setText(R.string.end_page2);
                    title.setText(R.string.end_page_2_title);

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
}
