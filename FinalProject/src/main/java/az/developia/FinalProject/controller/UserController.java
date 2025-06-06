package az.developia.FinalProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import az.developia.FinalProject.entity.UserEntity;
import az.developia.FinalProject.jwt.SecurityUtil;
import az.developia.FinalProject.request.UserLoginRequest;
import az.developia.FinalProject.service.UserService;

import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")

public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public void register(@RequestBody @Valid UserEntity userEntity) {
		userService.register(userEntity);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest userLoginRequest) {
		String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());

		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {

		String header = request.getHeader("Authorization");
		String token = header.substring(7);
		userService.logout(token);
	}

	@GetMapping("/me")
	public UserEntity me() {
		String username = SecurityUtil.getCurrentUsername();
		return userService.getUserInfo(username);
	}
}