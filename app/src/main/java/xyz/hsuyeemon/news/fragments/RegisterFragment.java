package xyz.hsuyeemon.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.data.models.LoginUserModel;
import xyz.hsuyeemon.news.data.models.RegisterModel;
import xyz.hsuyeemon.news.events.SuccessLoginEvent;
import xyz.hsuyeemon.news.events.SuccessRegisterEvent;

/**
 * Created by Dell on 1/20/2018.
 */

public class RegisterFragment extends Fragment {


    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;

    @BindView(R.id.et_password_register)
    EditText etPasswordRegister;

    @BindView(R.id.et_confirm_password_register)
    EditText etConfirmPasswordRegister;

    @BindView(R.id.et_email_register)
    EditText etEmailRegister;

    @BindView(R.id.et_address)
    EditText etAddress;

    @BindView(R.id.rg_gender)
    RadioGroup rgGender;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,view);
        return view;
    }



    @OnClick(R.id.btn_register)
    public void onTapRegister(View view) {  //represent login button
        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String emailRegister = etEmailRegister.getText().toString();
        String passwordRegister = etPasswordRegister.getText().toString();
        String confirmPasswordRegister = etConfirmPasswordRegister.getText().toString();
        String address = etAddress.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etName.setError("Name cannot be empty");
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            etPhoneNumber.setError("Phone number cannot be empty");
            return;
        }
        if (TextUtils.isEmpty(emailRegister)) {
            etEmailRegister.setError("Email cannot be empty");
            return;
        }
        if (TextUtils.isEmpty(passwordRegister)) {
            etPasswordRegister.setError("Password cannot be empty");
            return;
        }
        if (TextUtils.isEmpty(confirmPasswordRegister)) {
            etConfirmPasswordRegister.setError("Confirm password cannot be empty");
            return;
        }
        if (!passwordRegister.equals(confirmPasswordRegister)) {
            etConfirmPasswordRegister.setError("Password and confirm password must be same");
            return;
        }
        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Please Choose the Gender", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(address)) {
            etAddress.setError("Address cannot be empty");
            return;
        }
        RegisterModel.getObjectInstance().registerUser(name,phoneNumber,passwordRegister);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(SuccessRegisterEvent event){

        if(getActivity() != null){
            getActivity().onBackPressed();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}


