package com.zen.android.zz.ui.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.zen.android.zz.R;
import com.zen.android.zz.sdk.model.Story;

import java.util.List;

import easymvp.annotation.FragmentView;
import easymvp.annotation.Presenter;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * @author zen yang - 2016/12/8
 */
@FragmentView(presenter = NewsPresenter.class)
public class NewsFragment extends Fragment implements NewsView {

    @Presenter
    NewsPresenter mNewsPresenter;

    //    @BindView(R.id.sur_news)
    UltimateRecyclerView mSurNews;

    private NewsAdapter mNewsAdapter;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
//        ButterKnife.bind(this, root);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        mSurNews = (UltimateRecyclerView) root.findViewById(R.id.sur_news);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSurNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mSurNews.setDefaultOnRefreshListener(() -> mNewsPresenter.fetchNews());
        mSurNews.setRefreshing(true);
    }

    @Override
    public void showNews(List<Story> stories) {
        if (mNewsAdapter == null) {
            mNewsAdapter = new NewsAdapter(stories);
            mSurNews.setAdapter(mNewsAdapter);
        } else {
            mNewsAdapter.updateData(stories);
            mNewsAdapter.notifyDataSetChanged();
        }
    }

    private static class NewsAdapter extends easyRegularAdapter<Story, NewsViewHolder> {

        public NewsAdapter(List<Story> list) {
            super(list);
        }

        void updateData(List<Story> data) {
            source = data;
        }

        @Override
        protected int getNormalLayoutResId() {
            return R.layout.list_item_news;
        }

        @Override
        protected NewsViewHolder newViewHolder(View view) {
            return new NewsViewHolder(view);
        }

        @Override
        protected void withBindHolder(NewsViewHolder holder, Story data, int position) {
            holder.onBindView(data);
        }
    }

    static class NewsViewHolder extends UltimateRecyclerviewViewHolder<Story> {

//        @BindView(R.id.tv_title)
        TextView  mTvTitle;
//        @BindView(R.id.tv_detail)
        TextView  mTvDetail;
//        @BindView(R.id.iv_cover)
        ImageView mIvCover;

        public NewsViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            mIvCover = (ImageView) itemView.findViewById(R.id.iv_cover);
        }

        @Override
        protected void updateView(Context context, Story story) {
            super.updateView(context, story);
            mTvTitle.setText(story.getTitle());
            mTvDetail.setText(story.getGaPrefix());
            Glide.with(context).load(story.getImage()).into(mIvCover);
        }
    }
}
