package com.peepeep.transport.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.peepeep.transport.R;
import com.peepeep.transport.acitivities.Landingpage;
import com.peepeep.transport.acitivities.ResetPasswordActivity;
import com.peepeep.transport.interfaces.ResponceCallback;
import com.peepeep.transport.servicerequest.PP_RetrofitSevicecall;
import com.peepeep.transport.servicerequest.responsemodels.LoginDataset;
import com.peepeep.transport.servicerequest.retrofitrequestparams.Retrofit_RequestParams;
import com.peepeep.transport.uicomponents.PpEditText;
import com.peepeep.transport.utils.CommonHelper;
import com.peepeep.transport.utils.CommonUtils;
import com.peepeep.transport.utils.Constants;


import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * peepp225@gmail.com
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends Fragment implements ResponceCallback {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    CallbackManager callbackManager;
    private String TAG = "LoginActivity";
    // UI references.
    @BindView(R.id.email_edit)
    PpEditText mEmailView;
    @BindView(R.id.pwd_edit)
    EditText mPasswordView;
    @BindView(R.id.forgotpwd)
    TextView forgotpwd;
    @BindView(R.id.email_sign_in_button)
    Button mEmailSignInButton;
    @BindView(R.id.fblogin)
    AppCompatButton fblogin;
    @BindView(R.id.gmaillogin)
    AppCompatButton gmaillogin;


    private ProgressDialog mProgressView;
    private View mLoginFormView;
    private Context mContext;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 111;
    private PP_RetrofitSevicecall mRt_retrofitSevicecall;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_CONTACTS};

    public static boolean hasPermissions(Context mContext, String... permissions) {
        if (mContext != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this,view);
        mContext=getActivity();
        // Inflate the layout for this fragment
        callbackManager = CallbackManager.Factory.create();
        if (!hasPermissions(getActivity(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(Constants.GmailOauthid)
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mRt_retrofitSevicecall = new PP_RetrofitSevicecall(getContext(),this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            afterloginnavigation();
        }
        // mCommonHelper=new CommonHelper();
        // Set up the login form.



        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mEmailSignInButton.setBackground(CommonHelper.getGradientDrawable(getResources().getColor(R.color.colorDark), getResources().getColor(R.color.colorDark), 85));
        gmaillogin.setBackground(CommonHelper.getGradientDrawable(getResources().getColor(R.color.white), getResources().getColor(R.color.gmail_red), 45));
        fblogin.setBackground(CommonHelper.getGradientDrawable(getResources().getColor(R.color.white), getResources().getColor(R.color.facebook_color), 45));

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        fblogin.setOnClickListener(facebooklogin);
        gmaillogin.setOnClickListener(gmailogin);

        forgotpwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.forget_anv = Constants.PP_FORGETPWD;
                startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
            }
        });
        return view;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) ) {
            CommonHelper.setErrorTextBackground(mPasswordView,getActivity(), getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if(!isPasswordValid(password)){
            CommonHelper.setErrorTextBackground(mPasswordView,getActivity(), getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            CommonHelper.setErrorTextBackground(mEmailView,getActivity(), getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            CommonHelper.setErrorTextBackground(mEmailView,getActivity(),getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

         // startActivity(new Intent(getActivity(), Landingpage.class));


           mRt_retrofitSevicecall.loginpost(Constants.PP_LOGIN,email,password);

        }
        /*Intent intent = new Intent(getActivity(), BookingActivity.class);
        startActivity(intent);*/


    }

    OnClickListener gmailogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            getActivity().startActivityForResult(signInIntent, RC_SIGN_IN);

        }
    };

    public void afterloginnavigation() {
    Intent intent = new Intent(getActivity(),
            Landingpage.class);
    startActivity(intent);
        //startActivity(new Intent(LoginActivity.this,LandigpageActivity.class));
    }

    OnClickListener facebooklogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
            LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("email", "public_profile"));
        /*LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");*/
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    getUserDetails(loginResult);
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    CommonHelper.showErrorAlertDiaolog(mContext, "Facebook SignIn", "Facebook SignIn Failed");
                }
            });
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
            afterloginnavigation();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.getStatusCode());
            CommonHelper.showErrorAlertDiaolog(mContext, "Google SignIn", "Google SignIn Failed");
            /*updateUI(null);*/

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        afterloginnavigation();

                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }


    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        LoginDataset loginDataset= ((LoginDataset) responce);
        if(loginDataset.getIsResponseSuccess()==false)
        CommonHelper.showErrorAlertDiaolog(mContext, "Login Failure", loginDataset.getResponseMsg());
        else
        afterloginnavigation();

        afterloginnavigation();
    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}

