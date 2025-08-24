package com.FormLoging.FormLoging.service;

import com.FormLoging.FormLoging.entity.Customer;
import com.FormLoging.FormLoging.entity.Role;
import com.FormLoging.FormLoging.repository.CustomerRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    CustomerRepository  customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        logger.info("this is data {}",customer);
        Customer customer1 = customerRepository.save(customer);
        logger.info("this is  data {}",customer1);
        return customer1;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer  customer = customerRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("No user found by that username"));

        List<GrantedAuthority> list = new ArrayList<>();

        for(Role r :customer.getRoles()){
            list.add(new SimpleGrantedAuthority("ROLE_"+r.getRole()));
        }

        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .authorities(list)
                .build();
    }
}
