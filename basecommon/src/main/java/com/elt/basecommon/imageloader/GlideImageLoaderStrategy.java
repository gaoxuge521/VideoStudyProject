package com.elt.basecommon.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elt.basecommon.imageloader.util.GlideCircleTransform;
import com.elt.basecommon.imageloader.util.GlideRoundTransform;
import com.elt.zl.basecommon.R;

import java.io.File;


/**
 * Created by Anthony on 2016/3/3.
 * Class Note:
 * using {@link Glide} to load image
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {


    /**
     * 加载普通图片
     * @param ctx
     * @param img
     */
    @Override
    public void loadImage(Context ctx, ImageLoaders img) {

        //if currently not under wifi
        if (!ImageLoaderUtil.wifiFlag) {
            //如果不是在wifi下才加载图片
            if(ctx==null){
                img.getImgView().setImageResource(img.getErrerHolder());
            }else{
                loadNormal(ctx, img);
            }

            return;
        }

        int strategy = img.getWifiStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = ImageLoaderUtil.getNetWorkType(ctx);
            //if wifi ,load pic
            if (netType == ImageLoaderUtil.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img);
            } else {
                //if not wifi ,load cache
                loadNormal(ctx, img);
            }
        } else {
            //如果不是在wifi下才加载图片
            if(ctx==null){
                img.getImgView().setImageResource(img.getErrerHolder());
            }else{
                loadNormal(ctx, img);
            }

        }

    }

    /**
     * 加载圆形图片
     * @param ctx 上下文
     * @param img 图片
     * @param radio 边框厚度
     */
    @Override
    public void loadCircleImage(Context ctx, ImageLoaders img, int radio,int color) {
        //进来直接走这加载图片
        if (!ImageLoaderUtil.wifiFlag) {
            if(ctx==null){
                setImageErrorCircle(ctx,img,radio,color);
            }else{
                loadNormal(ctx, img,radio,color);
            }
            return;
        }

        int strategy = img.getWifiStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = ImageLoaderUtil.getNetWorkType(ctx);
            //if wifi ,load pic
            if (netType == ImageLoaderUtil.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img,radio,color);
            } else {
                //if not wifi ,load cache
                loadNormal(ctx, img,radio,color);
            }
        } else {
            //如果不是在wifi下才加载图片
            if(ctx==null){
                setImageErrorCircle(ctx,img,radio,color);
            }else{
                loadNormal(ctx, img,radio,color);
            }
        }

    }

    /**
     * 加载圆角图片
     * @param ctx
     * @param img
     * @param radius
     */
    @Override
    public void loadRoundImage(Context ctx, ImageLoaders img, int radius) {
        if(radius==0){
            radius = 3;
        }
        //进来直接走这加载图片
        if (!ImageLoaderUtil.wifiFlag) {
            if(ctx==null){
                img.getImgView().setImageResource(img.getErrerHolder());
            }else{
                loadNormal(ctx, img,radius);
            }
            return;
        }

        int strategy = img.getWifiStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = ImageLoaderUtil.getNetWorkType(ctx);
            //if wifi ,load pic
            if (netType == ImageLoaderUtil.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img,radius);
            } else {
                //if not wifi ,load cache
                loadNormal(ctx, img,radius);
            }
        } else {
            //如果不是在wifi下才加载图片
            if(ctx==null){
                img.getImgView().setImageResource(img.getErrerHolder());
            }else{
                loadNormal(ctx, img,radius);
            }
        }
    }

    /**
     * 加载圆角图片
     * color默认白色
     */
    private void loadNormal(Context ctx, ImageLoaders img,int radius) {
        DrawableTypeRequest load = null;
        if (img.getUrl() instanceof String) {
            load = Glide.with(ctx).load((String) ((String) img.getUrl()).trim());
        } else if (img.getUrl() instanceof Integer) {
            load = Glide.with(ctx).load((Integer) img.getUrl());
        } else if (img.getUrl() instanceof File) {
            load = Glide.with(ctx).load((File) img.getUrl());
        } else if (img.getUrl() instanceof Uri) {
            load = Glide.with(ctx).load((Uri) img.getUrl());
        } else {
            setImageErrorRound(ctx,img,radius);
            return;
        }
        load.bitmapTransform(getGlideRoundTransform(ctx,radius)).dontAnimate().priority(Priority.NORMAL).diskCacheStrategy(DiskCacheStrategy.ALL).error(img.getErrerHolder()).placeholder(img.getPlaceHolder()).into(img.getImgView());
    }


    /**
     * 加载带边框的圆形图片
     * color默认白色
     */
    private void loadNormal(Context ctx, ImageLoaders img,int borderWidth,int color) {

        DrawableTypeRequest load = null;
        if (img.getUrl() instanceof String) {
            load = Glide.with(ctx).load((String) ((String) img.getUrl()).trim());
        } else if (img.getUrl() instanceof Integer) {
            load = Glide.with(ctx).load((Integer) img.getUrl());
        } else if (img.getUrl() instanceof File) {
            load = Glide.with(ctx).load((File) img.getUrl());
        } else if (img.getUrl() instanceof Uri) {
            load = Glide.with(ctx).load((Uri) img.getUrl());
        } else {
            setImageErrorCircle(ctx,img,borderWidth,color);
            return;
        }
        load .centerCrop().transform(getCrileTransform(ctx,borderWidth,color)).dontAnimate().crossFade().priority(Priority.NORMAL).diskCacheStrategy(DiskCacheStrategy.ALL).error(img.getErrerHolder()).placeholder(img.getPlaceHolder()).into(img.getImgView());
    }
    /**
     * 设置普通图片的错误显示
     */
    private void setImageError(ImageView _imageView, int errorId) {
        _imageView.setImageResource(errorId);
    }

    /**
     * 设置圆形角图片的错误显示
     */
    private void setImageErrorRound(Context ctx,ImageLoaders img,int radius) {
        Glide.with(ctx).load(img.getErrerHolder()).bitmapTransform(getGlideRoundTransform(ctx,radius)).into(img.getImgView());
    }

    /**
     * 设置圆形图片的错误显示
     */
    private void setImageErrorCircle(Context ctx,ImageLoaders img,int borderWidth,int color) {
        Glide.with(ctx).load(img.getErrerHolder()).bitmapTransform(getCrileTransform(ctx,borderWidth,color)).into(img.getImgView());
    }

    /**
     * load image with Glide
     * 加载普通图片
     */
    private void loadNormal(Context ctx, ImageLoaders img) {
        DrawableTypeRequest load = null;
        if (img.getUrl() instanceof String) {
            load = Glide.with(ctx).load((String) ((String) img.getUrl()).trim());
        } else if (img.getUrl() instanceof Integer) {
            load = Glide.with(ctx).load((Integer) img.getUrl());
        } else if (img.getUrl() instanceof File) {
            load = Glide.with(ctx).load((File) img.getUrl());
        } else if (img.getUrl() instanceof Uri) {
            load = Glide.with(ctx).load((Uri) img.getUrl());
        } else {
            setImageError(img.getImgView(), img.getErrerHolder());
            return;
        }
        load.asBitmap().dontAnimate().centerCrop().priority(Priority.NORMAL).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(img.getPlaceHolder()).error(img.getErrerHolder()).into(img.getImgView());
    }


    /**
     * 根据传入的radio和color返回 GlideCircleTransform
     * @param context 上下文
     * @param borderWidth 边框宽
     * @param color 颜色
     * @return
     */
    private GlideCircleTransform getCrileTransform(Context context, int borderWidth, int color){
        if(borderWidth==0){
            return new GlideCircleTransform(context);
        }else{
            if(color==0){
                color = context.getResources().getColor(R.color.white);
            }
            return new GlideCircleTransform(context,borderWidth,color);
        }
    }

    /**
     * 根据传入的radio和color返回 GlideCircleTransform
     * @param context 上下文
     * @param radius 圆角
     * @return
     */
    private GlideRoundTransform getGlideRoundTransform(Context context, int radius){
        if(radius==0){
            return new GlideRoundTransform(context);
        }else{
            return new GlideRoundTransform(context,radius);
        }
    }


}
