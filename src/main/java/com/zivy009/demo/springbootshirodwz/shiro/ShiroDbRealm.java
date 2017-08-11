package com.zivy009.demo.springbootshirodwz.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeException;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysPermissionMapper;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysRoleMapper;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysUserMapper;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysUserRoleMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysUser;

@Service
public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    /**
     * 登录认证
     */
    @Override
    @Transactional(readOnly = true)
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        SysUser sysUser = new SysUser();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String loginName = (String) token.getPrincipal();

        sysUser.setLoginName(loginName);
        sysUser.setDisabled(0);
        sysUser = sysUserMapper.selectOne(sysUser);
        if (sysUser == null) {
            throw new MyRuntimeException("用户不存在");
        } else {
            // 密码加盐处理
            ByteSource credentialsSalt = new Md5Hash(sysUser.getSalt());//
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), credentialsSalt, super.getName());
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
            return simpleAuthenticationInfo;
        }

    }

    /**
     * 授权信息
     */
    @Override
    @Transactional(readOnly = true)
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //定义储存变量
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        // 1.获得这个用户的所有角色
        Set<Integer> roleIdList = sysUserRoleMapper.obtainRoldidBy(sysUser.getId());
        // 有角色就把他的角色和相关权限添加到安全系统中
        if (roleIdList != null && roleIdList.size() > 0) {
            String roleIds = roleIdList.toString().replace("[", "").replaceAll("]", "");
            // 1.1 角色
            roleNameSet = sysRoleMapper.obtainCodeBy(roleIds);
             
            // 1.2 权限
            permissionSet= sysPermissionMapper.obtainPermissionCodeBy(roleIds);

            info.addStringPermissions(permissionSet);
            info.addRoles(roleNameSet);
        }

        System.out.println("Authoriztion........................");
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);

        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
