package com.devdroid.projday4fin2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextInputEditText userid,id,title,body;
    Button reg_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userid = findViewById(R.id.userid);
        id = findViewById(R.id.id);
        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        reg_user = findViewById(R.id.btn_register);

        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              saveUser(createRequest());
            }
        });
    }
    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUserid(Integer.parseInt(userid.getText().toString()));
        userRequest.setId(Integer.parseInt(id.getText().toString()));
        userRequest.setTitle(title.getText().toString());


        userRequest.setBody(body.getText().toString());

        return userRequest;
    }
    public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>(){
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response){
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
                    
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t){
                Toast.makeText(MainActivity.this, "Request failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}