package com.summer;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.RoleService;
import com.summer.modules.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummerBootApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRole () {
		Role role = new Role();
		role.setName("ROLE_COMMEN_USER");
		role.setCnName("普通用户");
		roleService.save(role);
		System.out.println("*****************   插入完成    **********************");
	}

	@Test
	public void testRoleList () {
		Page<Role> page = roleService.findPage(new Page<Role>(1, 10), new Role("936ca8afdb0c476687d82ff328385a5e"));
		System.out.println(page);
		System.out.println(page.getRecords());
		System.out.println("*****************   插入完成    **********************");
	}

	@Test
	public void testUser () {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setName("admin");
		List<Role> list = Lists.newArrayList();
		list.add(new Role("936ca8afdb0c476687d82ff328385a5e"));
		user.setRoleList(list);
		userService.save(user);
		System.out.println("*****************   插入完成    **********************");
	}

}
