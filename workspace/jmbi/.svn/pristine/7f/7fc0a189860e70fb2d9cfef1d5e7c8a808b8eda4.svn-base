package com.jumore.jmbi.service.impl.permission;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.jumore.dove.common.audit.OperationLogBuilder;
import com.jumore.dove.common.audit.OperationLogParam;
import com.jumore.dove.common.audit.OperationType;
import com.jumore.dove.plugin.Page;
import com.jumore.dove.service.BaseServiceImpl;
import com.jumore.dove.util.MD5;
import com.jumore.dove.util.ParamMap;
import com.jumore.jmbi.common.exceptions.BizException;
import com.jumore.jmbi.common.util.ParamUtil;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.enums.BaseExceptionEnum;
import com.jumore.jmbi.model.param.permission.AccountQueryParam;
import com.jumore.jmbi.model.param.permission.PasswordParam;
import com.jumore.jmbi.model.vo.PageResult;
import com.jumore.jmbi.service.permission.AccountService;

/**
 *
 * @author:zhuwei
 * @since: 2016年7月1日 上午11:31:39
 * @history:
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl implements
		AccountService {

	@Override
	public boolean updatePassword(PasswordParam param, boolean isCheckPwd)
			throws BizException {
		if (isCheckPwd) {
			Account accountDB = get(Account.class, param.getId());
			if (!accountDB.getPassword()
					.equals(MD5.md5(param.getOldPassword()))) {
				throw new BizException(BaseExceptionEnum.OLD_PASSWORD_ERROR);
			}
		}
		Account account = new Account();
		String pwdMD5 = MD5.md5(param.getNewPassword());
		account.setPassword(pwdMD5);
		account.setId(param.getId());
		update(account);

		// 日志
		OperationLogBuilder.get(OperationType.other)
				.set(OperationLogParam.userId, param.getId().longValue())
				.set(OperationLogParam.remark, "修改用户【" + param.getId() + "】密码")
				.save();
		return true;
	}
	
	@Override
	public boolean reSetPassword(Integer id,Account accountAdmin)
			throws BizException {
		Account account = get(Account.class, id);
		String pwdMD5 = MD5.md5("123456");
		account.setPassword(pwdMD5);
//		account.setId(id);
		update(account);
		// 日志
		OperationLogBuilder.get(OperationType.other)
				.set(OperationLogParam.userId, accountAdmin.getId().longValue())
				.set(OperationLogParam.remark, "修改用户【" + id + "】密码")
				.save();
		return true;
	}

	@Override
	public PageResult<Account> queryPage(AccountQueryParam queryParam) {
		Page<Account> page = ParamUtil.getPage(Account.class, queryParam);
		ParamMap paramMap = ParamUtil.getParamMap(queryParam);
		Page<Account> result = findPageByParams(Account.class, page,
				".AccountMapper.selectByNoContent", paramMap);
	 
		PageResult<Account> response = new PageResult<Account>(result.result,
				result.getTotalResult());
		return response;
	}

	@Override
	public void add(Account account) throws BizException {
		account.setCreateTime(new Date());
		String pwdMD5 = MD5.md5("123456");
		account.setPassword(pwdMD5);
		commonDao.save(account);
	}

	@Override
	public boolean deleteById(Integer id) {
		Account account = get(Account.class, id);
		account.setIsDel(1);
		update(account);
		return true;
	}

	@Override
	public boolean setStatus(Integer id, int status) {
		Account account = get(Account.class, id);
		if (0 == status) {
			status = 1;
//			account.setPublicDate(new Date());
		} else if (1 == status) {
			status = 0;
		}
		account.setStatus(status);
		update(account);
		return true;
	}

}
