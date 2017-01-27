package com.v7ench.eswaripg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Details_Section extends AppCompatActivity {
TextView Troomno,Tname,Tmonum,Tamt;
    String Sname,Sroomno,Smonum,Samt;
Button Bgenotp;
    EditText Eamt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__section);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tname=(TextView) findViewById(R.id.name_det);
        Troomno=(TextView) findViewById(R.id.roomno_det);
        Tmonum=(TextView) findViewById(R.id.mn_det);
        Tamt=(TextView) findViewById(R.id.amt_det);
        Bgenotp=(Button) findViewById(R.id.genotp);
        Eamt=(EditText) findViewById(R.id.amtpr_det);
        final Intent details=getIntent();
        Sname=details.getStringExtra("name");
        Sroomno=details.getStringExtra("roomnum");
        Smonum=details.getStringExtra("monum");
        Samt=details.getStringExtra("amt");
        Tname.setText(Sname);
        Troomno.setText("Room No:"+Sroomno);
        Tmonum.setText(Smonum);
        Tamt.setText("Consumed Amount Till Date: Rs."+Samt);
  Bgenotp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Smonum=details.getStringExtra("monum");
          Samt=details.getStringExtra("amt");
String Samtine=Eamt.getText().toString();
          if (Samtine.isEmpty())
          {
              Toast.makeText(Details_Section.this,"Enter amount to genrate otp",Toast.LENGTH_SHORT).show();
          }
          else
          {
              genrateotps(Smonum,Samt,Samtine);
              Intent intent = new Intent(Details_Section.this, Verify_Otp.class);
              intent.putExtra("monum",Smonum);
              intent.putExtra("newamt",Samtine);
              startActivity(intent);
          }
      }
  });
    }

public void genrateotps(final String smonum, final String samt, final String samtine)
{
    StringRequest stringRequest = new StringRequest(Request.Method.POST, Config_url.genotpmem, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

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
            params.put("amt",samt);
            params.put("newamt",samtine);
            return params;
        }
    };
    AppController.getInstance().addToRequestQueue(stringRequest);
}
}
