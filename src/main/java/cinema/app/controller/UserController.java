package cinema.app.controller;

import cinema.app.dto.response.UserResponseDto;
import cinema.app.exception.DataProcessingException;
import cinema.app.model.User;
import cinema.app.service.UserService;
import cinema.app.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(
                () -> new DataProcessingException("User with email " + email + " not found"));
        return userMapper.mapToDto(user);
    }
}
