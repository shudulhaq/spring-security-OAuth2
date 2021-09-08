package com.infosys.oauth2login.config;

import com.infosys.oauth2login.dao.UserDatabase;
import com.infosys.oauth2login.model.UserHaq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDatabase userDatabase;

    @Override
    public UserHelper loadUserByUsername(final String username) throws UsernameNotFoundException{
        UserHaq userHaq = null;
        try {
            userHaq = userDatabase.getUserDetails(username);
            UserHelper userDetail = new UserHelper(userHaq);
            return userDetail;
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + "was not found in the database");
        }
    }
}
