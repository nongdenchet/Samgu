package apidez.com.samgu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apidez.com.samgu.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nongdenchet on 6/5/16.
 */

public class SongListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SongItemViewHolder) holder).bind(songs().get(position));
    }

    @Override
    public int getItemCount() {
        return songs().size();
    }

    public class SongItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvName)
        TextView tvName;

        @Bind(R.id.tvArtist)
        TextView tvArtist;

        @Bind(R.id.tvDuration)
        TextView tvDuration;

        public SongItemViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }

        public void bind(Song song) {
            tvName.setText(song.name);
            tvArtist.setText(song.artist);
            tvDuration.setText(song.duration);
            itemView.setOnClickListener(v -> {
            });
        }
    }

    public static class Song {
        public String name, artist, duration;

        public Song(String name, String artist, String duration) {
            this.name = name;
            this.artist = artist;
            this.duration = duration;
        }
    }

    public List<Song> songs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("This Love", "Maroon 5", "5:20"));
        songs.add(new Song("Animals", "Maroon 5", "4:21"));
        songs.add(new Song("Payphone", "Maroon 5", "3:10"));
        songs.add(new Song("Sugar", "Maroon 5", "4:28"));
        songs.add(new Song("One More Night", "Maroon 5", "5:00"));
        songs.add(new Song("She Will Be Loved", "Maroon 5", "4:05"));
        songs.add(new Song("Misery", "Maroon 5", "3:21"));
        songs.add(new Song("Love Somebody", "Maroon 5", "3:00"));
        songs.add(new Song("Harder To Breath", "Maroon 5", "4:15"));
        songs.add(new Song("Wake Up Call", "Maroon 5", "5:21"));
        songs.add(new Song("Secret", "Maroon 5", "3:20"));
        songs.add(new Song("Infatuation", "Maroon 5", "3:45"));
        return songs;
    }
}
