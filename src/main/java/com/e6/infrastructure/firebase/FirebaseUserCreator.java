package com.e6.infrastructure.firebase;

import com.e6.infrastructure.util.security.PasswordGenerator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FirebaseUserCreator {

    public UserRecord registerUser(String email) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest();
        request.setEmail(email);
        //request.setPassword(PasswordGenerator.generateTempPassword());
        request.setPassword("TEMP_PASSWORD");
        request.setDisabled(false);
        request.setEmailVerified(false);
        UserRecord user = FirebaseAuth.getInstance().createUser(request);
        FirebaseAuth.getInstance().generateEmailVerificationLink(email);
        return user;
    }

}
