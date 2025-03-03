package com.example.nexus.Services.interfaces;

import java.util.List;

import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Services.general.CRUDService;

public interface UserCompagneService extends CRUDService<UserCompagne> {
public List<UserCompagne> getAllBySupervisor(User supervisor);
}