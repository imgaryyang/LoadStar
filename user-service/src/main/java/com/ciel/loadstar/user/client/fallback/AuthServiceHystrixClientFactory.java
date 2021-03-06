package com.ciel.loadstar.user.client.fallback;

import com.ciel.loadstar.infrastructure.dto.web.ReturnModel;
import com.ciel.loadstar.user.dto.input.CreateUser;
import com.ciel.loadstar.user.client.AuthServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cielqian
 * @email qianhong91@outlook.com
 * @date 2018/10/24 15:35
 */
@Component
public class AuthServiceHystrixClientFactory implements FallbackFactory<AuthServiceClient>
{
    @Override
    public AuthServiceClient create(Throwable throwable) {
//        throw new FriendlyException("请稍后重试");

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
