package com.rperazzo.weatherapp.mvc.view.activity;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rperazzo.weatherapp.R;
import com.rperazzo.weatherapp.mvc.model.entity.City;
import com.rperazzo.weatherapp.mvc.controller.Controller
import com.rperazzo.weatherapp.mvc.controller.INotification
import com.rperazzo.weatherapp.mvc.view.adapter.FindItemAdapter


public class MainActivity: AppCompatActivity(), INotification {

    companion object {
        private val PREFERENCE_NAME: String = "com.rperazzo.weatherapp.shared";
        private val TEMPERATURE_UNIT_KEY: String = "TEMPERATURE_UNIT_KEY";
    }
    private lateinit var mSharedPref: SharedPreferences;
    private lateinit var mEditText : EditText;
    private lateinit var mTextView: TextView;
    private lateinit var mProgressBar: ProgressBar;
    private lateinit var mList: ListView;
    private lateinit var mAdapter: FindItemAdapter;
    private val cities = ArrayList<City>()

    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPref = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        initViews()
        initController()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int? = item?.itemId;

        if (id == R.id.menu_celcius) {
            updateUnitIfNecessary("metric");
            return true;
        } else if (id == R.id.menu_fahrenheit) {
            updateUnitIfNecessary("imperial");
            return true;
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        mEditText = findViewById(R.id.editText) as EditText;
        mTextView = findViewById(R.id.textView) as TextView;
        mProgressBar = findViewById(R.id.progressBar) as ProgressBar;
        mList = findViewById(R.id.list) as ListView;

        mAdapter = FindItemAdapter(this, cities);
        mList.setAdapter(mAdapter);

        mEditText.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)){
                searchByName()
            }
            false
        }
    }

    private fun initController() {
        controller = Controller(this)
    }

    fun onSearchClick(view: View) {
        searchByName();
    }

    private fun onStartLoading() {
        mList.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);

        val view: View = this.currentFocus;
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private fun onFinishLoading(result: List<City>){
        mProgressBar.setVisibility(View.GONE);
        cities.clear();

        if (result.size > 0) {
            cities.addAll(result);
            mList.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        } else {
            mTextView.setText("No results.");
        }
    }

    private fun onFinishLoadingWithError() {
        mProgressBar.setVisibility(View.GONE);
        mList.setVisibility(View.GONE);
        mTextView.setText("Error");
    }

    fun isDeviceConnected(): Boolean{
        val cm: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo: NetworkInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    private fun searchByName() {
        if (!isDeviceConnected()) {
            Toast.makeText(this, "No connection!", Toast.LENGTH_LONG).show();
            return;
        }

        val search: String = mEditText.getText().toString();
        if (TextUtils.isEmpty(search)) {
            return
        }
        val units: String = getTemperatureUnit();

        onStartLoading();

        controller.searchCityByName(search, units)
    }

    fun setTemperatureUnit(value: String){
        val editor: SharedPreferences.Editor = mSharedPref.edit();
        editor.putString(TEMPERATURE_UNIT_KEY, value);
        editor.apply();
    }

    fun getTemperatureUnit(): String {
        val unit: String = mSharedPref?.getString(TEMPERATURE_UNIT_KEY, "metric") ?: "metric"
        return unit;
    }

    private fun updateUnitIfNecessary(newUnits: String) {
        val currentUnits: String = getTemperatureUnit()
        if (!currentUnits.equals(newUnits)) {
            setTemperatureUnit(newUnits);
            searchByName();
        }
    }

    override fun notifyView(cities: List<City>){
        onFinishLoading(cities)
    }

    override fun notifyViewError(){
        onFinishLoadingWithError()
    }

}
