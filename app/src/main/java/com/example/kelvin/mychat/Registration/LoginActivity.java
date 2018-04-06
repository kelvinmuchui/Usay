package com.example.kelvin.mychat.Registration;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelvin.mychat.Model.User;
import com.example.kelvin.mychat.R;
import com.example.kelvin.mychat.Usay.MainActivity;
import com.example.kelvin.mychat.data.SharedPreferenceHelper;
import com.example.kelvin.mychat.data.StaticConfig;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kelvin on 3/31/18.
 */

public class LoginActivity extends AppCompatActivity{

    private static String TAG = "LoginActivity";
//    FloatingActionButton fab;
//    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
//            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//    private EditText editTextUsername, editTextPassword;
//    private LovelyProgressDialog waitingDialog;
//
//    private AuthUtils authUtils;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    private FirebaseUser user;
//    private boolean firstTimeAccess;
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        editTextUsername = (EditText) findViewById(R.id.et_username);
//        editTextPassword = (EditText) findViewById(R.id.et_password);
//        firstTimeAccess = true;
//        initFirebase();
//    }
//
//
//    /**
//     * Khởi tạo các thành phần cần thiết cho việc quản lý đăng nhập
//     */
//    private void initFirebase() {
//        //Khoi tao thanh phan de dang nhap, dang ky
//        mAuth = FirebaseAuth.getInstance();
//        authUtils = new AuthUtils();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    StaticConfig.UID = user.getUid();
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    if (firstTimeAccess) {
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        LoginActivity.this.finish();
//                    }
//                } else {
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//                firstTimeAccess = false;
//            }
//        };
//
//        //Khoi tao dialog waiting khi dang nhap
//        waitingDialog = new LovelyProgressDialog(this).setCancelable(false);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public void clickRegisterLayout(View view) {
//        getWindow().setExitTransition(null);
//        getWindow().setEnterTransition(null);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options =
//                    ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
//            startActivityForResult(new Intent(this, RegisterActivity.class), StaticConfig.REQUEST_CODE_REGISTER, options.toBundle());
//        } else {
//            startActivityForResult(new Intent(this, RegisterActivity.class), StaticConfig.REQUEST_CODE_REGISTER);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == StaticConfig.REQUEST_CODE_REGISTER && resultCode == RESULT_OK) {
//            authUtils.createUser(data.getStringExtra(StaticConfig.STR_EXTRA_USERNAME), data.getStringExtra(StaticConfig.STR_EXTRA_PASSWORD));
//        }
//    }
//
//    public void clickLogin(View view) {
//        String username = editTextUsername.getText().toString();
//        String password = editTextPassword.getText().toString();
//        if (validate(username, password)) {
//            authUtils.signIn(username, password);
//        } else {
//            Toast.makeText(this, "Invalid email or empty password", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        setResult(RESULT_CANCELED, null);
//        finish();
//    }
//
//    private boolean validate(String emailStr, String password) {
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
//        return (password.length() > 0 || password.equals(";")) && matcher.find();
//    }
//
//    public void clickResetPassword(View view) {
//        String username = editTextUsername.getText().toString();
//        if (validate(username, ";")) {
//            authUtils.resetPassword(username);
//        } else {
//            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * Dinh nghia cac ham tien ich cho quas trinhf dang nhap, dang ky,...
//     */
//    class AuthUtils {
//        /**
//         * Action register
//         *
//         * @param email
//         * @param password
//         */
//        void createUser(String email, String password) {
//            waitingDialog.setIcon(R.drawable.ic_add_friend)
//                    .setTitle("Registering....")
//                    .setTopColorRes(R.color.colorPrimary)
//                    .show();
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//                            waitingDialog.dismiss();
//                            // If sign in fails, display a message to the user. If sign in succeeds
//                            // the auth state listener will be notified and logic to handle the
//                            // signed in user can be handled in the listener.
//                            if (!task.isSuccessful()) {
//                                new LovelyInfoDialog(LoginActivity.this) {
//                                    @Override
//                                    public LovelyInfoDialog setConfirmButtonText(String text) {
//                                        findView(com.yarolegovich.lovelydialog.R.id.ld_btn_confirm).setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                dismiss();
//                                            }
//                                        });
//                                        return super.setConfirmButtonText(text);
//                                    }
//                                }
//                                        .setTopColorRes(R.color.colorAccent)
//                                        .setIcon(R.drawable.ic_add_friend)
//                                        .setTitle("Register false")
//                                        .setMessage("Email exist or weak password!")
//                                        .setConfirmButtonText("ok")
//                                        .setCancelable(false)
//                                        .show();
//                            } else {
//                                initNewUserInfo();
//                                Toast.makeText(LoginActivity.this, "Register and Login success", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                LoginActivity.this.finish();
//                            }
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            waitingDialog.dismiss();
//                        }
//                    })
//            ;
//        }
//
//
//        /**
//         * Action Login
//         *
//         * @param email
//         * @param password
//         */
//        void signIn(String email, String password) {
//            waitingDialog.setIcon(R.drawable.ic_person_low)
//                    .setTitle("Login....")
//                    .setTopColorRes(R.color.colorPrimary)
//                    .show();
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
//                            // If sign in fails, display a message to the user. If sign in succeeds
//                            // the auth state listener will be notified and logic to handle the
//                            // signed in user can be handled in the listener.
//                            waitingDialog.dismiss();
//                            if (!task.isSuccessful()) {
//                                Log.w(TAG, "signInWithEmail:failed", task.getException());
//                                new LovelyInfoDialog(LoginActivity.this) {
//                                    @Override
//                                    public LovelyInfoDialog setConfirmButtonText(String text) {
//                                        findView(com.yarolegovich.lovelydialog.R.id.ld_btn_confirm).setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                dismiss();
//                                            }
//                                        });
//                                        return super.setConfirmButtonText(text);
//                                    }
//                                }
//                                        .setTopColorRes(R.color.colorAccent)
//                                        .setIcon(R.drawable.ic_person_low)
//                                        .setTitle("Login false")
//                                        .setMessage("Email not exist or wrong password!")
//                                        .setCancelable(false)
//                                        .setConfirmButtonText("Ok")
//                                        .show();
//                            } else {
//                                saveUserInfo();
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                LoginActivity.this.finish();
//                            }
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            waitingDialog.dismiss();
//                        }
//                    });
//        }
//
//        /**
//         * Action reset password
//         *
//         * @param email
//         */
//        void resetPassword(final String email) {
//            mAuth.sendPasswordResetEmail(email)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            new LovelyInfoDialog(LoginActivity.this) {
//                                @Override
//                                public LovelyInfoDialog setConfirmButtonText(String text) {
//                                    findView(com.yarolegovich.lovelydialog.R.id.ld_btn_confirm).setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dismiss();
//                                        }
//                                    });
//                                    return super.setConfirmButtonText(text);
//                                }
//                            }
//                                    .setTopColorRes(R.color.colorPrimary)
//                                    .setIcon(R.drawable.ic_pass_reset)
//                                    .setTitle("Password Recovery")
//                                    .setMessage("Sent email to " + email)
//                                    .setConfirmButtonText("Ok")
//                                    .show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            new LovelyInfoDialog(LoginActivity.this) {
//                                @Override
//                                public LovelyInfoDialog setConfirmButtonText(String text) {
//                                    findView(com.yarolegovich.lovelydialog.R.id.ld_btn_confirm).setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dismiss();
//                                        }
//                                    });
//                                    return super.setConfirmButtonText(text);
//                                }
//                            }
//                                    .setTopColorRes(R.color.colorAccent)
//                                    .setIcon(R.drawable.ic_pass_reset)
//                                    .setTitle("False")
//                                    .setMessage("False to sent email to " + email)
//                                    .setConfirmButtonText("Ok")
//                                    .show();
//                        }
//                    });
//        }
//
//        /**
//         * Luu thong tin user info cho nguoi dung dang nhap
//         */
//        void saveUserInfo() {
//            FirebaseDatabase.getInstance().getReference().child("user/" + StaticConfig.UID).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    waitingDialog.dismiss();
//                    HashMap hashUser = (HashMap) dataSnapshot.getValue();
//                    User userInfo = new User();
//                    userInfo.name = (String) hashUser.get("name");
//                    userInfo.email = (String) hashUser.get("email");
//                    userInfo.avata = (String) hashUser.get("avata");
//                    SharedPreferenceHelper.getInstance(LoginActivity.this).saveUserInfo(userInfo);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//
//        /**
//         * Khoi tao thong tin mac dinh cho tai khoan moi
//         */
//
//        void initNewUserInfo() {
//            User newUser = new User();
//            newUser.email = user.getEmail();
//            newUser.name = user.getEmail().substring(0, user.getEmail().indexOf("@"));
//            newUser.avata = StaticConfig.STR_DEFAULT_BASE64;
//            FirebaseDatabase.getInstance().getReference().child("user/" + user.getUid()).setValue(newUser);
//        }
//    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    EditText phoneed, codeed;
    FloatingActionButton fabbutton;
    String mVerificationId;
    TextView timertext;
    Timer timer;
    ImageView verifiedimg;
    Boolean mVerified = false;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.phone_number_activity);
        phoneed = findViewById(R.id.numbered);
        codeed = findViewById(R.id.verificationed);
        fabbutton = findViewById(R.id.sendverifybt);
        timertext = findViewById(R.id.timertv);
        verifiedimg = findViewById(R.id.verifiedsign);
        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                Log.d("TAG", "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TAG", "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Snackbar snackbar = Snackbar
                            .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Verification Failed !! Invalied verification Code", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
                else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar snackbar = Snackbar
                            .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Verification Failed !! Too many request. Try after some time. ", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("TAG", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
        fabbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fabbutton.getTag().equals(getResources().getString(R.string.tag_send))) {
                    if (!phoneed.getText().toString().trim().isEmpty() && phoneed.getText().toString().trim().length() >= 10) {
                        startPhoneNumberVerification(phoneed.getText().toString().trim());
                        mVerified = false;
                        starttimer();
                        codeed.setVisibility(View.VISIBLE);
                        fabbutton.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
                        fabbutton.setTag(getResources().getString(R.string.tag_verify));
                    }
                    else {
                        phoneed.setError("Please enter valid mobile number");
                    }
                }

                if (fabbutton.getTag().equals(getResources().getString(R.string.tag_verify))) {
                    if (!codeed.getText().toString().trim().isEmpty() && !mVerified) {
                        Snackbar snackbar = Snackbar
                                .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Please wait...", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, codeed.getText().toString().trim());
                        signInWithPhoneAuthCredential(credential);
                    }
                    if (mVerified) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                }


            }
        });

        timertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phoneed.getText().toString().trim().isEmpty() && phoneed.getText().toString().trim().length() == 10) {
                    resendVerificationCode(phoneed.getText().toString().trim(), mResendToken);
                    mVerified = false;
                    starttimer();
                    codeed.setVisibility(View.VISIBLE);
                    fabbutton.setImageResource(R.drawable.ic_arrow_forward_white_24dp);
                    fabbutton.setTag(getResources().getString(R.string.tag_verify));
                    Snackbar snackbar = Snackbar
                            .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Resending verification code...", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            mVerified = true;
                            timer.cancel();
                            verifiedimg.setVisibility(View.VISIBLE);
                            timertext.setVisibility(View.INVISIBLE);
                            phoneed.setEnabled(false);
                            codeed.setVisibility(View.INVISIBLE);
                            Snackbar snackbar = Snackbar
                                    .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Successfully Verified", Snackbar.LENGTH_LONG);

                            snackbar.show();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Snackbar snackbar = Snackbar
                                        .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Invalid OTP ! Please enter correct OTP", Snackbar.LENGTH_LONG);

                                snackbar.show();
                            }
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

    }

    public void starttimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            int second = 60;

            @Override
            public void run() {
                if (second <= 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timertext.setText("RESEND CODE");
                            timer.cancel();
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timertext.setText("00:" + second--);
                        }
                    });
                }

            }
        }, 0, 1000);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks

    }
}























