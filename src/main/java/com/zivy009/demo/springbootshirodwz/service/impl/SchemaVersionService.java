package com.zivy009.demo.springbootshirodwz.service.impl;

import com.zivy009.demo.springbootshirodwz.persistence.model.SchemaVersion;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SchemaVersionMapper;
import com.zivy009.demo.springbootshirodwz.service.ISchemaVersionService;
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
public class SchemaVersionService extends ServiceImpl<SchemaVersionMapper, SchemaVersion> implements ISchemaVersionService {
	
}
