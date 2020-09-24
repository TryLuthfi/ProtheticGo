package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.RecyclerView;
import xtrch.com.prostheticgo2.Model.ModelRehabLower;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;


public class AdapterLowerRehabRecycler extends RecyclerView.Adapter<AdapterLowerRehabRecycler.ProductViewHolder>{
    private static final String TAG = "AdapterLowerRehabRecycler";

    private Activity mCtx;
    private List<ModelRehabLower> lowerRehabList;
    List<String> colors;
    int i1;
    GradientDrawable draw;

    public AdapterLowerRehabRecycler(Activity mCtx, List<ModelRehabLower> lowerRehabList) {
        this.mCtx = mCtx;
        this.lowerRehabList = lowerRehabList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.row_info, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final ModelRehabLower list = lowerRehabList.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);

        Glide.with(Objects.requireNonNull(mCtx)).load(Konfigurasi.URL_IMAGE_INFORMASI + list.getFotoRehab()).apply(requestOptions).into(holder.info_gambar);
        holder.info_judul.setText(list.getJudulRehab());
        holder.info_date.setText(list.getTglInput());
        holder.info_isi.setText("   "+list.getIsiRehab());
        holder.info_nama.setText(list.getNamaDepanUser()+" "+list.getNamaBelakangUser());
    }

    @Override
    public int getItemCount() {
        return lowerRehabList.size();
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