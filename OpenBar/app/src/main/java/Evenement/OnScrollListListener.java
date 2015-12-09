package Evenement;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Frappereau Olivier on 26/11/2015.
 */
public class OnScrollListListener extends RecyclerView.OnScrollListener {
    private SwipeRefreshLayout swipe;
    private RecyclerView target;

    public OnScrollListListener(SwipeRefreshLayout swipeRefreshLayout,RecyclerView recyclerView){
        this.swipe = swipeRefreshLayout;
        this.target = recyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //super.onScrolled(recyclerView, dx, dy);
        boolean enable = false;
        if (target != null && target.getChildCount() > 0) {
            // check if the first item of the list is visible
            //boolean firstItemVisible = true;
            //boolean firstItemVisible = recListBar.() == 0;
            // check if the top of the first item is visible
            boolean topOfFirstItemVisible = target.getChildAt(0).getTop() == 0;
            // enabling or disabling the refresh layout
            //enable = firstItemVisible && topOfFirstItemVisible;
            enable = topOfFirstItemVisible;
        }
        Log.d("MethodCalled", String.valueOf(enable));
        swipe.setEnabled(enable);

    }

}
