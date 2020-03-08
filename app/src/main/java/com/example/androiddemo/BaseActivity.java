package com.example.androiddemo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by admin
 * Created Time: 2020/3/8 11:26
 * Description:
 */
public abstract class BaseActivity extends SupportActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutID());
        mBind = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int bindLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null && mBind != Unbinder.EMPTY) {
            mBind.unbind();
            mBind = null;
        }
    }
}
