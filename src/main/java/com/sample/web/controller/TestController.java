package com.sample.web.controller;

import com.sample.web.dto.AjaxResult;
import com.sample.web.dto.TestDTO;
import com.sample.web.dto.common.Result;
import com.sample.web.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Api(tags = "规范测试")
@RestController
@RequestMapping("/pretty")
public class TestController {

    private TestService testService;

    @PostMapping("test-validation")
    public void testValidation(@RequestBody @Validated TestDTO dto) throws Exception {
        this.testService.save(dto);
    }

    @PostMapping("/test")
    public Double test(@RequestBody TestDTO testDTO) {
        try {
            return this.testService.service(testDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{num}")
    public Integer detail(@PathVariable("num") @Min(1) @Max(20) Integer num) {
        return num * num;
    }

    @GetMapping("/getByEmail")
    public TestDTO getByAccount(@RequestParam @NotBlank @Email String email) {
        TestDTO testDTO = new TestDTO();
        testDTO.setEmail(email);
        return testDTO;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

}