package com.service.user.services;

import com.service.user.VO.Department;
import com.service.user.VO.ResponseTemplateVO;
import com.service.user.entities.User;
import com.service.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("UserService save");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department = restTemplate.getForObject("http://DEPT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
//        Mono<Department> department = client
//                .get()
//                .uri("http://DEPT-SERVICE/departments/" + user.getDepartmentId())
//                .retrieve()
//                .bodyToMono(Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
