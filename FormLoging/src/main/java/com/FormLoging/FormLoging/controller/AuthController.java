package com.FormLoging.FormLoging.controller;

import com.FormLoging.FormLoging.entity.Customer;
import com.FormLoging.FormLoging.entity.Role;
import com.FormLoging.FormLoging.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    private  static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/sing")
    public String singUpPage(){
        logger.info("calling this get  sing in method");
        return "sing";
    }
    @PostMapping("/sing")
    public String singUp(@RequestParam String username, @RequestParam String password){
  logger.info("this is logger {} {}",username,password);
        Customer customer=new Customer();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));
        customer.getRoles().add(new Role(2,"USER"));
        customerRepository.save(customer);
        return "redirect:/auth/login?registered";
    }

}
