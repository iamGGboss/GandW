package com.example.touchdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Author      : GandW
 * Time        : 2017/2/16 14:19
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class TouchActivity extends AppCompatActivity {

    LinearLayout parent;
    RelativeLayout top;
    RecyclerView down;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        parent = (LinearLayout) findViewById(R.id.parent);
        top = (RelativeLayout) findViewById(R.id.rl_top);
        down = (RecyclerView) findViewById(R.id.rv_down);
        down.setLayoutManager(new LinearLayoutManager(this));
        TouchDemoAdapter adapter = new TouchDemoAdapter();
        down.setAdapter(adapter);
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TouchActivity", "执行了父容器的触摸事件");
                return false;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TouchActivity", "执行了列表的触摸事件");
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TouchActivity", "执行了activity的触摸事件");
        return super.onTouchEvent(event);
    }

    class TouchDemoAdapter extends RecyclerView.Adapter<TouchDemoHolder> {
        @Override
        public TouchDemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getApplicationContext());
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
            textView.setLayoutParams(params);
            textView.setText("ggggggggggggggggggggggg");
            return new TouchDemoHolder(textView);
        }

        @Override
        public void onBindViewHolder(TouchDemoHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    class TouchDemoHolder extends RecyclerView.ViewHolder {
        public TouchDemoHolder(View itemView) {
            super(itemView);
        }
    }
}
