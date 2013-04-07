package com.mcsm.hellapp.admin.model.service;


import com.mcsm.hellapp.admin.model.domain.AdminUser;
import com.mcsm.hellapp.admin.model.domain.BusinessCompany;
import com.mcsm.hellapp.admin.model.domain.BusinessUser;

import java.util.List;

public interface AdminService {

    AdminUser getAdminUser(String email);

    BusinessCompany createPartner(BusinessCompany c);

    BusinessUser createPartnerUser(BusinessUser c);

    List<BusinessCompany> getBusinessCompanies();

    List<BusinessUser> getBusinessUsers();

    List<BusinessUser> getCampignStats();
}
