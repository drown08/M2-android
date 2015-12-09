package Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

import java.util.ArrayList;
import java.util.List;

import Evenement.OnClickAtBar;
import Evenement.OnLongClickAtBar;
import Model.Bar;

/**
 * Created by Frappereau Olivier on 11/11/2015.
 */
public class BarAdapter extends RecyclerView.Adapter<BarAdapter.BarViewHolder> {
    private List<Bar> barList;
    private View itemBarView;
    private Activity myActivity;

    public BarAdapter(List<Bar> list, Activity activity){
        this.barList = list;
        this.myActivity = activity;
    }

    @Override
    public BarViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        this.itemBarView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_layout, viewGroup, false);
        BarViewHolder tmpBarVH = new BarViewHolder(this.itemBarView);
        tmpBarVH.myV.setOnClickListener(new OnClickAtBar(tmpBarVH,this.myActivity));
        tmpBarVH.myV.setOnLongClickListener(new OnLongClickAtBar(tmpBarVH,this.myActivity));
        return tmpBarVH;
    }

    @Override
    public void onBindViewHolder(BarViewHolder barViewHolder, int i) {
        Bar b = this.barList.get(i);
        barViewHolder.vNom.setText(b.getNom());
        barViewHolder.vInfos.setText(b.getInfos());
        barViewHolder.vLogo.setImageResource(b.getLogo());

    }

    @Override
    public int getItemCount() {
        return barList.size();
    }

    public void addBar(int position,Bar bar) {
        this.barList.add(position, bar);
        notifyItemInserted(position);
    }

    public Bar removeBar(int position) {
        Bar barDeleted = this.barList.get(position);
        this.barList.remove(position);
        notifyItemRemoved(position);
        return barDeleted;
    }

    public void undoRemove(Bar b, int position) {
        this.barList.add(position, b);
        notifyItemInserted(position);
    }

    public void updateBar(int position,Bar bar) {
        this.barList.set(position, bar);
        notifyItemChanged(position);
    }

    public void UPBarList(ArrayList<Bar> l) {
        this.barList = new ArrayList<Bar>(l);
        //this.barList = l ;
        //Toast.makeText(this.myActivity.getApplicationContext(),"TAILLE-LISTE-BAR AV "+String.valueOf(l.size()),Toast.LENGTH_SHORT;
        notifyItemRangeChanged(0, l.size());
        //Toast.makeText(this.myActivity.getApplicationContext(), "TAILLE-LISTE-BAR AP " + String.valueOf(l.size()), Toast.LENGTH_SHORT);
        //notifyDataSetChanged();
    }

    public static class BarViewHolder extends RecyclerView.ViewHolder  {
        protected ImageView vLogo;
        protected TextView vNom;
        protected TextView vInfos;
        protected  View myV;

        public BarViewHolder(View v) {
            super(v);
            myV = v;
            vLogo = (ImageView) v.findViewById(R.id.icon);
            vNom = (TextView) v.findViewById(R.id.secondLine);
            vInfos = (TextView) v.findViewById(R.id.firstLine);
            v.setClickable(true);
           // v.setOnClickListener(new OnClickAtBar(this));
           // v.setOnLongClickListener(new OnLongClickAtBar(this));

        }

    }
}
