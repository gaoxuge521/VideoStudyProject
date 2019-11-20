package com.elt.basecommon;

import java.io.Serializable;

/**
 * 作者：Administrator on 2018/3/5 13:05
 * 邮箱：android_gaoxuge@163.com
 */
public class NoticeBean implements Serializable {
    private String title;
    private String content;
    private String img;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
