package ItemViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class NotifViewHolder extends RecyclerView.ViewHolder  {

    public TextView nameNotif;
    public TextView objectNotif;
    public Button buttonAction1;
    public Button buttonAction2;
    public View myV;

    public NotifViewHolder(View itemView) {
        super(itemView);
        this.myV = itemView;
        nameNotif = (TextView) itemView.findViewById(R.id.card_notif_name);
        objectNotif = (TextView) itemView.findViewById(R.id.card_notif_object);
        buttonAction1 = (Button) itemView.findViewById(R.id.button_notif_action_1);
        buttonAction2 = (Button) itemView.findViewById(R.id.button_notif_action_2);
    }
}
