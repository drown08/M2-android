package Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import FragmentBar.TabBarInfos;
import ItemViewHolder.PhotoBarViewHolder;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<PhotoBarViewHolder>{
    private List<Uri> itemsUri;
    private LayoutInflater layoutInflater;
    private Context context;
    private GalleryAdapter.OnItemClickListener onItemClickListener;
    BarActivity myActivity;

    public GalleryAdapter(TabBarInfos fragment) {
        this.context = (Context) fragment.getActivity();
        layoutInflater = LayoutInflater.from(this.context);
        itemsUri = new ArrayList<Uri>();
        this.myActivity=(BarActivity)this.context;
    }


    @Override
    public PhotoBarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemCardView = (CardView)layoutInflater.inflate(R.layout.layout_cardview,parent,false);
        
        return new PhotoBarViewHolder(itemCardView,this);
    }

    @Override
    public void onBindViewHolder(PhotoBarViewHolder holder, int position) {
        Uri targetUri = itemsUri.get(position);
        holder.setItemUri(targetUri.getPath());

        if(targetUri != null) {
            Context context = holder.imageView.getContext();
            Picasso.with(context)
                    .load(targetUri)
                    .into(holder.imageView);

        }
    }


    @Override
    public int getItemCount() {
        return itemsUri.size();
    }

    public void setOnItemClickListener(GalleryAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public GalleryAdapter.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }


    public  interface OnItemClickListener{
        public void onItemClick(PhotoBarViewHolder item, int position);
    }

    public void add(int location, Uri iUri) {
        itemsUri.add(location,iUri);
        notifyItemInserted(location);
    }

    public void clearAll(){
        int itemCount = itemsUri.size();

        if(itemCount>0){
            itemsUri.clear();
            notifyItemRangeRemoved(0,itemCount);
        }
    }

}
