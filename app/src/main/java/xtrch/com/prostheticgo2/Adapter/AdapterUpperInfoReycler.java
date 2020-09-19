package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    List<String> colors;
    int i1;
    GradientDrawable draw;

    public AdapterUpperInfoReycler(Activity mCtx, List<ModelInfoUpper> upperInfoList) {
        this.mCtx = mCtx;
        this.upperInfoList = upperInfoList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        colors=new ArrayList<String>();

        colors.add("#577D80");
        colors.add("#F0D06B");
        colors.add("#DA5A41");
        colors.add("#00FFFF");


        Random r = new Random();
        i1 = r.nextInt(4- 0) + 0;

        draw = new GradientDrawable();
        draw.setColor(Color.parseColor(colors.get(i1)));

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
        holder.row_judul.setText(list.getJudulInfo());
        holder.row_isi.setText(list.getIsiInfo());
        holder.background.setBackground(draw);


    }

    @Override
    public int getItemCount() {
        return upperInfoList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView row_judul;
        TextView row_isi;
        RelativeLayout background;



        public ProductViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            row_isi = view.findViewById(R.id.row_info_isi);
            row_judul = view.findViewById(R.id.row_info_judul);
            background = view.findViewById(R.id.background);

        }
    }

}
