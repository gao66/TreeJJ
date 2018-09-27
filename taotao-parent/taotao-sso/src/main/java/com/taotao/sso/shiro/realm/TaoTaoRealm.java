package com.taotao.sso.shiro.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;

public class TaoTaoRealm implements Realm {
	
	private String  realmName = "taotaoRealm";
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return realmName;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		if(token instanceof UsernamePasswordToken )
			return true;
		return false; 
	}

	
	
	private TbUserMapper userMapper;
	
	public TbUserMapper getUserMapper() {
		return userMapper;
	}
	
	public void setUserMapper(TbUserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//取出用户名和密码
		UsernamePasswordToken tk = (UsernamePasswordToken)token;
		String username = tk.getUsername();
		String password = new String(tk.getPassword());
		
		//查表
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<TbUser> list = userMapper.selectByExample(example );
		
		//将查出来的数据装配
		TbUser user = list.get(0);
		if(user==null)return null; 
		SimpleAccount account = new SimpleAccount();
		
		
		PrincipalCollection principals= new SimplePrincipalCollection(username, realmName);
		account.setPrincipals(principals);
		Object credentials = password;
		account.setCredentials(credentials);
		return account;
	}

}
