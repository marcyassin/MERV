package cayenne.team.merv.Login;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import cayenne.team.merv.Main.MainActivity;
import cayenne.team.merv.R;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected EditText mPhone;
    protected Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText) findViewById(R.id.signUpUsernameEditText);
        mPassword = (EditText) findViewById(R.id.signUpPasswordEditText);
        mEmail = (EditText) findViewById(R.id.signUpEmailEditText);
        mPhone = (EditText) findViewById(R.id.signUpPhoneEditText);
        mSignUpButton = (Button) findViewById(R.id.signUpButton);

        Firebase.setAndroidContext(this);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();

                username = username.trim();
                password = password.trim();
                email = email.trim();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Firebase ref = new Firebase("https://crackling-fire-8381.firebaseio.com");
                    final String finalEmail = email;
                    final String finalPassword = password;
                    ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            Firebase ref = new Firebase("https://crackling-fire-8381.firebaseio.com");
                            ref.authWithPassword(finalEmail, finalPassword, new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {
                                    System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully Signed Up", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {
                                    // there was an error
                                }
                            });
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));
                        }
                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // there was an error
                        }
                    });
                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
