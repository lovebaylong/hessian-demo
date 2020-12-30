package yxw.example.hessiandemoclient.user.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import yxw.example.hessiandemoclient.user.pojo.UserPojo;
import yxw.example.hessiandemoclient.user.service.UserService;
import yxw.example.user.entity.User;

import java.util.List;

@RestController
@RequestMapping("openapi/user/v1")
@Api(value = "用户管理相关接口", tags = {"用户"})
public class UserOpenApiController {
    private final UserService userService;

    public UserOpenApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("detail")
    @ApiOperation(value = "根据用户编号获取用户详情，返回单个Json对象", notes = "数据来源于Hessian接口",
            response = ResponseEntity.class,
            responseHeaders = @ResponseHeader(name = "header1", response = String.class),
            authorizations = @Authorization(value = "demoClient_auth", scopes = {@AuthorizationScope(scope = "read:user", description = "用户只读")})
    )
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "未经授权"),
            @ApiResponse(code = 403, message = "权限不足"),
            @ApiResponse(code = 404, message = "用户不存在"),
            @ApiResponse(code = 400, message = "用户已冻结")
    })
    public ResponseEntity<JsonNode> detail(@RequestParam(value = "id") @ApiParam(value = "用户编号", required = true) Long id) {
        if (0L == id) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.detail(id), HttpStatus.OK);
    }

    @GetMapping("list")
    @ApiOperation(value = "获取所有用户列表，返回Json对象数组")
    @ApiResponse(code = 404, message = "用户不存在")
    public JsonNode list() {
        return userService.list();
    }

    @ApiOperation(value = "根据用户编号获取用户详情，返回单个Json对象")
    @GetMapping("detail2")
    public User detail2(@RequestParam(value = "id") Long id) {
        return userService.detail2(id);
    }

    @ApiOperation(value = "获取所有用户列表，返回Json对象数组")
    @GetMapping("list2")
    public List<User> list2() {
        return userService.list2();
    }

    @ApiIgnore
    @PostMapping("delete")
    public String delete(@RequestParam(value = "id") Long id) {
        return "true";
    }

    @PostMapping("add")
    public JsonNode add(@ModelAttribute UserPojo user) {
        return userService.save(user);
    }

    private String del() {
        return null;
    }
}
