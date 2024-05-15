package com.BoardPractice.BoardPractice.requestmapping;


import org.springframework.web.bind.annotation.*;

@RestController
// Mapping 부분에 /mapping/users 가 중복되므로, @RequestMapping("/mapping/users") 해놓으면 중복 최소화.
public class MappingClassController {

    @GetMapping("/mapping/users")
    public String user() {
        return "get users";
    }

    @PostMapping("/mapping/users")
    public String addUser() {
        return "post user";
    }

    @GetMapping("/mapping/users/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId = "+userId;
    }

    @PatchMapping("/mapping/users/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "get userId = "+userId;
    }

    @DeleteMapping("/mapping/users/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete7 userId = "+userId;
    }
}
