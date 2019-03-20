package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppCollectDto;

public interface AppCollectService {

	@SuppressWarnings("rawtypes")
	Result save(AppCollectDto collectDto);
	@SuppressWarnings("rawtypes")
	Result delete(AppCollectDto collectDto);
	@SuppressWarnings("rawtypes")
	Result selectById(AppCollectDto collectDto);

}