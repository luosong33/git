package com.jumore.dove.demo.dubbo.service.impl;

import org.springframework.stereotype.Service;

import com.jumore.dove.demo.dubbo.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService{

	@Override
	public String getVersion() {
		return "1.1.1";
	}

}
