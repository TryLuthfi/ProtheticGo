package xtrch.com.prostheticgo2.Activity;

import android.app.Activity;
import android.content.Intent;
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
import xtrch.com.prostheticgo2.Adapter.AdapterUpperRehabRecycler;
import xtrch.com.prostheticgo2.Model.ModelPsikologi;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class AdapterPsikoRecycler extends RecyclerView.Adapter<AdapterPsikoRecycler.ProductViewHolder>{
    private static final String TAG = "AdapterPsikoRecycler";

    private Activity mCtx;
    private List<ModelPsikologi> psikoList;

    public AdapterPsikoRecycler(Activity mCtx, List<ModelPsikologi> psikoList) {
        this.mCtx = mCtx;
        this.psikoList = psikoList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.row_info, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final ModelPsikologi list = psikoList.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);

        Glide.with(Objects.requireNonNull(mCtx)).load(Konfigurasi.URL_IMAGE_PSIKO + list.getFotoPsiko()).apply(requestOptions).into(holder.info_gambar);
        holder.info_judul.setText(list.getJudulPsiko());
        holder.info_date.setText(list.getTglInput());
        holder.info_isi.setText("   "+list.getIsiPsiko());
        holder.info_nama.setText(list.getNamaDepanUser()+" "+list.getNamaBelakangUser());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, DetailPost.class);
                intent.putExtra("id_info", list.getIdPsiko());
                intent.putExtra("judul_info", list.getJudulPsiko());
                intent.putExtra("isi_info", list.getIsiPsiko());
                intent.putExtra("foto_info", list.getFotoPsiko());
                intent.putExtra("id_user", list.getIdUser());
                intent.putExtra("tanggal_input", list.getTglInput());
                intent.putExtra("nama_depan_user", list.getNamaDepanUser());
                intent.putExtra("nama_belakang_user", list.getNamaBelakangUser());
                intent.putExtra("email_user", list.getEmailUser());
                intent.putExtra("status", "psiko");
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return psikoList.size();
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