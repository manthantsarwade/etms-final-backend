package com.etms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etms.pojos.Person;
import com.etms.repository.PersonRepository;


@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

//depcy
  @Autowired
  private PersonRepository personRepository;


  @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Person person = personRepository.findByEmail(email)
    .orElseThrow(() -> new UsernameNotFoundException("Email not found !!!!!"));
        return new CustomUserDetailsImpl(person);
   }
}
