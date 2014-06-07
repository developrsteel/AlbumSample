package com.sample.album.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sample.album.R;
import com.sample.album.imgutil.ImageDownloader;
import java.util.ArrayList;

public class ItemsAdapter extends BaseAdapter {
    private ArrayList<Item> items;
    private Context mContext;
    public  boolean isGrid;

    public ItemsAdapter(ArrayList<Item> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
        this.isGrid = false;
    }

    public void setGrid(boolean isGrid){
        this.isGrid = isGrid;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemHolder itemHolder;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(isGrid){
                view = inflater.inflate(R.layout.grid_item, viewGroup, false);
            } else {
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            // make and initialize item holder
            itemHolder = new ItemHolder();
            itemHolder.tvMainHeader = (TextView) view.findViewById(R.id.main_header);
            itemHolder.tvSecondaryHeader =(TextView) view.findViewById(R.id.secondary_header);
            itemHolder.ivIcon = (ImageView) view.findViewById(R.id.icon);

            // store the item holder with the view
            view.setTag(itemHolder);
        } else {
            // in this way calling of findViewById every time is avoided, because now we can use
            // the already created and initialized item holder
            itemHolder = (ItemHolder) view.getTag();
        }

        Item item = (Item) getItem(i);

        itemHolder.tvMainHeader.setText(item.getMainHeader());
        itemHolder.tvSecondaryHeader.setText(item.getSecondaryHeader());
        ImageDownloader imgDownload = new ImageDownloader();
        imgDownload.ShowImage(item.getIconUrl(), itemHolder.ivIcon);

        return view;
    }

    /**
     * ItemHolder - ItemHolder that will cache list items
     */
    static class ItemHolder{
        TextView tvMainHeader;
        TextView tvSecondaryHeader;
        ImageView ivIcon;
    }
}
