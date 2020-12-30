package yxw.example.hessiandemoclient.user.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户信息")
public class UserPojo {
    @ApiModelProperty(value = "用户名称", required = true)
    private String name;

    @ApiModelProperty(value = "用户年龄", required = true)
    private Integer age;

    @ApiModelProperty(value = "用户身份证号", required = true)
    private String idCardNo;

    @ApiModelProperty(value = "用户电话", required = true)
    private String phone;

    @ApiModelProperty(value = "用户地址", required = true)
    private String address;

    @ApiModelProperty(value = "用户邮箱", required = true)
    private String email;
}
