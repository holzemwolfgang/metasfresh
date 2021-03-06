package de.metas.pricing.rules;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.math.BigDecimal;
import java.util.List;

import org.adempiere.bpartner.service.IBPartnerBL;
import org.adempiere.mm.attributes.api.IAttributeDAO;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceAware;
import org.adempiere.mm.attributes.api.IAttributeSetInstanceAwareFactoryService;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Services;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_AttributeInstance;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;

import de.metas.logging.LogManager;
import de.metas.pricing.IPricingContext;
import de.metas.pricing.IPricingResult;
import de.metas.pricing.conditions.service.CalculatePricingConditionsRequest;
import de.metas.pricing.conditions.service.CalculatePricingConditionsResult;
import de.metas.pricing.conditions.service.IPricingConditionsService;

/**
 * Discount Calculations
 *
 * @author Jorg Janke
 * @author tobi42 metas us1064
 *         <li>calculateDiscount only calculates (retrieves) the discount, but does not alter priceStd.
 *         <li>Therefore, <code>m_PriceStd</code> is not changed from its
 *         respective productPrice.
 * @author Teo Sarca - refactory
 *
 * @version $Id: MProductPricing.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class Discount implements IPricingRule

{
	private final transient Logger log = LogManager.getLogger(getClass());

	@Override
	public boolean applies(final IPricingContext pricingCtx, final IPricingResult result)
	{
		if (!result.isCalculated())
		{
			log.debug("Cannot apply discount if the price was not calculated - {}", result);
			return false;
		}

		if (result.isDisallowDiscount())
		{
			return false;
		}

		if (pricingCtx.getC_BPartner_ID() <= 0)
		{
			return false;
		}

		if (pricingCtx.getM_Product_ID() <= 0)
		{
			return false;
		}

		return true;
	}

	@Override
	public void calculate(final IPricingContext pricingCtx, final IPricingResult result)
	{
		if (!applies(pricingCtx, result))
		{
			return;
		}

		//
		final int bpartnerId = pricingCtx.getC_BPartner_ID();
		final boolean isSOTrx = pricingCtx.isSOTrx();

		final I_C_BPartner partner = InterfaceWrapperHelper.loadOutOfTrx(bpartnerId, I_C_BPartner.class);
		final BigDecimal bpartnerFlatDiscount = partner.getFlatDiscount();

		final int discountSchemaId = Services.get(IBPartnerBL.class).getDiscountSchemaId(partner, isSOTrx);
		if (discountSchemaId <= 0)
		{
			return;
		}

		final CalculatePricingConditionsRequest request = CalculatePricingConditionsRequest.builder()
				.discountSchemaId(discountSchemaId)
				.qty(pricingCtx.getQty())
				.price(result.getPriceStd())
				.productId(pricingCtx.getM_Product_ID())
				.attributeInstances(getAttributeInstances(pricingCtx.getReferencedObject()))
				.bpartnerFlatDiscount(bpartnerFlatDiscount)
				.pricingCtx(pricingCtx)
				.build();
		
		final IPricingConditionsService pricingConditionsService = Services.get(IPricingConditionsService.class);
		final CalculatePricingConditionsResult pricingConditionsResult = pricingConditionsService.calculatePricingConditions(request);

		result.setUsesDiscountSchema(true);
		result.setM_DiscountSchema_ID(discountSchemaId);
		updatePricingResultFromPricingConditionsResult(result, pricingConditionsResult);
	}

	private List<I_M_AttributeInstance> getAttributeInstances(final Object pricingReferencedObject)
	{
		if(pricingReferencedObject == null)
		{
			return ImmutableList.of();
		}
		
		final IAttributeSetInstanceAware asiAware = Services.get(IAttributeSetInstanceAwareFactoryService.class)
				.createOrNull(pricingReferencedObject);
		if(asiAware == null)
		{
			return ImmutableList.of();
		}
		
		final int asiId = asiAware.getM_AttributeSetInstance_ID();
		final List<I_M_AttributeInstance> attributeInstances = Services.get(IAttributeDAO.class).retrieveAttributeInstances(asiId);
		return attributeInstances;
	}
	
	private static void updatePricingResultFromPricingConditionsResult(final IPricingResult pricingResult, final CalculatePricingConditionsResult pricingConditionsResult)
	{
		pricingResult.setDiscount(pricingConditionsResult.getDiscount());
		pricingResult.setC_PaymentTerm_ID(pricingConditionsResult.getC_PaymentTerm_ID());
		pricingResult.setM_DiscountSchemaBreak_ID(pricingConditionsResult.getDiscountSchemaBreakId());
		pricingResult.setM_DiscountSchemaBreak_BasePricingSystem_ID(pricingConditionsResult.getDiscountSchemaBreak_BasePricingSystem_Id());
		
		final BigDecimal priceStdOverride = pricingConditionsResult.getPriceStdOverride();
		final BigDecimal priceListOverride = pricingConditionsResult.getPriceListOverride();
		final BigDecimal priceLimitOverride = pricingConditionsResult.getPriceLimitOverride();
		if(priceStdOverride != null)
		{
			pricingResult.setPriceStd(priceStdOverride);
		}
		if(priceListOverride != null)
		{
			pricingResult.setPriceList(priceListOverride);
		}
		if(priceLimitOverride != null)
		{
			pricingResult.setPriceLimit(priceLimitOverride);
		}
	}
}
