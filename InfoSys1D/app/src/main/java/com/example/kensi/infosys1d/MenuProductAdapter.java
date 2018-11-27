package com.example.kensi.infosys1d;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Belal on 10/18/2017.
 */


public class MenuProductAdapter extends RecyclerView.Adapter<MenuProductAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a menu_layout_products
    private List<MenuProduct> productList;



    //getting the context and product menu_layout_products with constructor
    public MenuProductAdapter(Context mCtx, List<MenuProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.menu_layout_products, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        MenuProduct product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        // holder.textViewShortDesc.setText(product.getShortdesc());
        //holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public int getItemPosition() {
        int id = 0;
        for (int position=0; position<productList.size(); position++) {
                id = position;
            }
            return id;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(final View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            //textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            //textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);





                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(itemView.getContext(), MenuPop.class);


                        intent.putExtra("viewpager_position", getAdapterPosition());

                        itemView.getContext().startActivity(intent);
                    }


                });

            }

        }


}





