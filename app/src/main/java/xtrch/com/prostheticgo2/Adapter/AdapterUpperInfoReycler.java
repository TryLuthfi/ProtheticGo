package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.RecyclerView;
import xtrch.com.prostheticgo2.Model.ModelInfoUpper;
import xtrch.com.prostheticgo2.R;

public class AdapterUpperInfoReycler extends RecyclerView.Adapter<AdapterUpperInfoReycler.ProductViewHolder>{
    private static final String TAG = "AdapterUpperInfoReycler";

    private Activity mCtx;
    private List<ModelInfoUpper> upperInfoList;

    public AdapterUpperInfoReycler(Activity mCtx, List<ModelInfoUpper> upperInfoList) {
        this.mCtx = mCtx;
        this.upperInfoList = upperInfoList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.row_info, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final ModelInfoUpper list = upperInfoList.get(position);
//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.color.colorPrimary);
//
//        Glide.with(Objects.requireNonNull(mCtx)).load("https://malang-paradise.000webhostapp.com/" + postingan.getGambar()).apply(requestOptions).into(holder.gambar);
        holder.info_judul.setText(list.getJudulInfo());
        holder.info_date.setText(list.getTglInput());
        holder.info_isi.setText(list.getIsiInfo());
        holder.info_nama.setText(list.getNamaUser());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, list.getIdInfo(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return upperInfoList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView info_gambar;
        TextView info_judul;
        TextView info_date;
        TextView info_isi;
        TextView info_nama;



        public ProductViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            info_gambar = view.findViewById(R.id.info_gambar);
            info_judul = view.findViewById(R.id.info_judul);
            info_date = view.findViewById(R.id.info_date);
            info_isi = view.findViewById(R.id.info_isi);
            info_nama = view.findViewById(R.id.info_nama);

        }
    }

}
