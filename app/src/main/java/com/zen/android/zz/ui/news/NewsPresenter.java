package com.zen.android.zz.ui.news;

import com.zen.android.brite.dbflow.DbflowBrite;
import com.zen.android.zz.common.lifecycle.RxLifecyclePresenter;
import com.zen.android.zz.sdk.ZhiHu;
import com.zen.android.zz.sdk.model.Story;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * @author zen yang - 2016/12/8
 */
class NewsPresenter extends RxLifecyclePresenter<NewsView> {

    @Override
    public void onViewAttached(NewsView view) {
        super.onViewAttached(view);

        DbflowBrite.Query.from(Story.class)
                .queryModels()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateNews);
    }

    void fetchNews() {
        ZhiHu.get().service()
                .getNewsLatest()
                .compose(async())
                .subscribe(result -> {
                    DbflowBrite.save(Story.class, result.getStories());
                });
    }

    private void updateNews(List<Story> news) {
        NewsView v = getView();
        if (v != null) {
            v.showNews(news);
        }
    }

}
