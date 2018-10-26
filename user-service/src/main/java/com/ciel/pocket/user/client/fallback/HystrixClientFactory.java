package com.ciel.pocket.user.client.fallback;

import com.ciel.pocket.infrastructure.dto.web.ReturnModel;
import com.ciel.pocket.user.client.AuthServiceClient;
import com.ciel.pocket.user.dto.input.CreateUser;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cielqian
 * @email qianhong91@outlook.com
 * @date 2018/10/24 15:35
 */
@Component
public class HystrixClientFactory implements FallbackFactory<AuthServiceClient> {
    @Override
    public AuthServiceClient create(Throwable throwable) {
        System.out.println(throwable);

        return new AuthServiceClientWithFactory() {
            @Override
            public ReturnModel<Long> createUser(CreateUser user) {
                return null;
            }

            @Override
            public ReturnModel deleteUser(String username) {
                return null;
            }
        };
    }
}