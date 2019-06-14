package com.tengyun.business.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tengyun.entity.ResponseDTO;
import com.tengyun.business.test.entity.Test;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mutidatasource", fallback = TestUserClient.TestUserClientFallback.class)
public interface TestUserClient {

    @GetMapping("/testUser/list")
    ResponseDTO<Page<Test>> listTestUser();

    @Component
    static class TestUserClientFallback implements TestUserClient {

        @Override
        public ResponseDTO<Page<Test>> listTestUser() {
            return null;
        }
    }
}
