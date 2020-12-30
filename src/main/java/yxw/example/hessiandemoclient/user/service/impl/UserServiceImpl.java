package yxw.example.hessiandemoclient.user.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import yxw.example.hessiandemoclient.user.pojo.UserPojo;
import yxw.example.hessiandemoclient.user.service.UserService;
import yxw.example.user.entity.User;
import yxw.example.user.remoting.UserRemoting;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRemoting userRemoting;
    private static ObjectMapper mapper = new ObjectMapper();

    public UserServiceImpl(UserRemoting userRemoting) {
        this.userRemoting = userRemoting;
    }

    @Override
    public JsonNode detail(Long id) {
        User user = userRemoting.findById(id);
        if (null == user) {
            return defaultErrorNode();
        }
        return mapper.convertValue(user, JsonNode.class);
    }

    @Override
    public JsonNode list() {
        List<User> userList = userRemoting.findAll();
        if (CollectionUtils.isEmpty(userList)) {
            return defaultErrorNode();
        }
        ArrayNode arrayNode = mapper.createArrayNode();
        for (User user : userList) {
            arrayNode.add(mapper.convertValue(user, JsonNode.class));
        }
        return arrayNode;
    }

    @Override
    public User detail2(Long id) {
        return userRemoting.findById(id);
    }

    @Override
    public List<User> list2() {
        return userRemoting.findAll();
    }

    @Override
    public JsonNode save(UserPojo user) {
        User userDto = new User();
        BeanUtils.copyProperties(user, userDto);
        log.info("保存用户信息，用户信息: {}", user.toString());
        userRemoting.save(userDto);
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("errcode", 0);
        return objectNode;
    }

    private JsonNode defaultErrorNode() {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("errcode", 1);
        objectNode.put("errmsg", "data is not existed, or deleted");
        return objectNode;
    }
}
