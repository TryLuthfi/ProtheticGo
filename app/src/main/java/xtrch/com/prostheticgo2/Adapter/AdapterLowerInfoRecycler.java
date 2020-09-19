package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.recyclerview.widget.RecyclerView;
import xtrch.com.prostheticgo2.Model.ModelInfoLower;
import xtrch.com.prostheticgo2.R;


public class AdapterLowerInfoRecycler extends RecyclerView.Adapter<AdapterLowerInfoRecycler.ProductViewHolder>{
    private static final String TAG = "AdapterLowerInfoRecycler";

    private Activity mCtx;
    private List<ModelInfoLower> lowerInfoList;
    List<String> colors;
    int i1;
    GradientDrawable draw;

    public AdapterLowerInfoRecycler(Activity mCtx, List<ModelInfoLower> lowerInfoList) {
        this.mCtx = mCtx;
        this.lowerInfoList = lowerInfoList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

//        colors=new ArrayList<String>();
//
//        colors.add("#577D80");
//        colors.add("#F0D06B");
//        colors.add("#DA5A41");
//        colors.add("#00FFFF");


//        Random r = new Random();
//        i1 = r.nextInt(4- 0) + 0;

//        draw = new GradientDrawable();
//        draw.setColor(Color.parseColor(colors.get(i1)));

        View view = inflater.inflate(R.layout.row_info, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final ModelInfoLower list = lowerInfoList.get(position);
//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.color.colorPrimary);
//
//        Glide.with(Objects.requireNonNull(mCtx)).load("https://malang-paradise.000webhostapp.com/" + postingan.getGambar()).apply(requestOptions).into(holder.gambar);
        holder.row_judul.setText(list.getJudulInfo());
        holder.row_isi.setText(list.getIsiInfo());
        holder.model = list;
    }

    @Override
    public int getItemCount() {
        return lowerInfoList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView row_judul;
        TextView row_isi;
        ModelInfoLower model;

        public ProductViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            model = new ModelInfoLower();
            row_isi = view.findViewById(R.id.row_info_isi);
            row_judul = view.findViewById(R.id.row_info_judul);

        }
    }

}
