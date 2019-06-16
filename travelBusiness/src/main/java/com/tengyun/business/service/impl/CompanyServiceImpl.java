package com.tengyun.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tengyun.business.dao.CompanyMapper;
import com.tengyun.business.entity.Company;
import com.tengyun.business.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司(Company)表服务实现类
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:21
 */
@Service("companyService")
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}