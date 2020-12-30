package yxw.example.hessiandemoclient.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import yxw.example.user.entity.User;

import java.util.List;

public interface UserService {
    JsonNode detail(Long id);

    JsonNode list();

    User detail2(Long id);

    List<User> list2();
}
