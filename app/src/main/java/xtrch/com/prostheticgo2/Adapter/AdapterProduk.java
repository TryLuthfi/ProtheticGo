package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

import xtrch.com.prostheticgo2.Activity.DetailProduk;
import xtrch.com.prostheticgo2.Model.ModelProduk;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.ProdukViewHolder> {

    private Activity mCtx;
    private List<ModelProduk> mItems;

    public AdapterProduk(Activity mCtx, List<ModelProduk> mItems) {
        this.mCtx = mCtx;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.row_produk, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, int position) {
        DecimalFormat formatRupiah = (DecimalFormat) NumberFormat.getInstance();
        formatRupiah.setPositivePrefix("Rp. ");
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        final ModelProduk model = mItems.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);

        Glide.with(Objects.requireNonNull(mCtx)).load(Konfigurasi.URL_IMAGE_PRODUK + model.getFotoProduk()).apply(requestOptions).into(holder.foto);
        holder.tvNama.setText(model.getNamaProduk());
        holder.tvHarga.setText(formatRupiah.format(Double.valueOf(model.getHargaProduk())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(mCtx, DetailProduk.class);
                detail.putExtra("id_produk", model.getIdProduk());
                detail.putExtra("id_provider", model.getIdProvider());
                detail.putExtra("foto_produk", model.getFotoProduk());
                detail.putExtra("nama_produk", model.getNamaProduk());
                detail.putExtra("harga_produk", model.getHargaProduk());
                detail.putExtra("desk_produk", model.getDeskProduk());
                detail.putExtra("nohp_provider", model.getNoHpProvider());
                detail.putExtra("tgl_input", model.getTgl_input());
                detail.putExtra("nama_provider", model.getNamaProvider());
                detail.putExtra("foto_provider", model.getFotoProvider());
                mCtx.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ProdukViewHolder extends RecyclerView.ViewHolder{

        ImageView foto;
        TextView tvNama, tvHarga;
        View view;

        public ProdukViewHolder(View itemView){
            super(itemView);
            view = itemView;
            foto = itemView.findViewById(R.id.row_produk_foto);
            tvNama = itemView.findViewById(R.id.row_produk_nama);
            tvHarga = itemView.findViewById(R.id.row_produk_harga);
        }
    }
}
