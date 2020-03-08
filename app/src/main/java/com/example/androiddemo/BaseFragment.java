package com.example.androiddemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by admin
 * Created Time: 2020/3/8 10:51
 * Description:
 */
public abstract class BaseFragment extends SupportFragment {

    private View mContentView;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(bindLayoutID(), container, false);
        return mContentView;
    }

    protected abstract int bindLayoutID();

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mBind = ButterKnife.bind(this, mContentView);
        initView(savedInstanceState);
        initData();

    }

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        if (mBind != null && mBind != Unbinder.EMPTY) {
            mBind.unbind();
            mBind = null;
        }
        super.onDestroyView();
    }
}
