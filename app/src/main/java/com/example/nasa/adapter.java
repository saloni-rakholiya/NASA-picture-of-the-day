package com.example.nasa;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.exampleviewholder> implements Filterable {
    private  ArrayList<item> list1;
    private  ArrayList<item> full;

 public static class exampleviewholder extends RecyclerView.ViewHolder{


     public ImageView img;
     public TextView t;

     public exampleviewholder(@NonNull View itemView) {
         super(itemView);
         img=itemView.findViewById(R.id.img);
         t=itemView.findViewById(R.id.textt);

     }
 }

 public adapter(ArrayList<item> list)
 {
   list1=list;
   full=new ArrayList<>(list);
 }

    @NonNull
    @Override
    public exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


     View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.acard,parent,false);
     exampleviewholder vh=new exampleviewholder(v);
     return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull exampleviewholder holder, int position) {

     item current=list1.get(position);
     holder.img.setImageResource(current.getImageresource());
     holder.t.setText(current.getName());

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<item> filteredlist=new ArrayList<>();
            if(constraint==null || constraint.length()==0)
            {
                filteredlist.addAll(full);

            }
            else{ String filterpatten=constraint.toString().toLowerCase().trim();

            for(item item:full)
            {
                if(item.getName().toLowerCase().contains(filterpatten))
                    filteredlist.add(item);
            }
            }
            FilterResults  results=new FilterResults();
            results.values=filteredlist;
            return results;


        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list1.clear();
            list1.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
