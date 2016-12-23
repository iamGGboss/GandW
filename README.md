### GandW的readme
------
目前只提供了控件的引用库，关于common和network模块暂未完成，后续将会完善

>1.控件包

    maven库地址:
    maven   {
                     url "https://github.com/iamGGboss/GandW/raw/master/mvn-repo/snapshots"
                    }
    引用库名:    compile 'com.iamGGboss:widget:0.0.1-SNAPSHOT@aar'


```android

 <gandw.com.widget.ExpandTextView
        android:id="@+id/etv_main"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:maxLines="2"
        android:textColor="@color/colorPrimary"
        gandw:beforeStr="%%%"
        gandw:clickColor="@color/colorAccent"
        gandw:expandStr="展开" />

 ExpandTextView expandTextview = (ExpandTextView) findViewById(R.id.etv_main);
 expandTextview.setInitText("这只是一个测试，这只是一个测试，这只是一个测试，这只是一个测试，这只是一个测试，这只是一个测试，");
```