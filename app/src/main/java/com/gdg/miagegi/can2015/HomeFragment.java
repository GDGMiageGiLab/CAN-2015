package com.gdg.miagegi.can2015;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.gdg.miagegi.can2015.fragment.FeedListFragment;
import com.gdg.miagegi.can2015.fragment.HistoriqueFragment;
import com.gdg.miagegi.can2015.fragment.StadesFragment;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener{

    private SliderLayout mDemoSlider;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        initUI(rootView);

        mDemoSlider = (SliderLayout)rootView.findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Tous contre EBOLA",R.drawable.ivoirien);
        file_maps.put("Champion d'afrique 2013",R.drawable.nigeria);
        file_maps.put("l'Algerie, un favorie...",R.drawable.algeri);
        file_maps.put("la CAF", R.drawable.slide1);
        file_maps.put("Eliminatoires ....", R.drawable.slide2);
        file_maps.put("Ballon officiel de la CAN 2015", R.drawable.ballon);
        file_maps.put("Adidas...", R.drawable.football);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        return rootView;
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
    }

    private void initUI(View v){
        Button button1 =(Button)v.findViewById(R.id.button1);
        Button button2 =(Button)v.findViewById(R.id.button2);
        Button button3 =(Button)v.findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tu fais ce que tu veux dans le onClick
                FragmentManager fragmentManager = getFragmentManager();
                Fragment historiquefragment = new HistoriqueFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, historiquefragment)
                        .commit();
                }
            });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tu fais ce que tu veux dans le onClick
                FragmentManager fragmentManager = getFragmentManager();
                Fragment stadesfragment = new StadesFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, stadesfragment)
                        .commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tu fais ce que tu veux dans le onClick
                FragmentManager fragmentManager = getFragmentManager();
                Fragment newsfragment = new FeedListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, newsfragment)
                        .commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
