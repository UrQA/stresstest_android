package com.urqa.alpha;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.urqa.alpha.common.FileHelper;
import com.urqa.alpha.common.Information;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;

/**
 * @author seunoh on 2014. 05. 07..
 */
public class ResultFragment extends Fragment {

    private OnActivityCommandListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View VIEW = inflater.inflate(R.layout.fragment_result, container, false);


        try {

            List<Information> list = Lists.transform(FileHelper.getInstance().reader(), new Function<String, Information>() {
                @Override
                public Information apply(String input) {
                    Gson gson = new Gson();
                    return gson.fromJson(input, Information.class);
                }
            });

            ResultAdapter adapter = new ResultAdapter(getActivity(), list);
            ListView listView = (ListView) VIEW.findViewById(R.id.list_view);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }


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


    public class ResultAdapter extends ArrayAdapter<Information> {

        public ResultAdapter(Context context, List<Information> list) {
            super(context, 0, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_result, new FrameLayout(getActivity()));
                holder = new ViewHolder(convertView);

                if (convertView != null) {
                    convertView.setTag(holder);
                }
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            setViewHolder(holder, getItem(position));

            return convertView;
        }

        public void setViewHolder(ViewHolder holder, Information information) {
            DateTime dateTime = new DateTime(information.getMillis());
            holder.getDateText().setText(dateTime.toString("yyyy-MM-dd hh:mm:ss"));
            holder.getMessageText().setText(information.getExceptionMessage());
        }
    }

    private class ViewHolder {
        private final TextView mDateText;
        private final TextView mMessageText;

        private ViewHolder(View view) {
            mDateText = (TextView) view.findViewById(R.id.date_text);
            mMessageText = (TextView) view.findViewById(R.id.message_text);
        }

        public TextView getDateText() {
            return mDateText;
        }

        public TextView getMessageText() {
            return mMessageText;
        }
    }
}
