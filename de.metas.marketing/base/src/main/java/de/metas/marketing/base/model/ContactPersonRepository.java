package de.metas.marketing.base.model;

import static org.adempiere.model.InterfaceWrapperHelper.load;
import static org.adempiere.model.InterfaceWrapperHelper.newInstance;
import static org.adempiere.model.InterfaceWrapperHelper.saveRecord;

import java.util.List;
import java.util.Optional;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.adempiere.util.StringUtils;
import org.adempiere.util.time.SystemTime;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableList;

import de.metas.marketing.base.model.ContactPerson.ContactPersonBuilder;
import lombok.NonNull;

/*
 * #%L
 * de.metas.marketing
 * %%
 * Copyright (C) 2018 metas GmbH
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

@Repository
public class ContactPersonRepository
{
	public ContactPerson save(@NonNull final ContactPerson contactPerson)
	{
		final I_MKTG_ContactPerson contactPersonRecord = cerateOrUpdateRecordDontSave(contactPerson);
		saveRecord(contactPersonRecord);

		return contactPerson.toBuilder()
				.contactPersonId(ContactPersonId.ofRepoId(contactPersonRecord.getMKTG_ContactPerson_ID()))
				.build();
	}

	private static I_MKTG_ContactPerson cerateOrUpdateRecordDontSave(
			@NonNull final ContactPerson contactPerson)
	{
		final I_MKTG_ContactPerson contactPersonRecord = loadRecordIfPossible(contactPerson)
				.orElse(newInstance(I_MKTG_ContactPerson.class));

		if (contactPerson.getAdUserId() > 0)
		{
			contactPersonRecord.setAD_User_ID(contactPerson.getAdUserId());
		}
		else
		{
			contactPersonRecord.setAD_User(null);
		}

		contactPersonRecord.setC_BPartner_ID(contactPerson.getCBpartnerId());
		contactPersonRecord.setName(contactPerson.getName());
		contactPersonRecord.setMKTG_Platform_ID(contactPerson.getPlatformId().getRepoId());
		contactPersonRecord.setRemoteRecordId(contactPerson.getRemoteId());

		// set email stuff
		final Optional<EmailAddress> email = EmailAddress.cast(contactPerson.getAddress());

		final String emailString = email.map(EmailAddress::getValue).orElse(null);

		final Boolean deactivatedBool = email.map(EmailAddress::getActiveOnRemotePlatformOrNull).orElse(null);
		final String deactivatedString = StringUtils.ofBoolean(
				deactivatedBool,
				X_MKTG_ContactPerson.DEACTIVATEDONREMOTEPLATFORM_UNKNOWN);

		contactPersonRecord.setEMail(emailString);
		contactPersonRecord.setDeactivatedOnRemotePlatform(deactivatedString);

		return contactPersonRecord;
	}

	private static Optional<I_MKTG_ContactPerson> loadRecordIfPossible(
			@NonNull final ContactPerson contactPerson)
	{
		I_MKTG_ContactPerson contactPersonRecord = null;
		if (contactPerson.getContactPersonId() != null)
		{
			final ContactPersonId contactPersonId = contactPerson.getContactPersonId();
			contactPersonRecord = load(contactPersonId.getRepoId(), I_MKTG_ContactPerson.class);
		}
		else if (!Check.isEmpty(contactPerson.getRemoteId(), true) && contactPerson.getPlatformId() != null)
		{
			contactPersonRecord = Services.get(IQueryBL.class)
					.createQueryBuilder(I_MKTG_ContactPerson.class)
					.addOnlyActiveRecordsFilter()
					.addEqualsFilter(I_MKTG_ContactPerson.COLUMN_MKTG_Platform_ID, contactPerson.getPlatformId().getRepoId())
					.addEqualsFilter(I_MKTG_ContactPerson.COLUMN_RemoteRecordId, contactPerson.getRemoteId())
					.create()
					.firstOnly(I_MKTG_ContactPerson.class); // might be null, that's ok
		}

		if (contactPersonRecord == null)
		{
			// if it's still null, then see if there is a contact with a matching email
			contactPersonRecord = Services.get(IQueryBL.class)
					.createQueryBuilder(I_MKTG_ContactPerson.class)
					.addOnlyActiveRecordsFilter()
					.addEqualsFilter(I_MKTG_ContactPerson.COLUMN_MKTG_Platform_ID, contactPerson.getPlatformId().getRepoId())
					.orderBy()
					.addColumn(I_MKTG_ContactPerson.COLUMN_MKTG_ContactPerson_ID).endOrderBy()
					.create()
					.first();

		}

		return Optional.ofNullable(contactPersonRecord);
	}

	public ContactPerson saveCampaignSyncResult(@NonNull final SyncResult syncResult)
	{
		final ContactPerson contactPerson = ContactPerson.cast(syncResult.getSynchedDataRecord()).get();
		final I_MKTG_ContactPerson contactPersonRecord = cerateOrUpdateRecordDontSave(contactPerson);

		if (syncResult instanceof LocalToRemoteSyncResult)
		{
			contactPersonRecord.setLastSyncOfLocalToRemote(SystemTime.asTimestamp());

			final LocalToRemoteSyncResult localToRemoteSyncResult = (LocalToRemoteSyncResult)syncResult;
			contactPersonRecord.setLastSyncStatus(localToRemoteSyncResult.getLocalToRemoteStatus().toString());
			contactPersonRecord.setLastSyncDetailMessage(localToRemoteSyncResult.getErrorMessage());
		}
		else if (syncResult instanceof RemoteToLocalSyncResult)
		{
			contactPersonRecord.setLastSyncOfRemoteToLocal(SystemTime.asTimestamp());

			final RemoteToLocalSyncResult remoteToLocalSyncResult = (RemoteToLocalSyncResult)syncResult;
			contactPersonRecord.setLastSyncStatus(remoteToLocalSyncResult.getRemoteToLocalStatus().toString());
			contactPersonRecord.setLastSyncDetailMessage(remoteToLocalSyncResult.getErrorMessage());
		}
		else
		{
			Check.fail("The given syncResult has an unexpected type of {}; syncResult={}", syncResult.getClass(), syncResult);
		}
		saveRecord(contactPersonRecord);
		return contactPerson
				.toBuilder()
				.contactPersonId(ContactPersonId.ofRepoId(contactPersonRecord.getMKTG_ContactPerson_ID()))
				.build();
	}

	public List<ContactPerson> getByCampaignId(@NonNull final CampaignId campaignId)
	{
		return Services.get(IQueryBL.class)
				.createQueryBuilder(I_MKTG_Campaign_ContactPerson.class)
				.addOnlyActiveRecordsFilter()
				.addEqualsFilter(I_MKTG_Campaign_ContactPerson.COLUMN_MKTG_Campaign_ID, campaignId.getRepoId())
				.andCollect(I_MKTG_Campaign_ContactPerson.COLUMN_MKTG_ContactPerson_ID)
				.addOnlyActiveRecordsFilter()
				.create()
				.stream()
				.map(ContactPersonRepository::asContactPerson)
				.collect(ImmutableList.toImmutableList());
	}

	private static ContactPerson asContactPerson(@NonNull final I_MKTG_ContactPerson contactPersonRecord)
	{
		final String emailDeactivated = contactPersonRecord.getDeactivatedOnRemotePlatform();

		final ContactPersonBuilder builder = ContactPerson.builder();
		if (!Check.isEmpty(contactPersonRecord.getEMail(), true))
		{
			final EmailAddress emailAddress = EmailAddress.of(
					contactPersonRecord.getEMail(),
					StringUtils.toBoolean(emailDeactivated, null));
			builder
					.address(emailAddress);
		}
		return builder
				.adUserId(contactPersonRecord.getAD_User_ID())
				.cBpartnerId(contactPersonRecord.getC_BPartner_ID())
				.name(contactPersonRecord.getName())
				.platformId(PlatformId.ofRepoId(contactPersonRecord.getMKTG_Platform_ID()))
				.remoteId(contactPersonRecord.getRemoteRecordId())
				.contactPersonId(ContactPersonId.ofRepoId(contactPersonRecord.getMKTG_ContactPerson_ID()))
				.build();
	}
}
