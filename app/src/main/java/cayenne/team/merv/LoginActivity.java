package cayenne.team.merv;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginButton;
    protected TextView mSignUpTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // must be before setContentView
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSignUpTextView = (TextView)findViewById(R.id.signUpTextView);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mUsername = (EditText) findViewById(R.id.usernameEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);
        mLoginButton = (Button) findViewById(R.id.loginButton);

        Firebase.setAndroidContext(this);
        
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                username = username.trim();
                password = password.trim();

                if (username.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Firebase ref = new Firebase("https://crackling-fire-8381.firebaseio.com");
                    ref.authWithPassword(username, password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                            Toast toast = Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(R.string.login_error_message)
                                    .setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

//                    setProgressBarIndeterminateVisibility(true);
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
