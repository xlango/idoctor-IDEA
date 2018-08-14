package com.idoctor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idoctor.dao.UserMapper;
import com.idoctor.pojo.User;
import com.idoctor.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    
	@Resource
	private UserMapper userMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findByIf(User user) {
		System.out.println(user.getName());
		return userMapper.findByIf(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(User user) {
		userMapper.add(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(User user) {
		userMapper.update(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(User user) {
		userMapper.delete(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> pageuser(Integer page, Integer pageSize) {
		//分页查询
        PageHelper.startPage(page, pageSize);
        List<User> list=userMapper.findAll();
        PageInfo pagelist=new PageInfo(list);
        
        System.out.println(pagelist.getList().toString());
		return pagelist.getList();
	}

	@Override
	public void addList(List<User> userList) {
		userMapper.addList(userList);
	}

	@Override
	public void deleteList(int[] ids) {
		userMapper.deleteList(ids);
	}

	@Override
	public void updateList(List<User> userList) {
		userMapper.updateList(userList);;
	}


}
