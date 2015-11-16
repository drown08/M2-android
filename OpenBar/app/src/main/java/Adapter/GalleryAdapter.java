package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.Activity.BarActivity;
import com.openbar.frappereauolivier.openbar.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import FragmentBar.TabBarInfos;

/**
 * Created by Frappereau Olivier on 15/11/2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ItemHolder> {
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
    public GalleryAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemCardView = (CardView)layoutInflater.inflate(R.layout.layout_cardview,parent,false);
        
        return new ItemHolder(itemCardView,this);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Uri targetUri = itemsUri.get(position);
        holder.setItemUri(targetUri.getPath());

        if(targetUri != null) {
            try {
                //!Attention
                //Pas sur que c'est un load propre ici
                holder.setImageView(loadScaledBitmap(targetUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * Reference :
    * Load scaled bitmap
    * http://android-er.blogspot.com....
    * */

    private Bitmap loadScaledBitmap(Uri src) throws FileNotFoundException {
        //Affiche le fichier to be loadScaledBitmap();
        //Je suppose que je sais comment ca marche
        Toast.makeText(this.context,src.getLastPathSegment(),Toast.LENGTH_SHORT).show();

        //Tailles requises
        final int REQ_WIDTH = 150;
        final int REQ_HEIGHT= 150;

        Bitmap bm = null;

        //D'abord on décode avec inJUstDecodeBounts=true to check dim
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(src),null,options);


        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, REQ_WIDTH, REQ_HEIGHT);

        //DEcode bitmap with inSampleSIze set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeStream(
                context.getContentResolver().openInputStream(src),null,options
        );

        return bm;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //Raw height ad wifth of image
        final int height = options.outHeight;
        final  int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth) {
            //Calcul ratios of height and width to requested height and with
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final  int widthRatio = Math.round((float) width / (float) reqWidth);

            //Choose le plus petit ratio as inSampleSize valeur, this garantira ça
            //A final image avec both dimensions largen than or eq to the request h and w.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
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
        public void onItemClick(ItemHolder item, int position);
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

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private GalleryAdapter parent;
        private CardView cardView;
        ImageView imageView;
        String itemUri;

        public ItemHolder(CardView cardView, GalleryAdapter parent) {
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
}
