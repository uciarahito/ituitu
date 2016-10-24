package uci.develops.wiraenergimobile.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.RequestCustomerAdapter;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class ListRequestCustomerActivity extends AppCompatActivity {

    RecyclerView recycleViewRequest;
    List<CustomerModel> modelRequestList;
    RequestCustomerAdapter requestCustomerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_request_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycleViewRequest = (RecyclerView)findViewById(R.id.recycleListRequestCustomer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListRequestCustomerActivity.this);
        recycleViewRequest.setLayoutManager(mLayoutManager);
        recycleViewRequest.setItemAnimator(new DefaultItemAnimator());
        modelRequestList = new ArrayList<>();
        requestCustomerAdapter = new RequestCustomerAdapter(ListRequestCustomerActivity.this, modelRequestList);
        recycleViewRequest.setAdapter(requestCustomerAdapter);

        Call<RequestListCustomerResponse> requestListCustomerResponseCall = RestClient.getRestClient()
                .getAllRequestCustomer("Bearer "+new SharedPreferenceManager().getPreferences(ListRequestCustomerActivity.this, "token"));
        requestListCustomerResponseCall.enqueue(new Callback<RequestListCustomerResponse>() {
            @Override
            public void onResponse(Call<RequestListCustomerResponse> call, Response<RequestListCustomerResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getData().size() > 0){
                        Toast.makeText(ListRequestCustomerActivity.this, "Not empty", Toast.LENGTH_SHORT).show();
                        modelRequestList = response.body().getData();
                        requestCustomerAdapter.updateList(modelRequestList);
                    } else {
                        Toast.makeText(ListRequestCustomerActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ListRequestCustomerActivity.this, "Not successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestListCustomerResponse> call, Throwable t) {
                Toast.makeText(ListRequestCustomerActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                Log.e("ListRequest", ""+t.getMessage());
            }
        });
    }

}
