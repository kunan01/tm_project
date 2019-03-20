package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.dto.AppInformationPageViewDto;

public interface AppInformationPageViewService {

	/**
	 * 添加
	 */
	@SuppressWarnings("rawtypes")
	Result save(AppInformationPageViewDto appInformationPageViewDto);

}