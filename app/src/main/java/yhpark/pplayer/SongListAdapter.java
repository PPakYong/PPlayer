package yhpark.pplayer;

import android.databinding.DataBindingUtil;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import yhpark.pplayer.databinding.RowSongListBinding;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListHolder> {

    private final List<File> musicList;

    SongListAdapter(List<File> musicList) {
        this.musicList = musicList;
    }

    class SongListHolder extends RecyclerView.ViewHolder {
        RowSongListBinding binding;

        public SongListHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @NonNull
    @Override
    public SongListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListHolder holder, int position) {
        holder.binding.tvName.setText(musicList.get(position).getName());
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(musicList.get(position).getPath());
        Glide.with(holder.itemView).load(mmr.getEmbeddedPicture()).into(holder.binding.ivAlbumArt);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
}
