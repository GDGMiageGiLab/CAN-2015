package com.gdg.miagegi.can2015.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdg.miagegi.can2015.R;

public class ProgrammeFragment extends Fragment {
    public ProgrammeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programme, container, false);

        /*
        MATCH 1
         */

        /* Poule A Match 1*/

        TextView guineeequato1 = (TextView) view.findViewById(R.id.score_guineeequatoriale);
        //guineeequato1.setText();
        TextView congobrazza1 = (TextView) view.findViewById(R.id.score_congobrazza);
        //congobrazza1.setText();
        TextView burkina1 = (TextView) view.findViewById(R.id.score_burkina);
        //burkina1.setText();
        TextView gabon1 = (TextView) view.findViewById(R.id.score_gabon);
        //gabon1.setText();

        /* Poule B Match 1*/

        TextView zambie1 = (TextView) view.findViewById(R.id.score_zambie);
        //zambie1.setText();
        TextView rdc1 = (TextView) view.findViewById(R.id.score_rdc);
        //rdc1.setText();
        TextView tunisie1 = (TextView) view.findViewById(R.id.score_tunisie);
        //tunisie1.setText();
        TextView capvert1 = (TextView) view.findViewById(R.id.score_capvert);
        //capvert1.setText();

        /* Poule C Match 1 */

        TextView ghana1 = (TextView) view.findViewById(R.id.score_ghana);
        //ghana1.setText();
        TextView senegal1 = (TextView) view.findViewById(R.id.score_senegal);
        //senegal1.setText();
        TextView algerie1 = (TextView) view.findViewById(R.id.score_algerie);
        //algerie1.setText();
        TextView afs1 = (TextView) view.findViewById(R.id.score_afs);
        //afs1.setText();

        /* Poule D Match 1*/

        TextView ci1 = (TextView) view.findViewById(R.id.score_ci);
        //ci1.setText();
        TextView guinee1 = (TextView) view.findViewById(R.id.score_guinee);
        //guinee1.setText();
        TextView mali1 = (TextView) view.findViewById(R.id.score_mali);
        //mali1.setText();
        TextView cameroun1 = (TextView) view.findViewById(R.id.score_cameroun);
        //cameroun1.setText();


        /*
        MATCH 2
         */

        /* Poule A Match 2*/

        TextView guineeequato2 = (TextView) view.findViewById(R.id.score_guineeequatoriale2);
        //guineeequato2.setText();
        TextView congobrazza2 = (TextView) view.findViewById(R.id.score_congobrazza2);
        //congobrazza2.setText();
        TextView burkina2 = (TextView) view.findViewById(R.id.score_burkina2);
        //burkina2.setText();
        TextView gabon2 = (TextView) view.findViewById(R.id.score_gabon2);
        //gabon2.setText();

        /* Poule B Match 2*/

        TextView zambie2 = (TextView) view.findViewById(R.id.score_zambie2);
        //zambie2.setText();
        TextView rdc2 = (TextView) view.findViewById(R.id.score_rdc2);
        //rdc2.setText();
        TextView tunisie2 = (TextView) view.findViewById(R.id.score_tunisie2);
        //tunisie2.setText();
        TextView capvert2 = (TextView) view.findViewById(R.id.score_capvert2);
        //capvert2.setText();

        /* Poule C Match 2 */

        TextView ghana2 = (TextView) view.findViewById(R.id.score_ghana2);
        //ghana2.setText();
        TextView senegal2 = (TextView) view.findViewById(R.id.score_senegal2);
        //senegal2.setText();
        TextView algerie2 = (TextView) view.findViewById(R.id.score_algerie2);
        //algerie2.setText();
        TextView afs2 = (TextView) view.findViewById(R.id.score_afs2);
        //afs2.setText();

        /* Poule D Match 2*/

        TextView ci2 = (TextView) view.findViewById(R.id.score_ci2);
        //ci2.setText();
        TextView guinee2 = (TextView) view.findViewById(R.id.score_guinee2);
        //guinee2.setText();
        TextView mali2 = (TextView) view.findViewById(R.id.score_mali2);
        //mali2.setText();
        TextView cameroun2 = (TextView) view.findViewById(R.id.score_cameroun2);
        //cameroun2.setText();


        /*
        MATCH 3
         */

        /* Poule A Match 3*/

        TextView guineeequato3 = (TextView) view.findViewById(R.id.score_guineeequatoriale3);
        //guineeequato3.setText();
        TextView congobrazza3 = (TextView) view.findViewById(R.id.score_congobrazza3);
        //congobrazza3.setText();
        TextView burkina3 = (TextView) view.findViewById(R.id.score_burkina3);
        //burkina3.setText();
        TextView gabon3 = (TextView) view.findViewById(R.id.score_gabon3);
        //gabon3.setText();

        /* Poule B Match 3*/

        TextView zambie3 = (TextView) view.findViewById(R.id.score_zambie3);
        //zambie3.setText();
        TextView rdc3 = (TextView) view.findViewById(R.id.score_rdc3);
        //rdc3.setText();
        TextView tunisie3 = (TextView) view.findViewById(R.id.score_tunisie3);
        //tunisie3.setText();
        TextView capvert3 = (TextView) view.findViewById(R.id.score_capvert3);
        //capvert3.setText();

        /* Poule C Match 3 */

        TextView ghana3 = (TextView) view.findViewById(R.id.score_ghana3);
        //ghana3.setText();
        TextView senegal3 = (TextView) view.findViewById(R.id.score_senegal3);
        //senegal3.setText();
        TextView algerie3 = (TextView) view.findViewById(R.id.score_algerie3);
        //algerie3.setText();
        TextView afs3 = (TextView) view.findViewById(R.id.score_afs3);
        //afs3.setText();

        /* Poule D Match 3*/

        TextView ci3 = (TextView) view.findViewById(R.id.score_ci3);
        //ci3.setText();
        TextView guinee3 = (TextView) view.findViewById(R.id.score_guinee3);
        //guinee3.setText();
        TextView mali3 = (TextView) view.findViewById(R.id.score_mali3);
        //mali3.setText();
        TextView cameroun3 = (TextView) view.findViewById(R.id.score_cameroun3);
        //cameroun3.setText();
        // Inflate the layout for this fragment
        return view;
    }

}
