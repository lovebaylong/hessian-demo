package yxw.example.hessiandemoclient.user.openapi;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("openapi/user/account/v1")
@Api(value = "用户账户管理相关接口", tags = {"用户账户"})
public class UserAccountOpenApiController {

    @GetMapping("profile")
    public String profile(@RequestParam(value = "id") Long id) {
        return "SUCCESS";
    }

}
