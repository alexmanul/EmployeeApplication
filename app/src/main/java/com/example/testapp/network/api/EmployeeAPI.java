package com.example.testapp.network.api;

import com.google.gson.JsonElement;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("/api/v1/employees")
    Response<JsonElement> getEmployees();

    @GET("/api/v1/api/v1/employee/{id}")
    Response<JsonElement> getEmployeeById(
            @Path("id") int id
    );
}
