package com.urqa.stress;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.urqa.stress.app.App;
import com.urqa.stress.app.AppPreferences;
import com.urqa.stress.service.ExecuteService;
import com.urqa.stress.common.Time;
import com.urqa.clientinterface.URQAController;

import java.util.List;

/**
 * @author seunoh on 2014. 05. 06..
 */
public class MainFragment extends Fragment {


    private EditText mApiKeyText;
    private EditText mPortText;
    private EditText mCountText;

    private Spinner mTimeSpinner;

    private RadioGroup mRadioGroup;
    private Button mStartView;
    private Button mStopView;

    private TextView mCheckView;


    private List<Time> mTimes = Lists.newArrayList(Time.values());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View VIEW = inflater.inflate(R.layout.fragment_main, container, false);

        mApiKeyText = (EditText) VIEW.findViewById(R.id.key_text);
        mPortText = (EditText) VIEW.findViewById(R.id.port_text);
        mCountText = (EditText) VIEW.findViewById(R.id.count_text);

        mTimeSpinner = (Spinner) VIEW.findViewById(R.id.spinner);
        mRadioGroup = (RadioGroup) VIEW.findViewById(R.id.radio_group);
        mStartView = (Button) VIEW.findViewById(R.id.start_button);
        mStopView = (Button) VIEW.findViewById(R.id.stop_button);
        mCheckView = (TextView) VIEW.findViewById(R.id.check_text);


        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(getActivity(), R.layout.spinner_item, mTimes);
        mTimeSpinner.setAdapter(adapter);
        mApiKeyText.setText(App.getPreferences().getString(AppPreferences.KEY_API, AppPreferences.DEFAULT_KEY));
        mPortText.setText("" + App.getPreferences().getLong(AppPreferences.KEY_PORT, AppPreferences.DEFAULT_PORT));
        mCountText.setText("" + App.getPreferences().getLong(AppPreferences.KEY_COUNT, AppPreferences.DEFAULT_COUNT));


        boolean b = App.getPreferences().getBoolean(AppPreferences.KEY_SERVICE, false);
        if (b)
            mRadioGroup.check(R.id.on);
        else
            mRadioGroup.check(R.id.off);


        mStartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String KEY = mApiKeyText.getText() != null ? mApiKeyText.getText().toString() : "";
                final String PORT = mPortText.getText() != null ? mPortText.getText().toString() : "";
                final String COUNT = mCountText.getText() != null ? mCountText.getText().toString() : "";

                if (!Strings.isNullOrEmpty(KEY)) {
                    URQAController.InitializeAndStartSession(getActivity(), KEY);
                    selectedRadioButton(mRadioGroup.getCheckedRadioButtonId());
                    selectedSpinner(mTimeSpinner);
                    port(PORT);
                    count(COUNT);
                    App.getPreferences().putString(AppPreferences.KEY_API, KEY);

                    start();
                } else {
                    mApiKeyText.setError(getString(R.string.error_empty_text));
                    mApiKeyText.requestFocus();
                }
            }
        });

        mStopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(App.getServiceIntent());
                stateService();
                stateEditText();
            }
        });


        stateService();

        return VIEW;
    }

    private void stateService() {
        if (isServiceRunning()) {
            mStartView.setVisibility(View.GONE);
            mStopView.setVisibility(View.VISIBLE);
            mCheckView.setText(R.string.running);
        } else {
            mStartView.setVisibility(View.VISIBLE);
            mStopView.setVisibility(View.GONE);
            mCheckView.setText(R.string.stopping);
        }
    }

    private void stateEditText() {
        if (isServiceRunning()) {
            mApiKeyText.setEnabled(false);
            mPortText.setEnabled(false);
            mCountText.setEnabled(false);
            mTimeSpinner.setEnabled(false);
            mRadioGroup.setEnabled(false);
        } else {
            mApiKeyText.setEnabled(true);
            mPortText.setEnabled(true);
            mCountText.setEnabled(true);
            mTimeSpinner.setEnabled(true);
            mRadioGroup.setEnabled(true);
        }
    }


    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (ExecuteService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void start() {
        getActivity().startService(App.getServiceIntent());
        stateService();
        stateEditText();
    }

    private void selectedRadioButton(int id) {

        switch (id) {
            case R.id.on:
                App.getPreferences().putBoolean(AppPreferences.KEY_SERVICE, true);
                break;
            case R.id.off:
            default:
                App.getPreferences().putBoolean(AppPreferences.KEY_SERVICE, false);
                break;
        }

    }

    private void selectedSpinner(Spinner spinner) {
        Time time = (Time) spinner.getSelectedItem();
        App.getPreferences().putLong(AppPreferences.KEY_TIME, time.getMilliseconds());
    }

    private void port(String s) {

        if (!Strings.isNullOrEmpty(s)) {
            try {
                long l = Long.parseLong(s);
                App.getPreferences().putLong(AppPreferences.KEY_PORT, l);
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
    }

    private void count(String s) {

        if (!Strings.isNullOrEmpty(s)) {
            try {
                long l = Long.parseLong(s);
                App.getPreferences().putLong(AppPreferences.KEY_COUNT, l);
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
    }
}
