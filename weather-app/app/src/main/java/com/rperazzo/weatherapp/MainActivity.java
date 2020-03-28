package com.rperazzo.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rperazzo.weatherapp.adapter.FindItemAdapter;
import com.rperazzo.weatherapp.api.WeatherManager;
import com.rperazzo.weatherapp.api.WeatherService;
import com.rperazzo.weatherapp.entities.City;
import com.rperazzo.weatherapp.entities.FindResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private static final String PREFERENCE_NAME = "com.rperazzo.weatherapp.shared";

    private MainActivityPresenter presenter;
    private SharedPreferences mSharedPref;
    private EditText mEditText;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private ListView mList;
    private FindItemAdapter mAdapter;
    private ArrayList<City> cities = new ArrayList<>();
    private Button btnSearch;
    private Button btnClean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPref = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        presenter = new MainActivityPresenter(this);

        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mList = (ListView) findViewById(R.id.list);
        btnSearch = (Button) findViewById(R.id.button);
        btnClean = (Button) findViewById(R.id.buttonClean);

        mAdapter = new FindItemAdapter(this, cities, mSharedPref);
        mList.setAdapter(mAdapter);

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    searchByName(mEditText.getText().toString());
                }
                return false;
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchByName(mEditText.getText().toString());
            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cleanList();
            }
        });

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

    private void updateUnitIfNecessary(String newUnits) {
        String currentUnits = mAdapter.getTemperatureUnit();
        if (!currentUnits.equals(newUnits)) {
            mAdapter.setTemperatureUnit(newUnits);
            searchByName(mEditText.getText().toString());
        }
    }

    private void onStartLoading() {
        mList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
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

    public boolean isDeviceConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    public void searchByName(String name) {
        if (!isDeviceConnected()) {
            Toast.makeText(this, "No connection!", Toast.LENGTH_LONG).show();
            return;
        }

        String search = mEditText.getText().toString();
        if (TextUtils.isEmpty(search)) {
            return;
        }

        onStartLoading();

        WeatherService wService = WeatherManager.getService();

        String units = mAdapter.getTemperatureUnit();
        final Call<FindResult> findCall = wService.find(search, units, WeatherManager.API_KEY);
        findCall.enqueue(new Callback<FindResult>() {
            @Override
            public void onResponse(Call<FindResult> call, Response<FindResult> response) {
                onFinishLoading(response.body());
            }

            @Override
            public void onFailure(Call<FindResult> call, Throwable t) {
                onFinishLoadingWithError();
            }
        });
    }

    @Override
    public void cleanList() {
        cities.clear();
        mAdapter.notifyDataSetChanged();
    }
}
