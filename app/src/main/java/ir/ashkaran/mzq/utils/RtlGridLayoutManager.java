package ir.ashkaran.mzq.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * Created by ASHKARAN on 3/30/2018.
 */

public class RtlGridLayoutManager extends GridLayoutManager {

    public RtlGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RtlGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public RtlGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    protected boolean isLayoutRTL(){
        return true;
    }
}