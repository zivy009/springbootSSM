package com.zivy009.demo.springbootshirodwz.service.impl;

import com.zivy009.demo.springbootshirodwz.persistence.model.ZzDemo;
import com.zivy009.demo.springbootshirodwz.persistence.dao.ZzDemoMapper;
import com.zivy009.demo.springbootshirodwz.service.IZzDemoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Service
public class ZzDemoService extends ServiceImpl<ZzDemoMapper, ZzDemo> implements IZzDemoService {
	
}
