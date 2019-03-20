package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.PermissionDao;
import com.tangmo.yiliao.dao.UserDao;
import com.tangmo.yiliao.entity.Module;
import com.tangmo.yiliao.entity.Permission;
import com.tangmo.yiliao.entity.Role;
import com.tangmo.yiliao.service.PermissionService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import com.tangmo.yiliao.utility.util.PinyinTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private UserDao userDao;

    @Override
    public Result getPremAll() {
        List<Permission> permissionList = permissionDao.getPremAll();
        if(permissionList != null && permissionList.size() != 0){
            for (int i = 0;i < permissionList.size(); i++){
                if(permissionList.get(i).getModuleId() != null){
                    String[] moIds = permissionList.get(i).getModuleId().split(",");
                    if(moIds.length != 0){
                        List<Module> modules = new ArrayList<>();
                        for (int j = 0;j < moIds.length; j++){
                            Module module = userDao.selectModuleByModuleId(moIds[j]);
                            if(module != null){
                                modules.add(module);
                            }
                        }
                        permissionList.get(i).setModuleList(modules);
                    }
                }
            }
            return ResultUtil.success(permissionList);
        }
        return ResultUtil.error("数据为空");
    }

    @Override
    public Result getRoleAll() {
        List<Role> roleList = permissionDao.getRoleAll();
        if(roleList.size() != 0){
            for(int i = 0; i<roleList.size(); i++){
                String str = roleList.get(i).getPermissionsId();
                if(str != null && !str.equals("")){
                    String[] prems = roleList.get(i).getPermissionsId().split(",");
                    if(prems.length != 0){
                        List<Permission> permissions = new ArrayList<>();
                        for (int j = 0;j < prems.length; j++){
                            Permission permission = permissionDao.getPremById(prems[j]);
                            if(permission != null){
                                permissions.add(permission);
                            }
                        }
                        roleList.get(i).setPermissionList(permissions);
                    }
                }
            }
            return ResultUtil.success(roleList);
        }
        return ResultUtil.error("数据为空");
    }

    @Override
    @Transactional
    public Result delPremById(String permId,String userId) {
        if(permId == null || permId.equals("") || userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        if(permissionDao.getRoleCountByPermId(permId) != 0){
            return ResultUtil.error("当前权限有角色正在使用，请处理后再试！");
        }
        permissionDao.delPermById(permId,EncryptUtil.get32Uuid(),userId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result delRoleById(String roleId, String userId) {
        if(roleId == null || roleId.equals("") || userId == null || userId.equals("")){
            return ResultUtil.paramError();
        }
        if(permissionDao.getUserCountByRoleId(roleId) != 0){
            return ResultUtil.error("当前角色信息使用，请处理后再试！");
        }
        //删除
        permissionDao.delRoleById(roleId, EncryptUtil.get32Uuid(),userId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updPremById(String permId, String permName, String userId, String mId) {
        if(permId == null || permName == null || userId == null){
            return ResultUtil.paramError();
        }
        if(mId.equals("") || mId == null){
            mId = null;
        }else{
            mId = mId.replaceAll("_",",");
        }
        permissionDao.updPremById(permId,permName,userId,mId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updRoleById(String roleId, String roleName, String userId, String mId) {
        if(roleId == null || roleName == null || userId == null){
            return ResultUtil.paramError();
        }
        if(mId.equals("") || mId == null){
            mId = null;
        }else{
            mId = mId.replaceAll("_",",");
        }
        permissionDao.updRoleById(roleId,roleName,userId,mId);
        return ResultUtil.success();
    }

    @Override
    public Result getRoleTwoAll(String id) {
        List<Permission> permissionList = permissionDao.getPremAll();
        String pId = userDao.selectPermissionsByRoleId(id);
        if(permissionList != null){
            for (int i = 0;i < permissionList.size();i++){
                int row = 0;
                if(pId != null && !pId.equals("")){
                    String[] pIds = pId.split(",");
                    for (int j = 0;j<pIds.length;j++){
                        if(permissionList.get(i).getPermissionsId().equals(pIds[j])){
                            permissionList.get(i).setState(Byte.parseByte("0"));
                            row = 1;
                            break;
                        }
                    }
                }
                if(row == 0){
                    permissionList.get(i).setState(Byte.parseByte("1"));
                }
            }
        }
        return ResultUtil.success(permissionList);
    }

    @Override
    @Transactional
    public Result addPrem(Permission permission) {
        if(permission.getPermissionsName() == null || permission.getPermissionsName().equals("") || permission.getCreateUserId() == null || permission.getCreateUserId().equals("")){
            return ResultUtil.paramError();
        }
        if(permission.getModuleId().equals("") || permission.getModuleId() == null){
            permission.setModuleId(null);
        }else{
            permission.setModuleId(permission.getModuleId().replaceAll("_",","));
        }
        PinyinTool tool = new PinyinTool();
        permission.setPermissionsId(tool.toPinYin(permission.getPermissionsName(), "", PinyinTool.Type.UPPERCASE));
        try {
            permissionDao.addPrem(permission);
        }catch (Exception e){
            return ResultUtil.error("当前权限名称生成的权限标识重复,请更换后在试！");
        }
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addRole(Role role) {
        if(role.getRoleName() == null || role.getCreateUserId() == null){
            return ResultUtil.paramError();
        }
        if(role.getPermissionsId().equals("") || role.getPermissionsId() == null){
            role.setPermissionsId(null);
        }else{
            role.setPermissionsId(role.getPermissionsId().replaceAll("_",","));
        }
        PinyinTool tool = new PinyinTool();
        role.setRoleId(tool.toPinYin(role.getRoleName(), "", PinyinTool.Type.UPPERCASE));
        try {
            permissionDao.addRole(role);
        }catch (Exception e){
            return ResultUtil.error("当前角色名称生成的角色标识重复,请更换后在试！");
        }
        return ResultUtil.success();
    }
}
