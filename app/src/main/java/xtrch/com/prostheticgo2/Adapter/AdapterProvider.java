package xtrch.com.prostheticgo2.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Objects;

import xtrch.com.prostheticgo2.Model.ModelProvider;
import xtrch.com.prostheticgo2.R;
import xtrch.com.prostheticgo2.Request.Konfigurasi;

public class AdapterProvider extends RecyclerView.Adapter<AdapterProvider.ProviderViewHoder> {

    private Activity mCtx;
    private List<ModelProvider> mItems;

    public AdapterProvider(Activity mCtx, List<ModelProvider> mItems) {
        this.mCtx = mCtx;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ProviderViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.row_provider, parent, false);
        return new AdapterProvider.ProviderViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHoder holder, int position) {
        ModelProvider model = mItems.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.color.colorPrimary);

        Glide.with(Objects.requireNonNull(mCtx)).load(Konfigurasi.URL_IMAGE_REHABILITASI + model.getFotoProvider()).apply(requestOptions).into(holder.foto);
        holder.tvNama.setText(model.getNamaProvider());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ProviderViewHoder extends RecyclerView.ViewHolder{

        ImageView foto;
        TextView tvNama;

        public ProviderViewHoder(View view){
            super(view);
        }
    }
}
