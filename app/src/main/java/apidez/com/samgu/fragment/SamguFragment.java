package apidez.com.samgu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;
import java.util.List;

import apidez.com.samgu.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nongdenchet on 6/5/16.
 */
public class SamguFragment extends Fragment {
    private CardAdapter cardAdapter;

    @Bind(R.id.card_stack)
    CardStack cardStack;

    @Bind(R.id.ivStatus)
    ImageView ivStatus;

    @OnClick(R.id.btnDislike)
    public void onDislikeClick() {
        cardStack.discardTop(2);
    }

    @OnClick(R.id.btnLike)
    public void onLikeClick() {
        cardStack.discardTop(3);
    }

    public static SamguFragment newInstance() {
        Bundle args = new Bundle();
        SamguFragment fragment = new SamguFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_samgu, container, false);
        ButterKnife.bind(this, rootView);
        initializeView();
        return rootView;
    }

    private void initializeView() {
        cardStack.setContentResource(R.layout.card_layout);
        cardStack.setStackMargin(20);
        cardAdapter = new CardAdapter(getContext(), 0);
        for (int i = 0; i < 5; i++) {
            cardAdapter.addAll(data());
        }
        cardStack.setAdapter(cardAdapter);
        cardStack.setListener(new CardStack.CardEventListener() {
            @Override
            public boolean swipeEnd(int section, float distance) {
                ivStatus.setVisibility(View.GONE);
                return distance >= 400;
            }

            @Override
            public boolean swipeStart(int section, float distance) {
                return true;
            }

            @Override
            public boolean swipeContinue(int section, float distanceX, float distanceY) {
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceX);
                if (distance >= 400) {
                    ivStatus.setVisibility(View.VISIBLE);
                    if (section == 1 || section == 3) {
                        ivStatus.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_like));
                    } else {
                        ivStatus.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_dislike));
                    }
                } else {
                    ivStatus.setVisibility(View.GONE);
                }
                return true;
            }

            @Override
            public void discarded(int index, int direction) {
            }

            @Override
            public void topCardTapped() {
            }
        });
    }

    private List<String> data() {
        List<String> data = new ArrayList<>();
        data.add("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/13072819_1133555470022181_5019669804777413920_o.jpg");
        data.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/15253571_1255569104513806_1694383344236438323_n.jpg?oh=c674b4a786507584893ad5e165ec5571&oe=58F1A03E");
        data.add("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/12998383_1127949393916122_3155205815095903997_o.jpg");
        data.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/15267945_738099653004497_6034502147966232251_n.jpg?oh=7cd77543cb39fc86798ee772d5880ab6&oe=58BD829E");
        data.add("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/13062973_463324620540694_6650778368538652819_o.jpg");
        data.add("https://scontent.fsgn2-2.fna.fbcdn.net/t31.0-8/14102954_655203127977711_346605723006854355_o.jpg");
        data.add("https://scontent.fsgn2-2.fna.fbcdn.net/v/t1.0-9/12038031_1504317549860999_6722477539523468950_n.jpg?oh=90bc47661df8d88ccc8e685b8ba446a0&oe=58C0A17F");
        data.add("http://scontent-hkg3-1.xx.fbcdn.net/t31.0-8/11000654_469380153209783_6363118670586989285_o.jpg");
        return data;
    }

    public static class CardAdapter extends ArrayAdapter<String> {

        public CardAdapter(Context context, int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, final View contentView, ViewGroup parent) {
            ImageView image = (ImageView) contentView.findViewById(R.id.image);
            Glide.with(getContext())
                    .load(getItem(position))
                    .centerCrop()
                    .into(image);
            return contentView;
        }
    }
}
