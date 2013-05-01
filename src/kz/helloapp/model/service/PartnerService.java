package kz.helloapp.model.service;


import kz.helloapp.model.domain.*;

import java.util.Date;
import java.util.List;

public interface PartnerService {

    Campaign addCampaign(Campaign campaign);

    List<Campaign> getCampaignsByCompany(long companyId);

    List<CampaignStat> getStatsByCompany(long companyId);

    List<CampaignStat> getStatsByCampaignId(long campaignId);

    List<CampaignStat> getStatsByCampaignId(long campaignId, Date from, Date to);

    PartnerUser getPartnerUser(String email);

    PartnerCompany getPartnerCompany(long id);


    PartnerConfirmer addConfirmer(PartnerConfirmer confirmer);

    PartnerConfirmer getConfirmer(long id);


}
