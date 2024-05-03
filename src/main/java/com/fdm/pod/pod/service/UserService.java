package com.fdm.pod.pod.service;

import com.fdm.pod.pod.exception.EmailTakenException;
import com.fdm.pod.pod.exception.UserDoesNotExistException;
import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.model.RegistrationForm;
import com.fdm.pod.pod.model.Role;
import com.fdm.pod.pod.repository.RoleRepository;
import com.fdm.pod.pod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public ApplicationUser getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public ApplicationUser updateUser(ApplicationUser user){
        try{
            return userRepository.save(user);
        } catch (Exception E){
            throw new EmailTakenException();
        }
    }

    public ApplicationUser registerUser(RegistrationForm form){

        ApplicationUser user = new ApplicationUser();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setDateOfBirth(form.getDOB());

        String name = user.getFirstName() + user.getLastName();

        boolean nameTaken = true;
        String tempName = "";

        while (nameTaken){
            tempName = generateUsername(name);
            if(userRepository.findByUsername(tempName).isEmpty()){
                nameTaken = false;
            }
        }

        user.setUsername(tempName);

        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        user.setAuthorities(roles);

        try {
            return userRepository.save(user);
        } catch (Exception E){
            throw new EmailTakenException();
        }

    }

    public void generateEmailVerification(String username) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        user.setVerification(generateVerificationNumber());
        userRepository.save(user);
    }

    private long generateVerificationNumber() {
        return (long)Math.floor(Math.random() * 1_000_000);
    }

    public String generateUsername(String name){
        long number = (long)Math.floor(Math.random() * 1_000_000);
        return name + number;
    }


    public ApplicationUser setPassword(String username, String password) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
