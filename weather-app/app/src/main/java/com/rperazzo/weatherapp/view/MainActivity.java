package com.rperazzo.weatherapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rperazzo.weatherapp.R;
import com.rperazzo.weatherapp.view.adapters.FindItemAdapter;
import com.rperazzo.weatherapp.model.City;
import com.rperazzo.weatherapp.model.FindResult;
import com.rperazzo.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel viewModel;

    private ListView mList;
    private EditText mEditText;
    private TextView mTextView;
    private ProgressBar mProgressBar;

    private FindItemAdapter mAdapter;
    private ArrayList<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        initUI();
        initObserver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_celcius) {
            updateUnitIfNecessary("metric");
            return true;
        } else if (id == R.id.menu_fahrenheit) {
            updateUnitIfNecessary("imperial");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Called from screen (XML)
    public void onSearchClick(View view) {
        searchByName();
    }


    private void initUI() {

        mList = findViewById(R.id.list);
        mEditText = findViewById(R.id.editText);
        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);

        mAdapter = new FindItemAdapter(this, cities, viewModel);
        mList.setAdapter(mAdapter);

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    searchByName();
                }

                return false;
            }
        });
    }

    private void initObserver() {
        viewModel.find().observe(this, new Observer<FindResult>() {

            @Override
            public void onChanged(@Nullable FindResult result) {
                if (result == null) {
                    onFinishLoadingWithError();
                    return;
                }

                onFinishLoading(result);
            }

        });

    }



    private void updateUnitIfNecessary(String newUnit) {
        String currentUnits = viewModel.getTemperatureUnit();
        if (!currentUnits.equals(newUnit)) {

            viewModel.saveTemperatureUnit(newUnit);
            searchByName();

        }
    }


    private void searchByName() {

        // check connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean isDeviceConnected = netInfo != null && netInfo.isConnected();

        if ( !isDeviceConnected ) {
            Toast.makeText(this, "No connection!", Toast.LENGTH_LONG).show();
            return;
        }

        // check empty text
        String search = mEditText.getText().toString();
        if (TextUtils.isEmpty(search)) {
            return;
        }


        // do Search
        onStartLoading();

        viewModel.find(search);
    }




    private void onStartLoading() {
        mList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void onFinishLoading(FindResult result){
        mProgressBar.setVisibility(View.GONE);
        cities.clear();

        if (result.list.size() > 0) {
            cities.addAll(result.list);
            mList.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        } else {
            mTextView.setText("No results.");
        }
    }

    private void onFinishLoadingWithError() {
        mProgressBar.setVisibility(View.GONE);
        mList.setVisibility(View.GONE);
        mTextView.setText("Error");
    }
}
