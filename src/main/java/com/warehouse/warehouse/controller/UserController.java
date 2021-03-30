package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.dto.LoginUserNamePasswordRequest;
import com.warehouse.warehouse.security.UserAccessServiceImpl;
import com.warehouse.warehouse.security.dto.LoginRequest;
import com.warehouse.warehouse.security.dto.UserData;
import com.warehouse.warehouse.security.dto.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserAccessServiceImpl userAccessService;

    @Autowired
    public UserController(UserAccessServiceImpl userAccessService) {
        this.userAccessService = userAccessService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserData> login(@RequestBody LoginUserNamePasswordRequest loginRequest) {
        Optional<UserData> userData = userAccessService.login(new LoginRequest(UserId.from(loginRequest.getUserName()), loginRequest.getPassword()));
        if (userData.isPresent()) {
            return ResponseEntity.ok().body(userData.get());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public String createUser(Authentication authentication){
        return "Name from token "+authentication.getName();
    }


}
