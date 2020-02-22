package com.example.testapp.network;

import com.example.testapp.model.Employee;
import com.example.testapp.network.api.EmployeeAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRepository {

    private Retrofit retrofit = null;

    public NetworkRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dummy.restapiexample.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
    }

    public List<Employee> getEmployees() {
        JsonElement element = retrofit.create(EmployeeAPI.class).getEmployees();
        return parse(element);
    }

    private List<Employee> parse(JsonElement json) {
        List<Employee> employees = new ArrayList<>();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray array = jsonObject.get("data").getAsJsonArray();

        for (int i = 0; i < array.size(); i++) {
            Employee employee = new Employee();
            JsonObject employeeObject = array.get(i).getAsJsonObject();

            employee.setId(employeeObject.get("id").getAsInt());
            employee.setName(employeeObject.get("employee_name").getAsString());
            employee.setName(employeeObject.get("employee_name").getAsString());
            employee.setAge(employeeObject.get("employee_age").getAsInt());
            employee.setSalary(employeeObject.get("employee_salary").getAsFloat());
            employee.setPhoto("https://avatars1.githubusercontent.com/u/26285589");

            employees.add(employee);
        }

        return employees;
    }
}
