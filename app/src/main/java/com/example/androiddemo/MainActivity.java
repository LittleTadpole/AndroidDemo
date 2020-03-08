package com.example.androiddemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.BusUtils;
import com.example.androiddemo.utils.Cons;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;


    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvTitle.setText(R.string.home);
        loadRootFragment(R.id.fl_container, new MainFragment());
    }

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        BusUtils.register(this);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        BusUtils.unregister(this);
        super.onDestroy();
    }

    @BusUtils.Bus(tag = Cons.TAG_TITLE)
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }


}
