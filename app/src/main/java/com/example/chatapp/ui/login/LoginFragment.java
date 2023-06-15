package com.example.chatapp.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chatapp.R;
import com.example.chatapp.data.model.response.ClientError;
import com.example.chatapp.data.model.response.Result;
import com.example.chatapp.data.model.Token;
import com.example.chatapp.settings.SettingsManager;
import com.example.chatapp.ui.BaseFragment;
import com.example.chatapp.ui.MainActivity;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

public class LoginFragment extends BaseFragment {

    private static final String TAG = Constants.LOGIN_FEATURE;

    private LoginViewModel loginViewModel;

    public LoginFragment() {

    }

    /**
     * Create new instance of this fragment.
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameText = view.findViewById(R.id.username);
        final EditText passwordText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.sign_in_button);
        final ProgressBar loginProgressBar = view.findViewById(R.id.progressBar);

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if (loginViewModel.getLoginResult().getValue() instanceof Result.Success) {
                    Token token = (Token) ((Result.Success) loginViewModel.getLoginResult().getValue()).getData();

                    SettingsManager.getInstance().storeAuthToken(token, getContext());
                    Logger.i(TAG, "Value stored");

                    loginProgressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                } else if (loginViewModel.getLoginResult().getValue() instanceof Result.Failure) {
                    ClientError error = (ClientError) ((Result.Failure) loginViewModel.getLoginResult().getValue()).getError();

                    loginProgressBar.setVisibility(View.GONE);
                    loginButton.setEnabled(true);
                } else if (loginViewModel.getLoginResult().getValue() instanceof Result.Error) {
                    loginProgressBar.setVisibility(View.GONE);
                    loginButton.setEnabled(true);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(getText(usernameText), getText(passwordText));
                loginProgressBar.setVisibility(View.VISIBLE);
                loginButton.setEnabled(false);
            }
        });

        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (loginViewModel.credentialsIsValid(getText(usernameText),
                        getText(passwordText))) {
                    loginButton.setEnabled(true);
                } else if (!(loginViewModel.usernameIsValid(getText(usernameText)))) {
                    usernameText.setError(getString(R.string.invalid_username));
                    loginButton.setEnabled(false);
                }
            }
        });

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (loginViewModel.credentialsIsValid(getText(usernameText),
                        getText(passwordText))) {
                    loginButton.setEnabled(true);
                } else if (!(loginViewModel.passwordIsValid(getText(passwordText)))) {
                    passwordText.setError(getString(R.string.invalid_password));
                    loginButton.setEnabled(false);
                }
            }
        });

        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((loginViewModel.credentialsIsValid(getText(usernameText), getText(passwordText)))
                    && actionId == EditorInfo.IME_ACTION_DONE
                    && loginButton.isActivated()) {
                    loginViewModel.login(getText(usernameText), getText(passwordText));
                    loginProgressBar.setVisibility(View.VISIBLE);
                    loginButton.setEnabled(false);
                }

                return false;
            }
        });
    }


    @NonNull
    private String getText(@NonNull EditText text) {
        return text.getText().toString();
    }

    @Nullable
    private LoginFragment getFragment() {
        return this;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        // TODO: Remove LoginViewModel handler callbacks
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}