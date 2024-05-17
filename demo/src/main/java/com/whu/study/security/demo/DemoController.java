package com.whu.study.security.demo;

import com.whu.study.security.demo.payload.request.LoginRequest;
import com.whu.study.security.demo.payload.response.RestResponse;
import com.whu.study.security.demo.security.jwt.JwtUtils;
import com.whu.study.security.demo.security.services.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/demo/")
public class DemoController {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

// Operations
    /**
     * Login, it will verify user by username and password.
     * @param loginRequest the login request
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<RestResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest) {

        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtUtils.generateTokenFromUser(userDetails.getUsername());
        String refreshToken = jwtUtils.generateRefreshToken(userDetails.getUsername());
        RestResponse restResponse = RestResponse.success()
                .put("accessToken", token)
                .put("refreshToken", refreshToken)
                .put("roleId", roles)
                .put("userId", userDetails.getUsername());

        return ResponseEntity.ok().body(restResponse);
    }

    /**
     * Home, no authentication required.
     * @return ResponseEntity
     */
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("hello");
    }

    /**
     * Secret, authentication required.
     * @return ResponseEntity
     */
    @GetMapping("/secret")
    public ResponseEntity<String> secret() {
        return ResponseEntity.ok().body("Secret");
    }
}
