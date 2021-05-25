package com.edw.operatingdbclient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * **************************************************************************************************
 * Project Name:    OperatingDBClient
 * <p>
 * Date:            2021-05-24
 * <p>
 * Author：         EdwardWMD
 * <p>
 * Github:          https://github.com/Edwardwmd
 * <p>
 * Blog:            https://edwardwmd.github.io/
 * <p>
 * Description：    ToDo
 * <p>
 * **************************************************************************************************
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    public MyItemDecoration() {
        super();
    }


    @Override
    public void getItemOffsets(@NonNull  Rect outRect, @NonNull  View view, @NonNull  RecyclerView parent, @NonNull  RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);


        if (position!=0){
            outRect.top = dp2Px(parent.getContext(), 10);
        }
    }

    private int dp2Px(Context context,int dp){
        float densityDpi =context.getResources().getDisplayMetrics().density;
        return (int) (dp * densityDpi + 0.5f);
    }
}
