package com.example.videoplayer;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import static java.lang.Math.abs;

public class Profile extends MainActivity {

    /**
     * Called when the activity is first created.
     */
    TextView Name,Un,Cn,Subs;

    public void onCreate(Bundle savedInstanceState) {
        InFIrst=false;
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.profile);
        final VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        int c=MainActivity.count;
        Name=(TextView)findViewById(R.id.name);
        Un=(TextView)findViewById(R.id.un);
        Cn=(TextView)findViewById(R.id.cn);
        Subs=(TextView)findViewById(R.id.subs);
        if(count==0){
            Name.setText("Video: SPD Opening Theme");
            Un.setText("Uploader Name: Power Rangers Producer");
            Cn.setText("Channel: Theme Songs");
            Subs.setText("Subscribers: 10,256");
        }
        if(count==1){
            Name.setText("Video: DBZ Opening Theme");
            Un.setText("Uploader Name: Dragon Ball Corp.");
            Cn.setText("Channel: Theme Songs");
            Subs.setText("Subscribers: 7,451");
        }
        if(count==2){
            Name.setText("Video: Doraemon Opening Theme");
            Un.setText("Uploader Name: Disnep Ltd.");
            Cn.setText("Channel: Theme Songs");
            Subs.setText("Subscribers: 22,663");
        }
        if(count==3){
            Name.setText("Video: Kick Buttowski Opening Theme");
            Un.setText("Uploader Name: Disnep Ltd.");
            Cn.setText("Channel: Theme Songs");
            Subs.setText("Subscribers: 22,663");
        }
        if(count==4){
            Name.setText("Video: Ben 10 Opening Theme");
            Un.setText("Uploader Name: Man of Action");
            Cn.setText("Channel: Theme Songs");
            Subs.setText("Subscribers: 12,663");
        }
        Button next = (Button) findViewById(R.id.Button);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }

    float x1, x2, y1, y2;

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x2 < x1 && abs(x1 - x2) > abs(y1 - y2) && abs(x1-x2)>100) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
        }
        return false;
    }

    @Override
    protected void onUserLeaveHint() {
        firstInterface=false;
        super.onUserLeaveHint();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}