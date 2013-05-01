package kz.helloapp.model.service;

import kz.helloapp.model.domain.AdminUser;
import kz.helloapp.model.domain.PartnerCompany;
import kz.helloapp.model.domain.PartnerUser;

import java.util.List;


public interface AdminService {

    AdminUser getAdminUser(String email);

    PartnerCompany createPartner(PartnerCompany c);

    PartnerUser createPartnerUser(PartnerUser c);

    List<PartnerCompany> getPartnerCompanies();

    List<PartnerUser> getPartnerUsers();

    List<PartnerUser> getCampaignStats();
}
