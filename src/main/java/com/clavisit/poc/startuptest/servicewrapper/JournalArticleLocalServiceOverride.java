package com.clavisit.poc.startuptest.servicewrapper;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.File;
import java.util.Locale;
import java.util.Map;

/**
 * @author sro
 * */

@Component(
	immediate = true,
	property = {
	},
	service = ServiceWrapper.class
)
public class JournalArticleLocalServiceOverride extends JournalArticleLocalServiceWrapper {

	private static final Log log = LogFactoryUtil.getLog(JournalArticleLocalServiceOverride.class);

	private RoleLocalService roleLocalService;

	public JournalArticleLocalServiceOverride() {
		super(null);
	}

	@Override
	public com.liferay.journal.model.JournalArticle getArticle(long groupId,
															   String articleId)
			throws PortalException {
		log.info("Get article");
		return super.getArticle(groupId, articleId);
	}

	@Override
	public JournalArticle addArticle(long userId, long groupId, long folderId, long classNameId, long classPK,
																	 String articleId, boolean autoArticleId, double version, Map<Locale, String> titleMap,
																	 Map<Locale, String> descriptionMap, String content, String ddmStructureKey, String ddmTemplateKey,
																	 String layoutUuid, int displayDateMonth, int displayDateDay, int displayDateYear, int displayDateHour,
																	 int displayDateMinute, int expirationDateMonth, int expirationDateDay, int expirationDateYear,
																	 int expirationDateHour, int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
																	 int reviewDateDay, int reviewDateYear, int reviewDateHour, int reviewDateMinute, boolean neverReview,
																	 boolean indexable, boolean smallImage, String smallImageURL, File smallImageFile,
																	 Map<String, byte[]> images, String articleURL, ServiceContext serviceContext) throws PortalException {


		JournalArticle article = super.addArticle(userId, groupId, folderId, classNameId, classPK, articleId, autoArticleId, version, titleMap,
				descriptionMap, content, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay,
				displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear,
				reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile, images,
				articleURL, serviceContext);

		log.info("Add article");
		log.info("Dummy call in article, number of roles: " + getRoleLocalService().getRolesCount());
		return article;
	}

	public RoleLocalService getRoleLocalService() {
		return roleLocalService;
	}

	@Reference(unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

}
