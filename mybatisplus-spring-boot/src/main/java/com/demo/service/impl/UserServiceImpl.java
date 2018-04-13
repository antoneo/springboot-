package com.demo.service.impl;

import java.util.List;

import com.demo.entity.User;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demo.mapper.UserMapper;
import com.demo.service.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

}