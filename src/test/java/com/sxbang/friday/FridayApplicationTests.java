package com.sxbang.friday;

import com.sxbang.friday.dao.UserDao;
import com.sxbang.friday.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FridayApplicationTests {

    @Resource
    UserDao dao ;
    @Test
    public void contextLoads() {
//        SysUser user = new SysUser();
//        user.setUsername("alex-s");
//        user.setPassword(new BCryptPasswordEncoder().encode("alex-s"));
//        user.setStatus(1);
//        dao.save(user);
        dao.changePassword(new Long(1), new BCryptPasswordEncoder().encode("admin"));
    }

}
