package com.idoctor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.idoctor.pojo.JSONResult;
import com.idoctor.pojo.User;
import com.idoctor.service.impl.UserServiceImpl;
import com.idoctor.utils.RedisTemplateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;


@RestController   //@RestController =@Controller+@ResponseBody
@RequestMapping("/user")
@Api(value="用户接口",tags= {"用户API"})
public class UserController {
	
	@Resource
	private UserServiceImpl userService;
	
    @Resource
    private RedisTemplateUtil redisTemplateUtil;

	
	String string = "2016-10-24 21:59:06";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
	@RequestMapping("/getuser")
	@ApiOperation(value = "获取一个用户json", httpMethod = "GET", notes = "获取一个用户json")
	public JSONResult getuser() throws ParseException {	
		User user=new User();
		user.setName(null);
		user.setPassword("123");
		user.setBirthday(string);
		return JSONResult.ok(user);
	}
	
	@RequestMapping("/all")
	@ApiOperation(value = "获取所有用户", httpMethod = "GET", notes = "获取所有用户")
	public JSONResult getAllUser() {
/*		for(User user:userService.findAll()) {
			System.out.println(user.getName());
		}*/
		
		return JSONResult.ok(userService.findAll());
	}
	
	@RequestMapping("/pageall")
	@ApiOperation(value = "分页获取用户", httpMethod = "GET", notes = "分页获取用户")
	public JSONResult getPageAllUser(Integer page, Integer pageSize) {		
		return JSONResult.ok(userService.pageuser(page, pageSize));
	}
	
	@RequestMapping("/add")
	@ApiOperation(value = "添加用户", httpMethod = "POST", notes = "添加用户")
	public JSONResult add(User user) {
		userService.add(user);
		return JSONResult.ok();
	}
	
	@RequestMapping("/update")
	@ApiOperation(value = "更新用户", httpMethod = "POST", notes = "更新用户")
	public JSONResult update(User user){
		userService.update(user);
		return JSONResult.ok();
	}
	
	@RequestMapping("/findByIf")
	@ApiOperation(value = "通过条件获取用户", httpMethod = "POST", notes = "通过条件获取用户")
	public JSONResult findByIf(User user){
		/*User user=new User();
		user.setName(name);
		
		System.out.println(userService.findbyName(user).getBirthday());*/
		return JSONResult.ok(userService.findByIf(user));
	}
	
	@RequestMapping("/delete")
	@ApiOperation(value = "删除用户", httpMethod = "POST", notes = "删除用户")
	public JSONResult delete(int id){
		User user=new User();
		user.setId(id);

		userService.delete(user);
		return JSONResult.ok();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/redis")
	@ApiOperation(value = "用户缓存", httpMethod = "GET", notes = "用户缓存")
	public JSONResult redis() {		 
		List<User> userList = (List<User>) redisTemplateUtil.getList("userlist");
		System.out.println(userList);
        if(userList==null) {
            System.out.println("还没有缓存，将从数据库中查询。。。");
            userList = userService.findAll();
            redisTemplateUtil.setList("userlist", userList);
        }else {
            System.out.println("已经有缓存了。。。");
        }
        return JSONResult.ok(userList);
	}
	
	/**
	 * 批量插入
	 * @param userlist
	 * @return
	 */
	@RequestMapping("/addlist")
	@ApiOperation(value = "批量插入用戶", httpMethod = "POST", notes = "批量插入用戶")
	public JSONResult addList(String  userlist) {
		/*User user1=new User();
		user1.setName("11120");
		user1.setPassword("11120");
		user1.setAge(22);
		user1.setBirthday("2018-02-02 00:00:00");
		user1.setDescation("11120");
		
		User user2=new User();
		user2.setName("11121");
		user2.setPassword("11121");
		user2.setAge(22);
		user2.setBirthday("2018-02-02 00:00:00");
		user2.setDescation("11121");
		
		User user3=new User();
		user3.setName("11122");
		user3.setPassword("11122");
		user3.setAge(22);
		user3.setBirthday("2018-02-02 00:00:00");
		user3.setDescation("11122");
		
		List<User> userList=new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);*/
		
		
		JSONArray jsonobject = JSONArray.fromObject(userlist);
		List<User> users= (List<User>)JSONArray.toCollection(jsonobject, User.class);
		System.out.println(users.get(0).getName());
		userService.addList(users);
		return JSONResult.ok();
	}
	
	@RequestMapping("/deleteList")
	@ApiOperation(value = "批量删除用戶", httpMethod = "POST", notes = "批量删除用戶")
	public JSONResult deleteList(int[] id) {					
		userService.deleteList(id);
		return JSONResult.ok();
	}
	
	/**
	 * 批量更新
	 * @param userlist
	 * @return
	 */
	@RequestMapping("/updatelist")
	@ApiOperation(value = "批量更新用戶", httpMethod = "POST", notes = "批量更新用戶")
	public JSONResult updateList() {
		User user1=new User();
		user1.setId(18);
		user1.setName("1112099");
		user1.setPassword("1112099");
		user1.setBirthday("2018-02-02 00:00:00");
		user1.setDescation("1112099");
		
		User user2=new User();
		user2.setId(17);
		user2.setName("1112199");
		user2.setPassword("1112199");
		user2.setBirthday("2018-02-02 00:00:00");
		user2.setDescation("1112199");
		
		User user3=new User();
		user3.setId(16);
		user3.setName("1112299");
		user3.setPassword("1112299");
		user3.setBirthday("2018-02-02 00:00:00");
		user3.setDescation("1112299");
		
		List<User> userList=new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
		
		/*JSONArray jsonobject = JSONArray.fromObject(userlist);
		List<User> users= (List<User>)JSONArray.toCollection(jsonobject, User.class);
		System.out.println(users.get(0).getName());*/
		userService.updateList(userList);
		return JSONResult.ok();
	}
	
	@RequestMapping("/testView")
	@ApiOperation(value = "跳转主页面", httpMethod = "GET", notes = "跳转主页面")
	public ModelAndView  rdTest() {
		ModelAndView mv = new ModelAndView("index"); 
		return mv;
	}
}
