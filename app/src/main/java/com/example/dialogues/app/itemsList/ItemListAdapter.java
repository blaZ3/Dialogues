package com.example.dialogues.app.itemsList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogues.BR;
import com.example.dialogues.R;
import com.example.dialogues.app.models.pojos.Item;
import com.example.dialogues.databinding.LayoutItemListItemBinding;
import com.example.dialogues.utils.DataBindingViewHolder;

import java.util.ArrayList;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> {
    private static final String TAG = ItemListAdapter.class.getSimpleName();

    static ItemListAdapterInterface adapterInterfaceCallback;
    ArrayList<Item> items;
    Context context;

    public static class ItemListViewHolder extends DataBindingViewHolder{
        LayoutItemListItemBinding dataBinding;
        public ItemListViewHolder(ViewDataBinding viewDataBinding){
            super(viewDataBinding);
            dataBinding = (LayoutItemListItemBinding) viewDataBinding;

            dataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterInterfaceCallback.itemSelected(getAdapterPosition());
                }
            });
        }
    }

    public ItemListAdapter(Context context, ArrayList<Item> items, ItemListAdapterInterface callback){
        this.context = context;
        this.items = items;
        this.adapterInterfaceCallback = callback;
    }

    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {
        holder.dataBinding.setItem(items.get(position));
    }

    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListViewHolder viewHolder = new ItemListViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(context),
                        R.layout.layout_item_list_item, parent, false));
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (items != null){
            return items.size();
        }
        return 0;
    }

    public interface ItemListAdapterInterface{
        void itemSelected(int position);
    }
}
