package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class MenuProductAdapter extends RecyclerView.Adapter<MenuProductAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a layout_products
    private List<MenuProduct> menuProductList;

    //getting the context and product layout_products with constructor
    public MenuProductAdapter(Context mCtx, List<MenuProduct> menuProductList) {
        this.mCtx = mCtx;
        this.menuProductList = menuProductList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the menuProduct of the specified position
        MenuProduct menuProduct = menuProductList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(menuProduct.getTitle());
       // holder.textViewShortDesc.setText(menuProduct.getShortdesc());
        //holder.textViewRating.setText(String.valueOf(menuProduct.getRating()));
        holder.textViewPrice.setText(String.valueOf(menuProduct.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(menuProduct.getImage()));

    }


    @Override
    public int getItemCount() {
        return menuProductList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            //textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            //textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
