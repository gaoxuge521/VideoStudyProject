package com.elt.basecommon.widght;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.elt.basecommon.NoticeBean;
import com.elt.basecommon.imageloader.ImageLoaderUtil;
import com.elt.basecommon.imageloader.ImageLoaders;
import com.elt.zl.basecommon.R;

import java.util.List;


/**
 * 作者：Administrator on 2017/11/20 14:11
 * 邮箱：android_gaoxuge@163.com
 * 轮播公告View
 * 使用时直接修改下Bean
 *
 */
public class NoticeView extends ViewFlipper implements View.OnClickListener{
    private Context mContext;
    private List<NoticeBean> mNotices;
    private OnNoticeClickListener mOnNoticeClickListener;

    public NoticeView(Context context) {
        super(context);
    }

    public NoticeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        // 轮播间隔时间为3s
        setFlipInterval(3000);
        // 内边距5dp
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        // 设置enter和leave动画
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notify_in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.notify_out));
    }

    /**
     * 添加需要轮播展示的公告
     *
     * @param notices
     */
    public void addNotice(List<NoticeBean> notices) {
        removeAllViews();
        mNotices = notices;
        for (int i = 0; i < notices.size(); i++) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.limit_scroller_item, null, false);
            TextView tvnoticecontent = (TextView) view.findViewById(R.id.tv_notice_content);
            TextView tvnoticetitle = (TextView) view.findViewById(R.id.tv_notice_title);
            ImageView ivicon = (ImageView) view.findViewById(R.id.iv_icon);


            if(!TextUtils.isEmpty(notices.get(i).getTitle())){
                tvnoticetitle.setVisibility(VISIBLE);
                tvnoticetitle.setText(notices.get(i).getTitle());
            }else{
                tvnoticetitle.setVisibility(GONE);
            }

            if(!TextUtils.isEmpty(notices.get(i).getContent())){
                tvnoticecontent.setVisibility(VISIBLE);
                tvnoticecontent.setText(notices.get(i).getContent());
            }else{
                tvnoticecontent.setVisibility(GONE);
            }
            if(!TextUtils.isEmpty(notices.get(i).getImg())){
                new ImageLoaderUtil().loadImage(mContext,new ImageLoaders.Builder().imgView(ivicon).url(notices.get(i).getImg()).build());
            }
            view.setTag(i);
            view.setOnClickListener(this);
            // 添加到ViewFlipper
            NoticeView.this.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        //开始轮播
        startFlipping();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        NoticeBean notice = mNotices.get(position);
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener.onNoticeClick(position, notice);
        }
    }

    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setOnNoticeClickListener(OnNoticeClickListener onNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener;
    }

    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                mContext.getResources().getDisplayMetrics());
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {
        void onNoticeClick(int position, NoticeBean notice);
    }
}
