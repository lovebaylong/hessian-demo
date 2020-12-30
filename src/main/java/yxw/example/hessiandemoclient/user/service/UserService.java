package yxw.example.hessiandemoclient.user.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface UserService {
    JsonNode detail(Long id);

    JsonNode list();
}
