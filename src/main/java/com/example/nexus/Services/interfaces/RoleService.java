package com.example.nexus.Services.interfaces;

import java.util.List;

import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Entitie.Role;
import com.example.nexus.Services.general.CRUDService;

public interface RoleService extends CRUDService<Role> {

List<Privilege> getRolePrivileges(Long roleId);
void addPrivilegeToRole(Long roleId, Long privilegeId);
void removePrivilegeFromRole(Long roleId, Long privilegeId); 

}