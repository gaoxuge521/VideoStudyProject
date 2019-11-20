package com.elt.basecommon.widght;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.elt.zl.basecommon.R;


/********************************************************************
 * [Summary]
 *       TODO 请在此处简要描述此类所实现的功能。因为这项注释主要是为了在IDE环境中生成tip帮助，务必简明扼要
 * [Remarks]
 *       TODO 请在此处详细描述类的功能、调用方法、注意事项、以及与其它类的关系.
 *******************************************************************/
@SuppressWarnings("unused")
public class CustomProgressDialog extends Dialog {
    private Context context = null;
    private AnimationDrawable animationDrawable;

    public CustomProgressDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
        this.context = context;
        setContentView(R.layout.customprogressdialog);
        getWindow().getAttributes().gravity = Gravity.CENTER;
    }




    public void onWindowFocusChanged(boolean hasFocus) {

        try {
            ImageView imageView = (ImageView)findViewById(R.id.loadingImageView);
            if(imageView!=null){
                animationDrawable = (AnimationDrawable) imageView.getBackground();
                if(animationDrawable!=null){
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(animationDrawable!=null){
                                animationDrawable.start();
                            }
                        }
                    },100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if(animationDrawable!=null){
            animationDrawable.stop();
            animationDrawable=null;
        }
    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public void setMessage(String strMessage) {
        TextView tvMsg = (TextView) findViewById(R.id.id_tv_loadingmsg);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

    }
}

