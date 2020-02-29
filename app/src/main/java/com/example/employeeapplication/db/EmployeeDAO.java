package com.example.employeeapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeeapplication.model.Employee;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface EmployeeDAO {

    @Insert
    Completable addEmployees(Employee... employees);

    @Query("SELECT * FROM employee")
    Observable<List<Employee>> getAllEmployees();

    @Query("SELECT * FROM employee WHERE id = :id")
    Observable<Employee> getEmployeeById(int id);

    @Update
    Completable updateEmployee(Employee employee);

    @Delete
    Completable deleteEmployee(Employee employee);
}
