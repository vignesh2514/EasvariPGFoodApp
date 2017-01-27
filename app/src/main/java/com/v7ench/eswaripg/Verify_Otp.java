package com.v7ench.eswaripg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Verify_Otp extends AppCompatActivity {
String Smonum,Snewamt;
    EditText Eentrotp;
    Button BVerifyotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__otp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         final Intent details=getIntent();
        Snewamt=details.getStringExtra("newamt");
        Smonum=details.getStringExtra("monum");
        Eentrotp=(EditText) findViewById(R.id.entr_det);
        BVerifyotp=(Button) findViewById(R.id.veriotp);
        BVerifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String veriop=Eentrotp.getText().toString();
                Snewamt=details.getStringExtra("newamt");
                Smonum=details.getStringExtra("monum");
                if (veriop.isEmpty())
                {
                    Toast.makeText(Verify_Otp.this,"Enter OTP to Proceed",Toast.LENGTH_SHORT).show();
                }
                else {
                    getmeout(veriop,Snewamt,Smonum);
                }
            }
        });
    }
    public void getmeout(final String veriop, final String snewamt, final String smonum)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config_url.verifyotp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
if (response.equals("success"))
{
    Toast.makeText(Verify_Otp.this,"Verified",Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(Verify_Otp.this, Result.class);

    startActivity(intent);

}
                else {
    Toast.makeText(Verify_Otp.this,"Not Verified",Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(Verify_Otp.this, WakeMeUp.class);
    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("monum",smonum);
                params.put("amtng",snewamt);
                params.put("otp",veriop);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
