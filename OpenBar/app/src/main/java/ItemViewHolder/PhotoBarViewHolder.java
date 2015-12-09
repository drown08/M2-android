package ItemViewHolder;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.openbar.frappereauolivier.openbar.R;

import Adapter.GalleryAdapter;

/**
 * Created by Frappereau Olivier on 09/12/2015.
 */
public class PhotoBarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private GalleryAdapter parent;
    private CardView cardView;
    public ImageView imageView;
    String itemUri;

    public PhotoBarViewHolder(CardView cardView, GalleryAdapter parent) {
        super(cardView);
        itemView.setOnClickListener(this);
        this.cardView = cardView;
        this.parent = parent;
        imageView =  (ImageView) cardView.findViewById(R.id.bar_image);
    }

    public void setItemUri(String itemUri){
        this.itemUri = itemUri;
    }

    public String getItemUri() {
        return itemUri;
    }

    public void setImageView(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        final GalleryAdapter.OnItemClickListener listener = parent.getOnItemClickListener();
        if(listener != null) {
            //listener.onItemClick(parent,v,getLayoutPosition(),v.getId());
            // listener.onItemClick(this,getLayoutPosition());
            //or use
            listener.onItemClick(this,getAdapterPosition());
        }
    }
}
