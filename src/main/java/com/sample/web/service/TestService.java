package com.sample.web.service;

import com.sample.web.dto.TestDTO;

public interface TestService {

    Double service(TestDTO testDTO) throws Exception;

    Integer save(TestDTO testDTO) throws Exception;
}
