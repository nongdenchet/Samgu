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
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/13321822_1158555317522196_7640562441507537379_n.jpg?oh=7efa83418ccb5cb2526fc5e46cd2da19&oe=5809784C", "Miu Le", "Anh ơi"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/12998383_1127949393916122_3155205815095903997_o.jpg", "Linh Nguyen", "<3 <3 <3"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/10923448_932912170086513_3531739529355490344_n.jpg?oh=a2d4db88318c607ceecb92db72aa46b5&oe=57D085C0", "My My", "Đang đi choy ak?"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/13062973_463324620540694_6650778368538652819_o.jpg", "Sammy", "Ck có nhớ vk ko???"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/11058793_479649545516177_8606396649784375837_n.jpg?oh=07112b7b9949f54d2ae887140721211c&oe=57DCBFF0", "Linh Đan", "Giận rùi"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/11037472_477641842383614_6878994719197104225_n.jpg?oh=84ff61062240e842c66a15880414d9ab&oe=580900E3", "Annie", "Thích nhạc Maroon 5 nhứt"));
        friends.add(new Friend("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/11000654_469380153209783_6363118670586989285_o.jpg", "Hà Lê", "Nhạc Jazz khó nghe lắm"));
        return friends;
    }
}
