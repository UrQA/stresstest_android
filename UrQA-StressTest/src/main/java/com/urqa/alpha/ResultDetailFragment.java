package com.urqa.alpha;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author seunoh on 2014. 05. 07..
 */
public class ResultDetailFragment extends Fragment {

    private OnActivityCommandListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View VIEW = inflater.inflate(R.layout.fragment_result, container, false);


        ListView listView = (ListView) VIEW.findViewById(R.id.list_view);


        return VIEW;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnActivityCommandListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnActivityCommandListener");
        }
    }

}