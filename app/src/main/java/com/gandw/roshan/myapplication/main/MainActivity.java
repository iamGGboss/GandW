package com.gandw.roshan.myapplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gandw.roshan.myapplication.R;

import gandw.com.network.RetrofitHelper;
import gandw.com.widget.ExpandTextView;
import gandw.com.widget.MobileNumEditText;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_test);
        Button btnString = (Button) findViewById(R.id.btn_test_string);
        Button btnJson = (Button) findViewById(R.id.btn_test_json);
        final ExpandTextView expandTextview = (ExpandTextView) findViewById(R.id.etv_main);
        final MobileNumEditText mobileNumEditText = (MobileNumEditText) findViewById(R.id.mnet);
        btnString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitHelper.getRetrofit();
                ApiMain call = retrofit.create(ApiMain.class);
                Observable<String> login = call.getString();
                login.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("MainActivity", s);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("MainActivity", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        });
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitHelper.getRetrofit();
                ApiMain call = retrofit.create(ApiMain.class);
                Observable<MainResponse> json = call.getJson();
                json.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<MainResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MainResponse mainResponse) {
                                MainResponse.Name name = mainResponse.data;
                                Log.d("MainActivity", name.firstName);
                                Log.d("MainActivity", name.lastName);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("MainActivity", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        });
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, mobileNumEditText.getMobileNum(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public MainContract.Presenter createPresenter() {
        return presenter = new MainPresenter(this);
    }
}
