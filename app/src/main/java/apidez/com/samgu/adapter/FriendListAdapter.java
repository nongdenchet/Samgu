package apidez.com.samgu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import apidez.com.samgu.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nongdenchet on 6/5/16.
 */
public class FriendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        return new FriendItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FriendItemViewHolder) holder).bind(friends().get(position));
    }

    @Override
    public int getItemCount() {
        return friends().size();
    }

    public static class Friend {
        public String url, name, message;

        public Friend(String url, String name, String message) {
            this.url = url;
            this.name = name;
            this.message = message;
        }
    }

    public class FriendItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cvAvatar)
        CircleImageView cvAvatar;

        @Bind(R.id.tvName)
        TextView tvName;

        @Bind(R.id.tvMessage)
        TextView tvMessage;

        public FriendItemViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }

        public void bind(Friend friend) {
            tvName.setText(friend.name);
            tvMessage.setText(friend.message);
            Glide.with(itemView.getContext())
                    .load(friend.url)
                    .centerCrop()
                    .into(cvAvatar);
            itemView.setOnClickListener(v -> {
            });
        }
    }

    public List<FriendListAdapter.Friend> friends() {
        List<FriendListAdapter.Friend> friends = new ArrayList<>();
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/13072819_1133555470022181_5019669804777413920_o.jpg", "Le Na", "Thấy ghét hà <3"));
        friends.add(new Friend("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/15253571_1255569104513806_1694383344236438323_n.jpg?oh=c674b4a786507584893ad5e165ec5571&oe=58F1A03E", "Miu Le", "Anh ơi"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/12998383_1127949393916122_3155205815095903997_o.jpg", "Linh Nguyen", "<3 <3 <3"));
        friends.add(new Friend("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/12038031_1504317549860999_6722477539523468950_n.jpg?oh=90bc47661df8d88ccc8e685b8ba446a0&oe=58C0A17F", "Sếp Tùng", "Sky eiiiii!!!"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/13062973_463324620540694_6650778368538652819_o.jpg", "Sammy", "Ck có nhớ vk ko???"));
        friends.add(new Friend("https://scontent.fsgn2-2.fna.fbcdn.net/t31.0-8/15325195_348739635493100_2324514124436964483_o.jpg", "Trinh", "Giận rùi"));
        friends.add(new Friend("https://scontent.fsgn2-2.fna.fbcdn.net/t31.0-8/14102954_655203127977711_346605723006854355_o.jpg", "Trang", "Thích nhạc Maroon 5 nhứt"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/11000654_469380153209783_6363118670586989285_o.jpg", "Hà Lê", "Nhạc Jazz khó nghe lắm"));
        return friends;
    }
}
