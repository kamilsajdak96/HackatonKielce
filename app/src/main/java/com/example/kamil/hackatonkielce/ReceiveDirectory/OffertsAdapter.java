package com.example.kamil.hackatonkielce.ReceiveDirectory;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.R;
import com.example.kamil.hackatonkielce.ReceiveDirectory.SingleItemDirectory.SingleItemActivity;

import java.util.List;

public class OffertsAdapter extends RecyclerView.Adapter<OffertsAdapter.OffertViewHolder>{

    private Context context;
    private List<Offerts> offertsList;


    public OffertsAdapter(Context context, List<Offerts> offertsLists) {
        this.context = context;
        this.offertsList = offertsLists;


    }

    public interface OnItemClickListener {
        void onOffertClick(Offerts offert);
    }



    @NonNull
    @Override
    public OffertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recive_sinle_item, parent, false);
        return new OffertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffertViewHolder holder, int position) {

        final Offerts offert = offertsList.get(position);

        //holder.imageView =offertsList
        if(offert.sallary!=null){
            holder.textViewPrice.setText(offert.sallary);
        } else {
            holder.textViewPrice.setText("");
        }

         holder.textViewTitle.setText(offert.title);

         holder.setItemClickListener(new ItemClickListener() {
             @Override
             public void onClick(View view, int positon) {
//                 Toast.makeText(context,"co tam kolego",Toast.LENGTH_SHORT).show();

                 Intent toy = new Intent(context, SingleItemActivity.class);
                 toy.putExtra("addPhoneNumber",offert.addPhoneNumber.toString());
                toy.putExtra("desc",offert.description.toString());
                toy.putExtra("PaidOffer",offert.idOfPaidOffer.toString());
                 if(offert.sallary!=null){
                 toy.putExtra("sallary",offert.sallary.toString()); }
                 toy.putExtra("title",offert.title.toString());

                 context.startActivity(toy);
             }
         });

    }



    @Override
    public int getItemCount() {
        return offertsList.size();
    }

    class OffertViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textViewTitle;
        TextView textViewPrice;

        private ItemClickListener itemClickListener;

        public OffertViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
                itemClickListener.onClick(v,getAdapterPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener ){
            this.itemClickListener = itemClickListener;
        }
    }
}
