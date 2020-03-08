package com.example.androiddemo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BusUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.androiddemo.butterknife.ButterKnifeFragment;
import com.example.androiddemo.utils.Cons;
import com.example.androiddemo.utils.SimpleDividerDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin
 * Created Time: 2020/3/8 10:48
 * Description:
 */
public class MainFragment extends BaseFragment implements OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String[] mStrings = {"ButterKnife", "test"};
    List<String> mList = Arrays.asList(mStrings);

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainAdapter adapter = new MainAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new SimpleDividerDecoration(getContext()));
        adapter.setNewData(mList);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        BusUtils.post(Cons.TAG_TITLE, mList.get(position));
        switch (position) {
            case 0:
                start(new ButterKnifeFragment());
                break;
            case 1:

                break;

        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        BusUtils.post(Cons.TAG_TITLE, getString(R.string.home));
    }
}
