package com.blog.controller;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.payload.JWTAuthResponse;
import com.blog.payload.LoginDto;
import com.blog.payload.SignupDto;
import com.blog.repositary.RoleRepositary;
import com.blog.repositary.UserRepositary;
import com.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepositary userRepositary;
    @Autowired
    private PasswordEncoder PasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepositary roleRepositary;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("/signin")
    //public ResponseEntity<?> authenticateuser(@RequestBody LoginDto loginDto) {
    public ResponseEntity<JWTAuthResponse> authenticateuser(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );//authenticate method compare actual value with expected value
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //get token from tokenProvider
        String token=tokenProvider.generateToken(authentication);
      //  return new ResponseEntity<>("user is signed-in successfully!.",HttpStatus.OK);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signupdto){
        if(userRepositary.existsByEmail(signupdto.getEmail())){
            return new ResponseEntity<>("Email id exists "+signupdto.getEmail(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(userRepositary.existsByUsername(signupdto.getUsername())){
            return new ResponseEntity<>("Username is  exists "+signupdto.getUsername(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user=new User();
        user.setName(signupdto.getName());
        user.setUsername(signupdto.getUsername());
        user.setEmail(signupdto.getEmail());
        user.setPassword(PasswordEncoder.encode(signupdto.getPassword()));
        Role roles=roleRepositary.findByName("ROLE_ADMIN").get();
//        Role roles = roleRepositary.findByName("ROLE_ADMIN")
//                .orElseThrow(() -> new RuntimeException("Role not found: ROLE_ADMIN"));
        user.setRoles(Collections.singleton(roles));
        User saveuser = userRepositary.save(user);
        return new ResponseEntity<>("user registered successfully", HttpStatus.OK);
    }
}
