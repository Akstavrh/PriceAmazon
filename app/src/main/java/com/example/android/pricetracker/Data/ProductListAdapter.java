package com.example.android.pricetracker.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.pricetracker.R;

import java.util.List;

/**
 * Created by e.delcampo on 10/04/2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemView;

        private ProductViewHolder (View itemView) {
            super(itemView);
            productItemView = (TextView) itemView.findViewById(R.id.tv_asin);
        }
    }

    private final LayoutInflater mInflater;
    private List<Products> mProducts; // Cached copy of products

    ProductListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return null; //new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (mProducts != null) {
            Products current = mProducts.get(position);
            holder.productItemView.setText(current.getAsin());
        } else {
            // Covers the case of data not being ready yet.
            holder.productItemView.setText("No Products");
        }
    }

    void setProducts(List<Products> products){
        mProducts = products;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mProducts has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        else return 0;
    }
}