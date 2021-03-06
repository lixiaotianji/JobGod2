package com.ant.jobgod.jobgod.module.main.recommend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Banner;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
import com.ant.jobgod.jobgod.module.job.RecommendActivity;
import com.ant.jobgod.jobgod.module.job.TopicListActivity;
import com.ant.jobgod.jobgod.util.Utils;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
@RequiresPresenter(RecommendPresenter.class)
public class RecommendFragment extends BeamFragment<RecommendPresenter> {


    @InjectView(R.id.vpgBanner)
    RollPagerView vpgBanner;
    @InjectView(R.id.tvTopicMore)
    TextView tvTopicMore;
    @InjectView(R.id.viewTopic)
    TopicsView viewTopic;
    @InjectView(R.id.tvRecommendMore)
    TextView tvRecommendMore;
    @InjectView(R.id.listRecommend)
    LinearWrapContentRecyclerView listRecommend;
    @InjectView(R.id.viewAd)
    LinearLayout viewAd;


    private BannerAdapter mBannerAdapter;
    private JobBriefAdapter mRecommendAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.main_fragment_recommend, container, false);
        ButterKnife.inject(this, rootView);
        vpgBanner.setAdapter(mBannerAdapter = new BannerAdapter());
        listRecommend.setAdapter(mRecommendAdapter = new JobBriefAdapter(getActivity()));
        viewAd.addView(new AdView(getActivity(), AdSize.SIZE_468x60));
        tvTopicMore.setOnClickListener(v -> startActivity(new Intent(getActivity(), TopicListActivity.class)));
        tvRecommendMore.setOnClickListener(v->startActivity(new Intent(getActivity(), RecommendActivity.class)));
        return rootView;
    }

    public void setAd(Banner[] banner) {
        mBannerAdapter.setData(banner);
    }

    public void setTopic(Topic[] topics) {
        viewTopic.setTopic(topics);
    }

    public void setRecommend(JobBrief[] jobs) {
        mRecommendAdapter.clear();
        mRecommendAdapter.addAll(jobs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    /**
     * Created by alien on 2015/7/7.
     */
    public static class BannerAdapter extends DynamicPagerAdapter {

        SimpleDraweeView img;
        private Banner[] banners;

        @Override
        public View getView(ViewGroup container, int position) {
            img = new SimpleDraweeView(container.getContext());
            img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(180)));
            GenericDraweeHierarchyBuilder builder =
                    new GenericDraweeHierarchyBuilder(container.getResources());
            builder.setRoundingParams(RoundingParams.fromCornersRadius(Utils.dip2px(2)));
            img.setHierarchy(builder.build());
            img.setImageURI(Uri.parse(banners[position].getImg()));
            return img;
        }

        @Override
        public int getCount() {
            return banners == null ? 0 : banners.length;
        }

        public void setData(Banner[] banners) {
            this.banners = banners;
            notifyDataSetChanged();
        }
    }


}
