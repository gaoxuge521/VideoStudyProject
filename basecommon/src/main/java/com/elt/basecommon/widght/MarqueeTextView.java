package com.elt.basecommon.widght;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.widget.TextView;

/**
 * 作者：Administrator on 2018/3/5 17:40
 * 邮箱：android_gaoxuge@163.com
 * 跑马灯效果
 */
@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 当前并没有焦点，我只是欺骗了Android系统
     */
    @Override
    @ViewDebug.ExportedProperty(category = "focus")
    public boolean isFocused() {
        return true;
    }
}
