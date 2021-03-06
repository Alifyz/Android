package com.alifyz.canadatour;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NatureFragment extends Fragment {

    public NatureFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_nature, container, false);

        ArrayList<Place> nature = new ArrayList<Place>();

        nature.add(new Place(R.string.firstNatureTitle, R.string.firstNatureDescription,
                R.drawable.alberta));
        nature.add(new Place(R.string.secondNatureTitle, R.string.secondNatureDescription,
                R.drawable.niagara));
        nature.add(new Place(R.string.thirdNatureTitle, R.string.thirdNatureDescription,
                R.drawable.park));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), nature);

        ListView listView = (ListView) rootView.findViewById(R.id.nature);
        listView.setAdapter(adapter);

        return rootView;
    }

}
