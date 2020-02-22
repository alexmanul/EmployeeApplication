package com.example.testapp.network.api;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("/api/v1/employees")
    JsonElement getEmployees();

    @GET("/api/v1/api/v1/employee/{id}")
    JsonElement getEmployeeById(
            @Path("id") int id
    );
}