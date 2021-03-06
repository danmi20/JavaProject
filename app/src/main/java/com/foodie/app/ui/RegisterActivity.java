package com.foodie.app.ui;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.foodie.app.R;
import com.foodie.app.database.CallBack;
import com.foodie.app.database.DBManagerFactory;
import com.foodie.app.database.DataStatus;
import com.foodie.app.entities.CPUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;


public class RegisterActivity extends AppCompatActivity {


    ConstraintLayout constraintLayout;
    Snackbar snackbar;
    LoadingButton signUpBtn = null;

    /**
     * Called when the activity is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        constraintLayout = (ConstraintLayout) findViewById(R.id.activity_register);

        final TextView signInTextView = (TextView) findViewById(R.id.signInTextView);
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement transition animation to register activity.
                //signInTextView.setTextColor(Color.parseColor("#BDBDBD"));
                finish();
            }
        });

        this.signUpBtn = (LoadingButton) findViewById(R.id.sign_up_btn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                signUpBtn.startLoading();


                CPUser user = new CPUser();
                try {
                    MaterialEditText p1 = (MaterialEditText) findViewById(R.id.pwdEditText);
                    MaterialEditText p2 = (MaterialEditText) findViewById(R.id.confPwdNameEditText);

                    if (!p1.getText().toString().trim().equals(p2.getText().toString().trim()))
                        throw new Exception("Your passwords doesn't match!");

                    user.setUserFullName(((MaterialEditText) findViewById(R.id.userNameEditText)).getText().toString().trim());
                    user.setUserEmail(((MaterialEditText) findViewById(R.id.emailEditText)).getText().toString().trim());
                    user.setUserPwd(((MaterialEditText) findViewById(R.id.pwdEditText)).getText().toString().trim());


                } catch (Exception e) {

                    snackbar = Snackbar.make(constraintLayout, e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    signUpBtn.loadingFailed();
                    return;
                }


                //Create an AsyncData object and set the constructor
                DBManagerFactory.signUp(user, new CallBack<CPUser>() {
                    @Override
                    public void onSuccess(List<CPUser> data) {
                        signUpBtn.loadingSuccessful();
                        snackbar = Snackbar.make(constraintLayout, "Success", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        //TODO animation when succeed
                        finish();
                    }

                    @Override
                    public void onFailed(DataStatus status, String error) {
                        snackbar = Snackbar.make(constraintLayout, "Failed", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        signUpBtn.loadingFailed();
                    }


                });

            }
        });


    }
}
