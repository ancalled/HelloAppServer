package kz.helloapp.model.service;

import kz.helloapp.model.domain.*;

import java.util.List;


public interface AdminService {

    AdminUser getAdminUser(String email);

    PartnerCompany createPartner(PartnerCompany c);

    PartnerUser createPartnerUser(PartnerUser c);

    List<PartnerCompany> getPartnerCompanies();

    List<PartnerUser> getPartnerUsers();

    List<PartnerUser> getPartnerUsers(long partnerId);

    List<PartnerUser> getCampaignStats();

    PartnerCompany removePartner(long partnerId);

    List<Campaign>getCampaigns();

    PartnerConfirmer getConfirmer(long id);

    List<PartnerConfirmer> getConfirmers(long partnerId);

}
