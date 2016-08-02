package com.prisys.shiro;

//import com.github.zhangkaitao.shiro.chapter15.service.UserService;
import java.util.ArrayList;
import java.util.List;










import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.authc.UserFilter;

import com.prisys.entity.ResFormMap;
import com.prisys.entity.UserFormMap;
import com.prisys.mapper.ResourcesMapper;
import com.prisys.mapper.UserMapper;

/**
 * <p>User: 
 * <p>Date: 
 * <p>Version: 1.0
 */
public class MyCasRealm extends CasRealm {

//    private UserService userService;
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

	
	public MyCasRealm(){
		System.out.println("进入MyCasRealm了----------------------------");
	}
	@Inject
	private ResourcesMapper resourcesMapper;

	@Inject
	private UserMapper userMapper;

	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	//AuthenticatingFilter 
        String username = (String)principals.getPrimaryPrincipal();
        UserFormMap userFormMap = new UserFormMap();
		userFormMap.put("username", "" + username + "");
		//List<UserFormMap> userFormMaps = userMapper.findByNames(userFormMap);
		List<UserFormMap> userFormMaps =new ArrayList<UserFormMap>();
		try{
			 userFormMaps = userMapper.seletUser(userFormMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (userFormMaps.size() != 0) {
			if ("2".equals(userFormMaps.get(0).get("locked"))) {
				throw new LockedAccountException(); // 帐号锁定
			}
			
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			 String userId = SecurityUtils.getSubject().getSession().getAttribute("userSessionId").toString();
		        List<ResFormMap> rs = resourcesMapper.findUserResourcess(userId);
		    	for (ResFormMap resources : rs) {
	    		authorizationInfo.addStringPermission(resources.get("resKey").toString());
		    		//authorizationInfo.addStringPermission(resources.get("resUrl").toString());
				}
			
			// 当验证都通过后，把用户信息放在session里
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("userSession", userFormMaps.get(0));
			session.setAttribute("userSessionId", userFormMaps.get(0).get("id"));
			return authorizationInfo;
		} else {
			throw new UnknownAccountException();// 没找到帐号
		}
        
       
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        
//        String userId = SecurityUtils.getSubject().getSession().getAttribute("userSessionId").toString();
//        List<ResFormMap> rs = resourcesMapper.findUserResourcess(userId);
//    	for (ResFormMap resources : rs) {
//    		authorizationInfo.addStringPermission(resources.get("resKey").toString());
//		}
//        return authorizationInfo;
    	
    
    }
    
   
}
