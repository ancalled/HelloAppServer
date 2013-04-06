package com.mcsm.hellapp.business.model.service;

import com.mcsm.hellapp.business.model.domain.BusinessCompany;
import com.mcsm.hellapp.business.model.domain.BusinessUser;
import com.mcsm.hellapp.business.model.domain.Campaign;
import com.mcsm.hellapp.business.model.domain.CampaignStat;

import java.util.Date;
import java.util.List;

public interface BusinessService {

    Campaign addCampaign(Campaign campaign);

    List<Campaign> getDiscountsByCompany(long companyId);

    List<CampaignStat> getStatsByCompany(long companyId);

    List<CampaignStat> getStatsByDiscountId(long discountId);

    List<CampaignStat> getStatsByDiscountId(long discountId, Date from, Date to);

    BusinessUser getBusinessUser(String email);

    BusinessCompany getBusinessCompany(long id);

}
