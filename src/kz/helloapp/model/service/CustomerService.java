package kz.helloapp.model.service;

import kz.helloapp.model.domain.Campaign;
import kz.helloapp.model.domain.CampaignStat;
import kz.helloapp.model.domain.PartnerConfirmer;
import kz.helloapp.model.domain.CustomerUser;

import java.util.List;

public interface CustomerService {


    List<Campaign> getAllCampaigns();

    CustomerUser getUser(long id);

    Campaign getCampaign(long id);


    PartnerConfirmer getConfirmer(String code);

    CampaignStat saveCampaignStat(CampaignStat stat);



}
