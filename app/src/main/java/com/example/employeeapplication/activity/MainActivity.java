package com.example.employeeapplication.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeapplication.R;
import com.example.employeeapplication.adapter.EmployeeAdapter;
import com.example.employeeapplication.db.EmployeeDatabase;
import com.example.employeeapplication.model.Employee;
import com.example.employeeapplication.network.NetworkRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onStart() {
        super.onStart();

        NetworkRepository networkRepository = new NetworkRepository();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        EmployeeDatabase
                .getInstance(this)
                .employeeDAO()
                .getAllEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(databaseEmployees -> {
                    if (databaseEmployees.isEmpty()) {
                        networkRepository.getEmployees().subscribe(networkEmployees -> {
                            EmployeeDatabase
                                    .getInstance(this)
                                    .employeeDAO()
                                    .addEmployees(networkEmployees.toArray(new Employee[]{}))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe();
                            EmployeeAdapter adapter = new EmployeeAdapter(this, networkEmployees);
                            mRecyclerView.setAdapter(adapter);
                        });
                    } else {
                        EmployeeAdapter adapter = new EmployeeAdapter(this, databaseEmployees);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
    }
}