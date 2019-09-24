package com.example.videoplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
public static int count=0,vi=0,sps;
float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.spd);
        //videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        View view = findViewById(R.id.rv);
        Snackbar snackbar = Snackbar.make(view,"Channel Subscribed", Snackbar.LENGTH_LONG);
            if(!videoView.isPlaying() && videoView.getCurrentPosition()==0)
                videoView.start();
              switch (touchEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = touchEvent.getX();
                    y1 = touchEvent.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    x2 = touchEvent.getX();
                    y2 = touchEvent.getY();
                    if (y1 < y2 && abs(x1 - x2) < abs(y1 - y2) && abs(y1-y2)>200) {
                        playNext(-1);
                    } else if ((y1 > y2) && (abs(x1 - x2) < abs(y1 - y2)&& abs(y1-y2)>200)) {
                        playNext(1);
                    } else if (x1>x2 && abs(x1-x2)>200){
                        snackbar.show();
                    } else if (x2>x1 && abs(x1-x2)>200){
                        profile();
                    }

                    break;
            }
        return false;
    }

 @SuppressLint("SetTextI18n")
 void profile(){
     VideoView videoView =(VideoView)findViewById(R.id.vdVw);
     sps = videoView.getCurrentPosition();
     videoView.stopPlayback();
        View view=findViewById(R.id.rv);
     Intent myIntent = new Intent(view.getContext(),Profile.class);
     startActivityForResult(myIntent, 0);
     overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
 }

    void playNext(int i) {
        VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        Uri uri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.spd);
        count=count+i;
        if(count==0)
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.spd);
        if(count==1)
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dbz);
        if(count==2)
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dts);
        if(count==3)
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kbt);
        if(count==4)
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cbt);
        if(count==5){
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.spd);
            count=0;
        }
        if(count==-1){
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cbt);
            count=4;
        }
        videoView.setVideoURI(uri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        videoView.seekTo(sps);
        if(vi==0)
        videoView.start();
        sps=0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        if (id == R.id.ppbtn) {
                if(videoView.isPlaying()){
                    videoView.pause();
                    vi=1;
                }else{
                    vi=0;
                    videoView.start();
                }
        }
        return super.onOptionsItemSelected(item);
    }

}