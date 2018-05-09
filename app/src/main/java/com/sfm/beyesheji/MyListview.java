package com.sfm.beyesheji;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自定义Listview，可配合ScrollView使用，进行嵌套滑动
 */

public class MyListview extends ListView {
    public MyListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListview(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
