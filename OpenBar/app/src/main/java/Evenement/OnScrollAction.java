package Evenement;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Frappereau Olivier on 14/11/2015.
 */
public class OnScrollAction extends RecyclerView.OnScrollListener {
    Activity myActivity;
    SwipeRefreshLayout mySwipeLayout;
    RecyclerView myRecycle;

    public OnScrollAction(Activity activity, SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView) {
        this.myActivity = activity;
        this.mySwipeLayout = swipeRefreshLayout;
        this.myRecycle = recyclerView;
    }

    //@Override
    public void onScrolled(int dx, int dy) {
        boolean enable = false;
        if(this.myRecycle != null && this.myRecycle.getChildCount() > 0){
            // check if the first item of the list is visible
            //boolean firstItemVisible = true;
            //boolean firstItemVisible = this.myRecycle.getFirstVisiblePosition() == 0;
            // check if the top of the first item is visible
            boolean topOfFirstItemVisible = this.myRecycle.getChildAt(0).getTop() == 0;
            // enabling or disabling the refresh layout
            //enable = firstItemVisible && topOfFirstItemVisible;
            enable = topOfFirstItemVisible;
        }
        Log.d("MethodCalled",String.valueOf(enable));
        mySwipeLayout.setEnabled(enable);
    }
}
