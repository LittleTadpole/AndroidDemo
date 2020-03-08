package com.example.androiddemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * Created by admin
 * Created Time: 2020/3/8 12:21
 * Description:
 */
public class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MainAdapter() {
        super(R.layout.item_main);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String title) {
        viewHolder.setText(R.id.tv_subtitle, title);
    }
}
