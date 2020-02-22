package com.example.employeeapplication.network.api;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("/api/v1/employees")
    Observable<JsonElement> getEmployees();

    @GET("/api/v1/api/v1/employee/{id}")
    Observable<JsonElement> getEmployeeById(
            @Path("id") int id
    );
}
