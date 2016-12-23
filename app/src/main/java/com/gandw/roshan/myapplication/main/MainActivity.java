package com.gandw.roshan.myapplication.main;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gandw.roshan.myapplication.R;
import com.gandw.roshan.myapplication.data.api.name.NameResolver;
import com.gandw.roshan.myapplication.data.api.name.NameService;

import java.util.concurrent.TimeUnit;

import gandw.com.network.RetrofitHelper;
import gandw.com.widget.ExpandTextView;
import gandw.com.widget.MobileNumEditText;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == presenter) {
            Log.d("MainActivity", "空的");
        }
        dialog = new ProgressDialog(this);
        Button btn = (Button) findViewById(R.id.btn_test);
        Button btnString = (Button) findViewById(R.id.btn_test_string);
        Button btnJson = (Button) findViewById(R.id.btn_test_json);
        final ExpandTextView expandTextview = (ExpandTextView) findViewById(R.id.etv_main);
        expandTextview.setInitText("这只是一个测试，这只是一个测试，这只是一个测试，这只是一个测试，这只是一个测试，这只是一个测试，");
        final MobileNumEditText mobileNumEditText = (MobileNumEditText) findViewById(R.id.mnet);
        btnString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("正在请求会员");
                dialog.show();
                Observable<String> string = NameResolver.getInstance().getString();
                if (null != string) {
                    string.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(String s) {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                } else {
                    dialog.dismiss();
                }
            }
        });
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
