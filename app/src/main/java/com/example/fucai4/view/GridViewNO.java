package com.example.fucai4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 42224 on 2018/4/20.
 */

public class GridViewNO extends GridView {

    public GridViewNO(Context context) {
        super(context);
    }

    public GridViewNO(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewNO(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
