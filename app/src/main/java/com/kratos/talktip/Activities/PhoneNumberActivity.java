package com.kratos.talktip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.kratos.talktip.databinding.ActivityPhoneNumberBinding;

public class PhoneNumberActivity extends AppCompatActivity {
    ActivityPhoneNumberBinding binding;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(PhoneNumberActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        getSupportActionBar().hide();
        binding.phoneBox.requestFocus();//for key board display

        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = binding.phoneBox.getText().toString();
                if(!phoneNumber.isEmpty()){
                    if((phoneNumber.trim()).length() == 10) {
                        Intent intent = new Intent(PhoneNumberActivity.this,OTPActivity.class);
                        intent.putExtra("phoneNumber", "+91"+phoneNumber);//phone number send to Otp activity
                        startActivity(intent);
                    }
                    else{
                        binding.phoneBox.setError("Enter 10 digit phone number");
                    }
                }
                else{
                    binding.phoneBox.setError("Enter mobile number");
                }
            }
        });
    }
}