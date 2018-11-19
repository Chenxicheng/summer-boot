package com.summer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.summer.commen.constant.CommenConstant;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.PermissionService;
import com.summer.modules.sys.service.RoleService;
import com.summer.modules.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummerBootApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRole () {
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		role.setCnName("超级管理员");
		roleService.insert(role);
		System.out.println("*****************   插入完成    **********************");
	}

	@Test
	public void testUserList () {
		User list = userService.getByUsername("admin");
		System.out.println(list);
	}

	@Test
	public void testRoleList () {
		Page<Role> page = roleService.findPage(new Page<Role>(1, 10), new Role("1"));
		System.out.println(page);
		System.out.println(page.getRecords());
		System.out.println("*****************   插入完成    **********************");
	}

	@Test
	public void testUser () {
		User user = new User();
		user.setUsername("test123456");
		user.setPassword("123456");
		user.setName("test");
//		List<Role> list = Lists.newArrayList();
//		list.add(new Role("5746e24e182544fa8f2483bb8c8843cc"));
//		user.setRoleList(list);
		userService.insert(user);
		System.out.println("*****************   插入完成    **********************");
	}

	@Test
	public void testPermission () {
		Permission permission = new Permission();
		permission.setParentId("0");
		permission.setTitle("系统管理");
		permission.setType(CommenConstant.PERMISSION_TYPE_PAGE);
		permission.setIcon("ios-book");
		permission.setName("sysMain");
		permission.setLevel(CommenConstant.PERMISSION_LEVEL_ONE);
		permission.setSort(10);
		permission.setPath("/sysManage");
		permission.setComponent("sysManage");
		permissionService.insert(permission);
		System.out.println("*****************   插入完成    **********************");
	}

    @Test
    public void testPermissionCh () {
	    Permission parent = permissionService.get("571152ea4b0546c78552b62fc2f984f7");

        Permission permission = new Permission();
        permission.setParentId(parent.getId());
        permission.setParentIds(parent.getParentIds());
        permission.setTitle("角色管理");
        permission.setType(CommenConstant.PERMISSION_TYPE_PAGE);
        permission.setIcon("ios-book");
        permission.setName("role-manage");
        permission.setStatus(CommenConstant.COMMEN_STATUS_USING);
        permission.setLevel(CommenConstant.PERMISSION_LEVEL_ONE);
        permission.setSort(10);
        permission.setPath("role-manage");
        permission.setComponent("@/sys/role-manage/roleManageList");
        permissionService.insert(permission);
        System.out.println("*****************   插入完成    **********************");
    }

	@Test
	public void testPermissionRole() {

	    Role role = new Role("5746e24e182544fa8f2483bb8c8843cc");
	    List<String> pIdList = Lists.newArrayList("ed2b75c3da2a49f29aa6518bc2b95eb7");

	    roleService.accessPermission(role, pIdList);
    }

	@Test
	public void testPermissionList() {
		List<Permission> list = permissionService.findMenuByUserId("1");
		JSONObject json = new JSONObject();
		json.put("list", list);
		System.out.println(json);
	}

	@Test
	public void testRedis () {
		stringRedisTemplate.delete("permission::userMenuList:1");
	}

}
