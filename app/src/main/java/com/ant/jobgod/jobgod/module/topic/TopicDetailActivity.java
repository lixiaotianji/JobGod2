package com.ant.jobgod.jobgod.module.topic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
import com.ant.jobgod.jobgod.util.RecyclerArrayAdapter;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TopicDetailPresenter.class)
public class TopicDetailActivity extends BaseActivity<TopicDetailPresenter> {


    @InjectView(R.id.imgTopic)
    SimpleDraweeView imgTopic;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.ryJobBrief)
    RecyclerView ryJobBrief;


    private JobBriefAdapter jobBriefAdapter;

    private Intent intent;

    private Topic topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_activity_topic_detail);
        ButterKnife.inject(this);
        intent = getIntent();
        topic = (Topic) intent.getSerializableExtra("topic");
        Utils.Log("topic:"+topic.getSubTitle());
        init();

    }

    public void init() {
        ryJobBrief.setLayoutManager(new LinearLayoutManager(this));
        jobBriefAdapter = new JobBriefAdapter(this);
        jobBriefAdapter.addHeader(new TopicIntroView());
        jobBriefAdapter = new JobBriefAdapter(this);
        ryJobBrief.setAdapter(jobBriefAdapter);
        imgTopic.setImageURI(Uri.parse(topic.getImg()));
        collapsingToolbar.setTitle(topic.getTitle());
    }

    public void setJobBriefData(JobBrief[] data) {
        jobBriefAdapter.addAll(data);
    }

    class TopicIntroView implements RecyclerArrayAdapter.HeaderView {

        @Override
        public View onCreateView(ViewGroup parent) {
            TextView textView = new TextView(TopicDetailActivity.this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(72)));
            textView.setPadding(Utils.dip2px(16), Utils.dip2px(16), Utils.dip2px(16), Utils.dip2px(16));
            Utils.Log("onCreateView");
            return textView;
        }

        @Override
        public void onBindView(View headerView) {
            ((TextView) headerView).setText(topic.getSubTitle());
            Utils.Log("onBindView");
        }
    }

}
