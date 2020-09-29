package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Objects;

import xtrch.com.prostheticgo2.DetailFoto;
import xtrch.com.prostheticgo2.Model.ModelFotoProduk;
import xtrch.com.prostheticgo2.Model.ModelProduk;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class AdapterFotoProduk extends RecyclerView.Adapter<AdapterFotoProduk.FotoProdukViewHolder> {

    private Activity mCtx;
    private List<ModelFotoProduk> mItems;

    public AdapterFotoProduk(Activity mCtx, List<ModelFotoProduk> mItems) {
        this.mCtx = mCtx;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public FotoProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.row_foto, parent, false);
        return new FotoProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoProdukViewHolder holder, int position) {
        final ModelFotoProduk model = mItems.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorWhite);
        Glide.with(Objects.requireNonNull(mCtx)).load(Konfigurasi.URL_IMAGE_PRODUK + model.getFotoProduk()).apply(requestOptions).into(holder.foto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(mCtx, DetailFoto.class);
                detail.putExtra("foto_produk", model.getFotoProduk());
                mCtx.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class FotoProdukViewHolder extends RecyclerView.ViewHolder{

        ImageView foto;
        View view;

        public FotoProdukViewHolder(View itemView){
            super(itemView);
            view = itemView;
            foto = itemView.findViewById(R.id.row_foto_foto);
        }
    }
}
