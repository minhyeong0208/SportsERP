package acorn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import acorn.shop.model.User;
import acorn.shop.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByCustomerId(username);
        if (user != null) {
            return bCryptPasswordEncoder.matches(password, user.getCustomerPassword());
        }
        return false;
    }
}
