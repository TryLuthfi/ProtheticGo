package xtrch.com.prostheticgo2.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xtrch.com.prostheticgo2.Model.ModelInfoUpper;
import xtrch.com.prostheticgo2.R;

public class AdapterInfoUpper extends RecyclerView.Adapter<AdapterInfoUpper.InfoUpperViewHolder> {

    Context ctx;
    List<ModelInfoUpper> mItems;

    public AdapterInfoUpper(Context ctx, List<ModelInfoUpper> mItems) {
        this.ctx = ctx;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public InfoUpperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(ctx).inflate(R.layout.row_info, parent, false);
        InfoUpperViewHolder viewHolder = new InfoUpperViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoUpperViewHolder holder, int position) {
        ModelInfoUpper model = mItems.get(position);
        holder.tvJudul.setText(model.getJudulInfo());
        holder.tvIsi.setText(model.getIsiInfo());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class InfoUpperViewHolder extends RecyclerView.ViewHolder{

        TextView tvJudul, tvIsi;
        ImageView fotoInfo;
        ModelInfoUpper model;

        public InfoUpperViewHolder(View view){
            super(view);
            model = new ModelInfoUpper();
            tvJudul = view.findViewById(R.id.row_info_judul);
            tvIsi = view.findViewById(R.id.row_info_isi);
            fotoInfo = view.findViewById(R.id.row_info_img);
        }
    }
}
