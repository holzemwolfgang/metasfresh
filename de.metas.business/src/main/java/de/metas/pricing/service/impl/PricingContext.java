package de.metas.pricing.service.impl;

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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Check;
import org.adempiere.util.time.SystemTime;
import org.compiere.model.I_M_PriceList_Version;
import org.compiere.model.I_M_Product;
import org.compiere.util.Env;

import de.metas.pricing.IEditablePricingContext;
import lombok.ToString;

@ToString
class PricingContext implements IEditablePricingContext
{
	private int pricingSystemId = -1;
	private int M_Product_ID;
	private int M_PriceList_ID;
	private int M_PriceList_Version_ID;
	private boolean skipCheckingPriceListSOTrxFlag;

	/**
	 * PriceDate timestamp.
	 * NOTE: storing as milliseconds only because {@link Timestamp} is not an immutable class.
	 */
	private long priceDateTS = 0;
	/**
	 * "now" timestamp to be used when there was no priceDate set.
	 * NOTE: We are taking a snapshot of the current timestamp because we want to return the same value each time we are asked.
	 */
	private long priceDateNowTS = SystemTime.millis();

	private int C_Country_ID = 0;

	private int C_UOM_ID;
	private int C_Currency_ID;
	private int C_BPartner_ID;
	private BigDecimal qty;
	private boolean isSOTrx;
	private int AD_Table_ID;
	private int Record_ID;
	private int m_PP_Product_BOM_ID;
	private int m_PP_Product_BOMLine_ID;
	private Object referencedObject;
	private boolean disallowDiscount;
	private String trxName;
	private boolean convertPriceToContextUOM;
	private Boolean isManualPrice = null; // task 08908: can be set by the calling code. Otherwise the engine shall try the referenced object
	private boolean failIfNotCalculated = false;

	final private Map<String, Object> properties = new HashMap<>();

	@Override
	public IEditablePricingContext copy()
	{
		final PricingContext pricingCtxNew = new PricingContext();
		pricingCtxNew.M_Product_ID = M_Product_ID;
		pricingCtxNew.pricingSystemId = pricingSystemId;
		pricingCtxNew.M_PriceList_ID = M_PriceList_ID;
		pricingCtxNew.M_PriceList_Version_ID = M_PriceList_Version_ID;
		pricingCtxNew._priceListVersion = _priceListVersion;
		pricingCtxNew.priceDateTS = this.priceDateTS;
		pricingCtxNew.priceDateNowTS = this.priceDateNowTS;
		pricingCtxNew.C_UOM_ID = C_UOM_ID;
		pricingCtxNew.C_Currency_ID = C_Currency_ID;
		pricingCtxNew.C_Country_ID = C_Country_ID;
		pricingCtxNew.C_BPartner_ID = C_BPartner_ID;
		pricingCtxNew.qty = qty;
		pricingCtxNew.isSOTrx = isSOTrx;
		pricingCtxNew.AD_Table_ID = AD_Table_ID;
		pricingCtxNew.Record_ID = Record_ID;
		pricingCtxNew.m_PP_Product_BOM_ID = m_PP_Product_BOM_ID;
		pricingCtxNew.m_PP_Product_BOMLine_ID = m_PP_Product_BOMLine_ID;
		pricingCtxNew.referencedObject = referencedObject;
		pricingCtxNew.disallowDiscount = disallowDiscount;
		pricingCtxNew.trxName = trxName;
		pricingCtxNew.convertPriceToContextUOM = convertPriceToContextUOM;
		pricingCtxNew.isManualPrice = isManualPrice;
		pricingCtxNew.failIfNotCalculated = failIfNotCalculated;
		pricingCtxNew.skipCheckingPriceListSOTrxFlag = skipCheckingPriceListSOTrxFlag;
		pricingCtxNew.properties.putAll(properties);

		return pricingCtxNew;
	}

	@Override
	public int getM_PricingSystem_ID()
	{
		return pricingSystemId;
	}

	@Override
	public void setM_PricingSystem_ID(final int pricingSystemId)
	{
		this.pricingSystemId = pricingSystemId;
	}

	@Override
	public int getM_Product_ID()
	{
		return M_Product_ID;
	}

	@Override
	public void setM_Product_ID(final int m_Product_ID)
	{
		M_Product_ID = m_Product_ID;
		product = null; // reset
	}

	private I_M_Product product;

	@Override
	public I_M_Product getM_Product()
	{
		if (product == null && getM_Product_ID() > 0)
		{
			product = InterfaceWrapperHelper.create(getCtx(), getM_Product_ID(), I_M_Product.class, ITrx.TRXNAME_None);
		}
		return product;
	}

	@Override
	public int getM_PriceList_ID()
	{
		return M_PriceList_ID;
	}

	@Override
	public void setM_PriceList_ID(final int M_PriceList_ID)
	{
		this.M_PriceList_ID = M_PriceList_ID;
	}

	@Override
	public int getM_PriceList_Version_ID()
	{
		return M_PriceList_Version_ID;
	}

	private I_M_PriceList_Version _priceListVersion = null;

	@Override
	public I_M_PriceList_Version getM_PriceList_Version()
	{
		final int priceListVersionId = getM_PriceList_Version_ID();
		if (priceListVersionId <= 0)
		{
			return null;
		}

		if (_priceListVersion == null || _priceListVersion.getM_PriceList_Version_ID() != priceListVersionId)
		{
			_priceListVersion = InterfaceWrapperHelper.create(getCtx(), priceListVersionId, I_M_PriceList_Version.class, ITrx.TRXNAME_None);
		}
		return _priceListVersion;
	}

	@Override
	public void setM_PriceList_Version_ID(final int m_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID == m_PriceList_Version_ID)
		{
			return;
		}

		M_PriceList_Version_ID = m_PriceList_Version_ID;
		_priceListVersion = null; // needs to be reloaded

	}

	@Override
	public Timestamp getPriceDate()
	{
		return new Timestamp(priceDateTS <= 0 ? priceDateNowTS : priceDateTS);
	}

	@Override
	public void setPriceDate(final Timestamp priceDate)
	{
		this.priceDateTS = priceDate == null ? 0 : priceDate.getTime();
	}

	@Override
	public int getC_UOM_ID()
	{
		return C_UOM_ID;
	}

	@Override
	public void setC_UOM_ID(final int c_UOM_ID)
	{
		C_UOM_ID = c_UOM_ID;
	}

	@Override
	public int getC_Currency_ID()
	{
		return C_Currency_ID;
	}

	@Override
	public void setC_Currency_ID(final int c_Currency_ID)
	{
		C_Currency_ID = c_Currency_ID;
	}

	@Override
	public int getC_BPartner_ID()
	{
		return C_BPartner_ID;
	}

	@Override
	public void setC_BPartner_ID(final int c_BPartner_ID)
	{
		C_BPartner_ID = c_BPartner_ID;
	}

	@Override
	public BigDecimal getQty()
	{
		return qty;
	}

	@Override
	public void setQty(final BigDecimal qty)
	{
		this.qty = qty;
	}

	@Override
	public boolean isSOTrx()
	{
		return isSOTrx;
	}

	@Override
	public void setSOTrx(final boolean isSOTrx)
	{
		this.isSOTrx = isSOTrx;
	}

	@Override
	public int getPP_Product_BOM_ID()
	{
		return m_PP_Product_BOM_ID;
	}

	@Override
	public int getPP_Product_BOMLine_ID()
	{
		return m_PP_Product_BOMLine_ID;
	}

	public void setPP_Product_BOM_ID(final int m_PP_Product_BOM_ID)
	{
		this.m_PP_Product_BOM_ID = m_PP_Product_BOM_ID;
	}

	public void setPP_Product_BOMLine_ID(final int m_PP_Product_BOMLine_ID)
	{
		this.m_PP_Product_BOMLine_ID = m_PP_Product_BOMLine_ID;
	}

	@Override
	public int getAD_Table_ID()
	{
		return AD_Table_ID;
	}

	@Override
	public void setAD_Table_ID(final int aD_Table_ID)
	{
		AD_Table_ID = aD_Table_ID;
	}

	@Override
	public int getRecord_ID()
	{
		return Record_ID;
	}

	@Override
	public void setRecord_ID(final int record_ID)
	{
		Record_ID = record_ID;
	}

	@Override
	public Object getReferencedObject()
	{
		return referencedObject;
	}

	@Override
	public void setReferencedObject(final Object referencedObject)
	{
		this.referencedObject = referencedObject;
		if (null != referencedObject)
		{
			trxName = InterfaceWrapperHelper.getTrxName(referencedObject);
		}
	}

	@Override
	public boolean isDisallowDiscount()
	{
		return disallowDiscount;
	}

	@Override
	public void setDisallowDiscount(final boolean disallowDiscount)
	{
		this.disallowDiscount = disallowDiscount;
	}

	@Override
	public String getTrxName()
	{
		return trxName;
	}

	@Override
	public void setTrxName(final String trxName)
	{
		this.trxName = trxName;
	}

	@Override
	public boolean isConvertPriceToContextUOM()
	{
		return convertPriceToContextUOM;
	}

	@Override
	public void setConvertPriceToContextUOM(final boolean convertPriceToContextUOM)
	{
		this.convertPriceToContextUOM = convertPriceToContextUOM;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getProperty(final String propertyName, final Class<T> clazz)
	{
		final Object object = properties.get(propertyName);
		Check.assume(clazz.isInstance(object), "The property {}={} is assumed to be an instance of clazz {}", propertyName, object, clazz);

		return (T)object;
	}

	@Override
	public void setProperty(final String propertyName, final Object value)
	{
		properties.put(propertyName, value);
	}

	@Override
	public Properties getCtx()
	{
		final Object referencedObject = getReferencedObject();
		if (referencedObject == null)
		{
			return Env.getCtx();
		}

		return InterfaceWrapperHelper.getCtx(referencedObject);
	}

	@Override
	public Boolean isManualPrice()
	{
		return isManualPrice;
	}

	@Override
	public void setManualPrice(boolean isManualPrice)
	{
		this.isManualPrice = isManualPrice;
	}

	@Override
	public int getC_Country_ID()
	{
		return C_Country_ID;
	}

	@Override
	public void setC_Country_ID(int countryId)
	{
		this.C_Country_ID = countryId;
	}
	
	@Override
	public boolean isFailIfNotCalculated()
	{
		return failIfNotCalculated;
	}
	
	@Override
	public void setFailIfNotCalculated(boolean failIfNotCalculated)
	{
		this.failIfNotCalculated = failIfNotCalculated;
	}
	
	@Override
	public void setSkipCheckingPriceListSOTrxFlag(boolean skipCheckingPriceListSOTrxFlag)
	{
		this.skipCheckingPriceListSOTrxFlag = skipCheckingPriceListSOTrxFlag;
	}
	
	@Override
	public boolean isSkipCheckingPriceListSOTrxFlag()
	{
		return skipCheckingPriceListSOTrxFlag;
	}
}
