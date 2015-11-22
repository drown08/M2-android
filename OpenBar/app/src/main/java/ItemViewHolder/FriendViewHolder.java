package ItemViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openbar.frappereauolivier.openbar.R;

/**
 * Created by Frappereau Olivier on 22/11/2015.
 */
public class FriendViewHolder  extends RecyclerView.ViewHolder  {

    public View myV;
    public TextView name;
    public TextView surname;
    public ImageView photo;
    public ImageButton buttonAction1;
    public ImageButton buttonAction2;
    public ImageButton buttonAction3;

    public FriendViewHolder(View itemView) {
        super(itemView);
        this.myV = itemView;
        name = (TextView) itemView.findViewById(R.id.card_friend_name);
        surname = (TextView)itemView.findViewById(R.id.card_friend_surname);
        photo = (ImageView)itemView.findViewById(R.id.card_friend_photo);
        buttonAction1 = (ImageButton)itemView.findViewById(R.id.card_friend_buttonAction1);
        buttonAction2 = (ImageButton)itemView.findViewById(R.id.card_friend_buttonAction2);
        buttonAction3 = (ImageButton)itemView.findViewById(R.id.card_friend_buttonAction3);
    }
}
