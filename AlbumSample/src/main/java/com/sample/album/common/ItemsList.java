package com.sample.album.common;

import android.app.ListFragment;
import android.os.Bundle;
import java.util.ArrayList;

public class ItemsList extends ListFragment {

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

        ItemsAdapter itemsAdapter = new ItemsAdapter(dummies, getActivity());
        itemsAdapter.setGrid(false);
        setListAdapter(itemsAdapter);
    }

}
