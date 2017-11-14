package mviel.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */

public class Fragment1 extends Fragment {

    private OnFragmentInteractionListener1 mListener;

    private RelativeLayout layoutF1;
    private FragmentManager fm;
    private FragmentTransaction ft;

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_main, container, false);
        layoutF1 = (RelativeLayout) v.findViewById(R.id.layoutF1);
        fm = getFragmentManager(); //un unic fragmentmanager

        layoutF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Has fet click en Fragment1",Toast.LENGTH_SHORT).show();

                ft = fm.beginTransaction();
                if (!mListener.estaFragment2EnActivity()) {
                    ft.add(R.id.canto_superior_dret, Fragment2.newInstance("", ""));
                    ft.addToBackStack(null);
                } else {
                    ft.remove(getFragmentManager().findFragmentById(R.id.canto_superior_dret));
                    fm.popBackStack(); //Si no afegeisc esta linea de codi el programa no funciona correctament
                }
                ft.commit();


            }
        });

        layoutF1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getActivity(),"Has fet un longClick",Toast.LENGTH_SHORT).show();

                FragmentTransaction ft = fm.beginTransaction();

                if (mListener.estaFragment2EnActivity()){
                    Toast.makeText(getActivity(),"Borrant Fragment 2",Toast.LENGTH_SHORT).show();
                    ft.remove(getActivity().getFragmentManager().findFragmentById(R.id.canto_superior_dret));
                    fm.popBackStack(); //si no afegeisc aquesta linea de codi el programa no funciona correctament
                }

                if (mListener.estaFragment3EnActivity()){
                    Toast.makeText(getActivity(),"Borrant Fragment 3",Toast.LENGTH_SHORT).show();
                    ft.remove(getActivity().getFragmentManager().findFragmentById(R.id.canto_inferior_dret));
                    fm.popBackStack(); //si no afegeisc aquesta linea de codi el programa no funciona correctament
                }
                ft.commit();


                return true;
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener1) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface OnFragmentInteractionListener1 {
        // TODO: Update argument type and name
        public void onFragmentInteraction1(Uri uri);
        boolean estaFragment3EnActivity();
        boolean estaFragment2EnActivity();
    }
}

