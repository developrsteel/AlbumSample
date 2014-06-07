package com.sample.album.common;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sample.album.R;

import java.util.ArrayList;

/**
 * Created by DeveloprSteel on 6/7/2014.
 */
public class ItemsGrid extends Fragment{
    private ItemsAdapter itemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = getArguments().getInt("position");

        String title;
        String color;
        switch(position){
            case 1:
                title = "Songs";
                color = "75CC00";
                break;
            case 2:
                title = "Artists";
                color = "E4DB0F";
                break;
            default:
                title = "Albums";
                color = "C50000";
                break;
        }

        // Generate a list of 1000 dummy items
        ArrayList<Item> dummies = new ArrayList<Item>();
        for (int i = 0; i < 1000; i++){
            Item item = new Item();
            item.setMainHeader(title + " " + i);
            item.setSecondaryHeader("Gender " + i);
            item.setIconUrl("http://placehold.it/100x100/" + color + "/ffffff.png&text=" + i);
            dummies.add(item);
        }

        itemsAdapter = new ItemsAdapter(dummies, getActivity());
        itemsAdapter.setGrid(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gridview, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(itemsAdapter);
        return view;
    }
}
