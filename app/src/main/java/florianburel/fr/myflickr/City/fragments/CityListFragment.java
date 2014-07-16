package florianburel.fr.myflickr.City.fragments;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import florianburel.fr.myflickr.City.City;
import florianburel.fr.myflickr.City.Repository.CityDAO;
import florianburel.fr.myflickr.R;

public class CityListFragment extends Fragment {

    private ListView listView;
    private ArrayList<City> cities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_city_list, container, false);

        // Recuperation de la listView
        this.listView = (ListView) v.findViewById(R.id.listView);

        // Recuperation de la liste des villes a fficher via la DAO
        this.cities = CityDAO.getInstance(getActivity()).getAllCities();

        // Creation d'un adapter
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(getActivity(), android.R.layout.simple_list_item_1, this.cities);
        this.listView.setAdapter(adapter);

        return v;
    }


}
