package com.goosen.commons.shiro;

import com.goosen.commons.service.UserService;
import com.goosen.commons.shiro.factory.IShiro;
import com.goosen.commons.shiro.factory.ShiroFactroy;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.EncryUtil;
//import com.stylefeng.guns.core.util.ToolUtil;
import com.goosen.commons.model.po.User;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

public class ShiroDbRealm extends AuthorizingRealm {
	
	@Resource
//	@Autowired
	private IShiro iShiro;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
//        IShiro shiroFactory = ShiroFactroy.me();
//    	IShiro shiroFactory = new ShiroFactroy();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = iShiro.user(token.getUsername());
        ShiroUser shiroUser = iShiro.shiroUser(user);
//        SimpleAuthenticationInfo info = iShiro.info(shiroUser, user, super.getName());
//        return info;
        
        String credentials = EncryUtil.encodeByMD5("123456");//user.getPassword();
        // 密码加盐处理
        String source = "8l9ws";//user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        String realmName = super.getName();
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        IShiro shiroFactory = ShiroFactroy.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<String> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

//        for (Integer roleId : roleList) {
//            List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
//            if (permissions != null) {
//                for (String permission : permissions) {
//                    if (ToolUtil.isNotEmpty(permission)) {
//                        permissionSet.add(permission);
//                    }
//                }
//            }
//            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
//            roleNameSet.add(roleName);
//        }
        for (String roleId : roleList) {
            List<String> permissions = iShiro.findPermissionsByRoleId(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (!CommonUtil.isTrimNull(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            String roleName = iShiro.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

    /**
     * 设置认证加密方式
     */
//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
//        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
//        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
//        super.setCredentialsMatcher(md5CredentialsMatcher);
//    }
}
