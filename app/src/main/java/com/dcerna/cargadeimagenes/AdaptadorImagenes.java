package com.dcerna.cargadeimagenes;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;

public class AdaptadorImagenes extends RecyclerView.Adapter<AdaptadorImagenes.ViewHolder> {
    private String[] localDataSet;
    private List<HeroImg> heroimg;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;


        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.ImageView);
            textView = (TextView) view.findViewById(R.id.textView);

        }

        public ImageView getImageView() {
            return imageView;
        }
        public TextView getTextView() {
            return textView;
        }
    }

    /**
     *
     * @param lista
     * @param cont
     */
    public AdaptadorImagenes(List<HeroImg> lista, Context cont) {
        this.heroimg = lista;
        this.context = cont;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String name = heroimg.get(position).getName();
        String url= heroimg.get(position).getImageurl();

        viewHolder.getTextView().setText(name);

        ImageRequest imgReq = new
                ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        viewHolder.getImageView().setImageBitmap(response);
                    }

                },600,600,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.ALPHA_8,
                error -> {
                    error.printStackTrace();
                }
        );

        MySingleton.getInstance(context).addToRequestQueue(imgReq);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return heroimg.size();
    }
}
