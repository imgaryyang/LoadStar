package com.ciel.pocket.user.client;

import com.ciel.pocket.infrastructure.dto.web.ReturnModel;
import com.ciel.pocket.user.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/api/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnModel<String> createUser(User user);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/users/{username}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnModel deleteUser(@PathVariable("username") String username);
}
