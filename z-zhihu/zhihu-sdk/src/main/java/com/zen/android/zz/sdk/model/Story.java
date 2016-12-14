package com.zen.android.zz.sdk.model;

import android.text.TextUtils;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.squareup.moshi.Json;
import com.zen.android.zz.sdk.converter.ArrayStringConverter;
import com.zen.android.zz.sdk.db.ZhiHuDatabase;

/**
 * @author zen
 * @version 2016/12/7
 */
@Table(database = ZhiHuDatabase.class)
public class Story extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int      baseId;
    @Column
    @Json(name = "ga_prefix")
    String   gaPrefix;
    @Column
    String   id;
    @Column
    String   title;
    @Column(typeConverter = ArrayStringConverter.class)
    String[] images;
    @Column
    int      type;
    @Column
    boolean  isTop;

    public String getGaPrefix() {
        return gaPrefix;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getImages() {
        return images;
    }

    public String getImage() {
        if (images == null) {
            return null;
        }
        for (String img : images) {
            if (!TextUtils.isEmpty(img)) {
                return img;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public boolean isTop() {
        return isTop;
    }
}
