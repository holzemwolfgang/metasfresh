package de.metas.marketing.base.model;


/** Generated Interface for MKTG_Campaign_ContactPerson
 *  @author Adempiere (generated) 
 */
@SuppressWarnings("javadoc")
public interface I_MKTG_Campaign_ContactPerson 
{

    /** TableName=MKTG_Campaign_ContactPerson */
    public static final String Table_Name = "MKTG_Campaign_ContactPerson";

    /** AD_Table_ID=540972 */
//    public static final int Table_ID = org.compiere.model.MTable.getTable_ID(Table_Name);

//    org.compiere.util.KeyNamePair Model = new org.compiere.util.KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org
     */
//    java.math.BigDecimal accessLevel = java.math.BigDecimal.valueOf(3);

    /** Load Meta Data */

	/**
	 * Get Mandant.
	 * Mandant für diese Installation.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getAD_Client_ID();

	public org.compiere.model.I_AD_Client getAD_Client();

    /** Column definition for AD_Client_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_Client> COLUMN_AD_Client_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_Client>(I_MKTG_Campaign_ContactPerson.class, "AD_Client_ID", org.compiere.model.I_AD_Client.class);
    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/**
	 * Set Sektion.
	 * Organisatorische Einheit des Mandanten
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setAD_Org_ID (int AD_Org_ID);

	/**
	 * Get Sektion.
	 * Organisatorische Einheit des Mandanten
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getAD_Org_ID();

	public org.compiere.model.I_AD_Org getAD_Org();

	public void setAD_Org(org.compiere.model.I_AD_Org AD_Org);

    /** Column definition for AD_Org_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_Org> COLUMN_AD_Org_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_Org>(I_MKTG_Campaign_ContactPerson.class, "AD_Org_ID", org.compiere.model.I_AD_Org.class);
    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/**
	 * Set Ansprechpartner.
	 * User within the system - Internal or Business Partner Contact
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public void setAD_User_ID (int AD_User_ID);

	/**
	 * Get Ansprechpartner.
	 * User within the system - Internal or Business Partner Contact
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User();

	public void setAD_User(org.compiere.model.I_AD_User AD_User);

    /** Column definition for AD_User_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_User> COLUMN_AD_User_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_User>(I_MKTG_Campaign_ContactPerson.class, "AD_User_ID", org.compiere.model.I_AD_User.class);
    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/**
	 * Set Geschäftspartner.
	 * Bezeichnet einen Geschäftspartner
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setC_BPartner_ID (int C_BPartner_ID);

	/**
	 * Get Geschäftspartner.
	 * Bezeichnet einen Geschäftspartner
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public int getC_BPartner_ID();

	@Deprecated
	public org.compiere.model.I_C_BPartner getC_BPartner();

	@Deprecated
	public void setC_BPartner(org.compiere.model.I_C_BPartner C_BPartner);

    /** Column definition for C_BPartner_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_C_BPartner> COLUMN_C_BPartner_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_C_BPartner>(I_MKTG_Campaign_ContactPerson.class, "C_BPartner_ID", org.compiere.model.I_C_BPartner.class);
    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/**
	 * Get Erstellt.
	 * Datum, an dem dieser Eintrag erstellt wurde
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public java.sql.Timestamp getCreated();

    /** Column definition for Created */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_Created = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "Created", null);
    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/**
	 * Get Erstellt durch.
	 * Nutzer, der diesen Eintrag erstellt hat
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getCreatedBy();

    /** Column definition for CreatedBy */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_User> COLUMN_CreatedBy = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_User>(I_MKTG_Campaign_ContactPerson.class, "CreatedBy", org.compiere.model.I_AD_User.class);
    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/**
	 * Set Platformseitig deaktiviert.
	 *
	 * <br>Type: List
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setDeactivatedOnRemotePlatform (java.lang.String DeactivatedOnRemotePlatform);

	/**
	 * Get Platformseitig deaktiviert.
	 *
	 * <br>Type: List
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.lang.String getDeactivatedOnRemotePlatform();

    /** Column definition for DeactivatedOnRemotePlatform */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_DeactivatedOnRemotePlatform = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "DeactivatedOnRemotePlatform", null);
    /** Column name DeactivatedOnRemotePlatform */
    public static final String COLUMNNAME_DeactivatedOnRemotePlatform = "DeactivatedOnRemotePlatform";

	/**
	 * Set eMail.
	 * EMail-Adresse
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setEMail (java.lang.String EMail);

	/**
	 * Get eMail.
	 * EMail-Adresse
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.lang.String getEMail();

    /** Column definition for EMail */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_EMail = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "EMail", null);
    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/**
	 * Set Aktiv.
	 * Der Eintrag ist im System aktiv
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setIsActive (boolean IsActive);

	/**
	 * Get Aktiv.
	 * Der Eintrag ist im System aktiv
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public boolean isActive();

    /** Column definition for IsActive */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_IsActive = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "IsActive", null);
    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/**
	 * Set Synchronisierungstatus-Detail.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setLastSyncDetailMessage (java.lang.String LastSyncDetailMessage);

	/**
	 * Get Synchronisierungstatus-Detail.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.lang.String getLastSyncDetailMessage();

    /** Column definition for LastSyncDetailMessage */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_LastSyncDetailMessage = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "LastSyncDetailMessage", null);
    /** Column name LastSyncDetailMessage */
    public static final String COLUMNNAME_LastSyncDetailMessage = "LastSyncDetailMessage";

	/**
	 * Set Zuletzt auf Platform hochgeladen.
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setLastSyncOfLocalToRemote (java.sql.Timestamp LastSyncOfLocalToRemote);

	/**
	 * Get Zuletzt auf Platform hochgeladen.
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.sql.Timestamp getLastSyncOfLocalToRemote();

    /** Column definition for LastSyncOfLocalToRemote */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_LastSyncOfLocalToRemote = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "LastSyncOfLocalToRemote", null);
    /** Column name LastSyncOfLocalToRemote */
    public static final String COLUMNNAME_LastSyncOfLocalToRemote = "LastSyncOfLocalToRemote";

	/**
	 * Set Zuletzt von Platform runtergeladen.
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setLastSyncOfRemoteToLocal (java.sql.Timestamp LastSyncOfRemoteToLocal);

	/**
	 * Get Zuletzt von Platform runtergeladen.
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.sql.Timestamp getLastSyncOfRemoteToLocal();

    /** Column definition for LastSyncOfRemoteToLocal */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_LastSyncOfRemoteToLocal = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "LastSyncOfRemoteToLocal", null);
    /** Column name LastSyncOfRemoteToLocal */
    public static final String COLUMNNAME_LastSyncOfRemoteToLocal = "LastSyncOfRemoteToLocal";

	/**
	 * Set Letzter Synchronisierungsstatus.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setLastSyncStatus (java.lang.String LastSyncStatus);

	/**
	 * Get Letzter Synchronisierungsstatus.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.lang.String getLastSyncStatus();

    /** Column definition for LastSyncStatus */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_LastSyncStatus = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "LastSyncStatus", null);
    /** Column name LastSyncStatus */
    public static final String COLUMNNAME_LastSyncStatus = "LastSyncStatus";

	/**
	 * Set MKTG_Campaign_ContactPerson.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setMKTG_Campaign_ContactPerson_ID (int MKTG_Campaign_ContactPerson_ID);

	/**
	 * Get MKTG_Campaign_ContactPerson.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getMKTG_Campaign_ContactPerson_ID();

    /** Column definition for MKTG_Campaign_ContactPerson_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_MKTG_Campaign_ContactPerson_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "MKTG_Campaign_ContactPerson_ID", null);
    /** Column name MKTG_Campaign_ContactPerson_ID */
    public static final String COLUMNNAME_MKTG_Campaign_ContactPerson_ID = "MKTG_Campaign_ContactPerson_ID";

	/**
	 * Set MKTG_Campaign.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setMKTG_Campaign_ID (int MKTG_Campaign_ID);

	/**
	 * Get MKTG_Campaign.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getMKTG_Campaign_ID();

	public de.metas.marketing.base.model.I_MKTG_Campaign getMKTG_Campaign();

	public void setMKTG_Campaign(de.metas.marketing.base.model.I_MKTG_Campaign MKTG_Campaign);

    /** Column definition for MKTG_Campaign_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, de.metas.marketing.base.model.I_MKTG_Campaign> COLUMN_MKTG_Campaign_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, de.metas.marketing.base.model.I_MKTG_Campaign>(I_MKTG_Campaign_ContactPerson.class, "MKTG_Campaign_ID", de.metas.marketing.base.model.I_MKTG_Campaign.class);
    /** Column name MKTG_Campaign_ID */
    public static final String COLUMNNAME_MKTG_Campaign_ID = "MKTG_Campaign_ID";

	/**
	 * Set MKTG_ContactPerson.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setMKTG_ContactPerson_ID (int MKTG_ContactPerson_ID);

	/**
	 * Get MKTG_ContactPerson.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getMKTG_ContactPerson_ID();

	public de.metas.marketing.base.model.I_MKTG_ContactPerson getMKTG_ContactPerson();

	public void setMKTG_ContactPerson(de.metas.marketing.base.model.I_MKTG_ContactPerson MKTG_ContactPerson);

    /** Column definition for MKTG_ContactPerson_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, de.metas.marketing.base.model.I_MKTG_ContactPerson> COLUMN_MKTG_ContactPerson_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, de.metas.marketing.base.model.I_MKTG_ContactPerson>(I_MKTG_Campaign_ContactPerson.class, "MKTG_ContactPerson_ID", de.metas.marketing.base.model.I_MKTG_ContactPerson.class);
    /** Column name MKTG_ContactPerson_ID */
    public static final String COLUMNNAME_MKTG_ContactPerson_ID = "MKTG_ContactPerson_ID";

	/**
	 * Set MKTG_Platform.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setMKTG_Platform_ID (int MKTG_Platform_ID);

	/**
	 * Get MKTG_Platform.
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public int getMKTG_Platform_ID();

	@Deprecated
	public de.metas.marketing.base.model.I_MKTG_Platform getMKTG_Platform();

	@Deprecated
	public void setMKTG_Platform(de.metas.marketing.base.model.I_MKTG_Platform MKTG_Platform);

    /** Column definition for MKTG_Platform_ID */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, de.metas.marketing.base.model.I_MKTG_Platform> COLUMN_MKTG_Platform_ID = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, de.metas.marketing.base.model.I_MKTG_Platform>(I_MKTG_Campaign_ContactPerson.class, "MKTG_Platform_ID", de.metas.marketing.base.model.I_MKTG_Platform.class);
    /** Column name MKTG_Platform_ID */
    public static final String COLUMNNAME_MKTG_Platform_ID = "MKTG_Platform_ID";

	/**
	 * Set Name.
	 * Alphanumeric identifier of the entity
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setName (java.lang.String Name);

	/**
	 * Get Name.
	 * Alphanumeric identifier of the entity
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.lang.String getName();

    /** Column definition for Name */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_Name = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "Name", null);
    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/**
	 * Set Externe Datensatz-ID.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a virtual column
	 */
	@Deprecated
	public void setRemoteRecordId (java.lang.String RemoteRecordId);

	/**
	 * Get Externe Datensatz-ID.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: true (lazy loading)
	 * @deprecated Please don't use it because this is a lazy loading column and it might affect the performances
	 */
	@Deprecated
	public java.lang.String getRemoteRecordId();

    /** Column definition for RemoteRecordId */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_RemoteRecordId = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "RemoteRecordId", null);
    /** Column name RemoteRecordId */
    public static final String COLUMNNAME_RemoteRecordId = "RemoteRecordId";

	/**
	 * Get Aktualisiert.
	 * Datum, an dem dieser Eintrag aktualisiert wurde
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public java.sql.Timestamp getUpdated();

    /** Column definition for Updated */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object> COLUMN_Updated = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, Object>(I_MKTG_Campaign_ContactPerson.class, "Updated", null);
    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/**
	 * Get Aktualisiert durch.
	 * Nutzer, der diesen Eintrag aktualisiert hat
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getUpdatedBy();

    /** Column definition for UpdatedBy */
    public static final org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_User> COLUMN_UpdatedBy = new org.adempiere.model.ModelColumn<I_MKTG_Campaign_ContactPerson, org.compiere.model.I_AD_User>(I_MKTG_Campaign_ContactPerson.class, "UpdatedBy", org.compiere.model.I_AD_User.class);
    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";
}
