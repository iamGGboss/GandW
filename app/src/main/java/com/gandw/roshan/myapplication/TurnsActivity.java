package com.gandw.roshan.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TurnsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turns);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        TurnsAdapter adapter = new TurnsAdapter();
        adapter.setList(strings);
        rv.setAdapter(adapter);
    }

    class TurnsAdapter extends RecyclerView.Adapter<TurnsHolder> {

        ArrayList<String> list;

        public void setList(ArrayList<String> list) {
            this.list = list;
        }

        @Override

        public TurnsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getApplicationContext());
            return new TurnsHolder(textView);
        }

        @Override
        public void onBindViewHolder(TurnsHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            int size = list.size();
            int index = position % size;
            textView.setText(list.get(index));
            textView.setTextColor(Color.BLACK);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    }

    class TurnsHolder extends RecyclerView.ViewHolder {

        public TurnsHolder(View itemView) {
            super(itemView);
        }
    }
}
