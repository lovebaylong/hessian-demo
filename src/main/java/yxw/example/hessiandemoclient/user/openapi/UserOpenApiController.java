package yxw.example.hessiandemoclient.user.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yxw.example.hessiandemoclient.user.service.UserService;
import yxw.example.user.entity.User;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("openapi/user/v1")
public class UserOpenApiController {
    private final UserService userService;

    public UserOpenApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("detail")
    public JsonNode detail(@RequestParam(value = "id") Long id) {
        return userService.detail(id);
    }

    @GetMapping("list")
    public JsonNode list() {
        return userService.list();
    }

    @GetMapping("detail2")
    public User detail2(@RequestParam(value = "id") Long id) {
        return userService.detail2(id);
    }

    @GetMapping("list2")
    public List<User> list2() {
        return userService.list2();
    }
}
