package com.violation.web.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.violation.model.entity.Categories;
import com.violation.model.entity.Violations;
import com.violation.model.service.CategoryService;
import com.violation.model.service.ViolationService;
import com.violation.web.utils.EndirimUtils;
import com.violation.web.utils.MessagesUtils;
import com.violation.web.utils.PhotoUtils;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;

@Scope("session")
@Component
public class ViolationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ViolationService violationService;

	@Autowired
	private ApplicationBean applicationBean;

	@Autowired
	private UserBean userBean;

	@Autowired
	private CategoryService categoryService;

	private UploadedFile uploadFile;

	private int offset;
	private int limit;
	private Violations selectedViolation = new Violations();
	private List<Violations> violationList = new ArrayList<>();
	private List<SelectItem> categoriessAsSelectItem = new ArrayList<>();
	private List<SelectItem> subCategoriessAsSelectItem = new ArrayList<>();

	public String gotoViolationListPage() {
		userBean.checkForLogin();
		violationList = violationService.getViolationList(0, 1000);
		return "";
	}

	public String gotoUserOpenViolationListPage() {
		userBean.checkForLogin();
		violationList = violationService.getUserOpenViolationList(userBean.getloggedUser());
		return "";
	}

	public String gotoUserClosedViolationListPage() {
		userBean.checkForLogin();
		violationList = violationService.getUserClosedViolationList(userBean.getloggedUser());
		return "";
	}

	public String gotoViolationEditPage() {
		userBean.checkForLogin();
		categoriessAsSelectItem = EndirimUtils
				.convertCategoriesToSelectItemList(categoryService.getParentCategories(0, 1000));
		selectedViolation = violationService.getViolationsById(selectedViolation.getId());
		subCategoriessAsSelectItem = EndirimUtils.convertCategoriesToSelectItemList(
				categoryService.getCategoriesListByParentId(selectedViolation.getCatParentId(), 0, 100));
		return "";
	}

	public String gotoViolationDetailPage() {
		userBean.checkForLogin();
		categoriessAsSelectItem = EndirimUtils
				.convertCategoriesToSelectItemList(categoryService.getParentCategories(0, 1000));
		selectedViolation = violationService.getViolationsById(selectedViolation.getId());
		if (selectedViolation.getCatParentId() != null && selectedViolation.getCatParentId() > 0) {
			subCategoriessAsSelectItem = EndirimUtils.convertCategoriesToSelectItemList(
					categoryService.getCategoriesListByParentId(selectedViolation.getCatParentId(), 0, 100));
		}
		return "";
	}

	public String gotoAddViolationPage() {
		userBean.checkForLogin();
		categoriessAsSelectItem = EndirimUtils
				.convertCategoriesToSelectItemList(categoryService.getParentCategories(0, 1000));
		selectedViolation = new Violations();
		selectedViolation.setUserId(userBean.getloggedUser().getId());
		selectedViolation.setCatParentId((int)categoriessAsSelectItem.get(0).getValue());
		if (selectedViolation.getCatParentId() != null && selectedViolation.getCatParentId() > 0) {
			subCategoriessAsSelectItem = EndirimUtils.convertCategoriesToSelectItemList(
					categoryService.getCategoriesListByParentId(selectedViolation.getCatParentId(), 0, 100));
		}
		
		return "";
	}

	public String saveOrUpdateViolation() throws IOException {
		userBean.checkForLogin();

		if (selectedViolation.getCatChildId() != null && selectedViolation.getCatChildId() > 0) {

			Categories cat = categoryService.getCategoryById(selectedViolation.getCatChildId());

			if (cat.getType() == 1) {
				// min
				if (selectedViolation.getValue() < cat.getMin()) {
					MessagesUtils.addMessage("Value Must Be Greater Than : " + cat.getMin(),
							FacesMessage.SEVERITY_ERROR);
				} else {
					selectedViolation = violationService.saveOrUpdateViolations(selectedViolation);
					MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!",
							FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(applicationBean.getViolationDetailPageUrl(selectedViolation));
				}

			} else if (cat.getType() == 2) {
				// max
				if (selectedViolation.getValue() > cat.getMax()) {
					MessagesUtils.addMessage("Value Must Be Less Than  : " + cat.getMax(), FacesMessage.SEVERITY_ERROR);
				} else {
					selectedViolation = violationService.saveOrUpdateViolations(selectedViolation);
					MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!",
							FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(applicationBean.getViolationDetailPageUrl(selectedViolation));
				}

			} else if (cat.getType() == 3) {
				// between
				if (selectedViolation.getValue() < cat.getMin() || selectedViolation.getValue() > cat.getMax()) {
					MessagesUtils.addMessage("Value Must Be Betwen  " + cat.getMin() + " and  " + cat.getMax(),
							FacesMessage.SEVERITY_ERROR);
				} else {
					selectedViolation = violationService.saveOrUpdateViolations(selectedViolation);
					MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!",
							FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(applicationBean.getViolationDetailPageUrl(selectedViolation));
				}

			}else if (cat.getType() == 4) {
				// between
				if (!selectedViolation.getValue().equals(cat.getMax())) {
					MessagesUtils.addMessage("Value Must Be  : " + cat.getMax(), FacesMessage.SEVERITY_ERROR);
				} else {
					selectedViolation = violationService.saveOrUpdateViolations(selectedViolation);
					MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!",
							FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(applicationBean.getViolationDetailPageUrl(selectedViolation));
				}

			} else {
				selectedViolation = violationService.saveOrUpdateViolations(selectedViolation);
				MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!",
						FacesMessage.SEVERITY_INFO);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(applicationBean.getViolationDetailPageUrl(selectedViolation));
			}

		} else {
			selectedViolation = violationService.saveOrUpdateViolations(selectedViolation);
			MessagesUtils.addMessage("Adding/Updating Operations Completed Successfully!", FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(applicationBean.getViolationDetailPageUrl(selectedViolation));
		}

		return null;
	}

	public void onStateChange(StateChangeEvent event) {
		LatLngBounds bounds = event.getBounds();
		int zoomLevel = event.getZoomLevel();
	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();
		selectedViolation.setLatitude(latlng.getLat());
		selectedViolation.setLongitude(latlng.getLng());
		MessagesUtils.addMessage("Point Selected For Violation Lat: " + latlng.getLat() + " - Lng: " + latlng.getLng(),
				FacesMessage.SEVERITY_INFO);
	}

	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void uploadViolationLogoImage(FileUploadEvent event) {
		uploadFile = event.getFile();
		if (uploadFile != null) {
			String imageUrl = PhotoUtils.uploadViolationImage(uploadFile);
			if (!imageUrl.isEmpty() && !imageUrl.equals("")) {
				selectedViolation.setImageUrl(imageUrl);
				MessagesUtils.addMessage("Image Uploaded Successfully!", FacesMessage.SEVERITY_INFO);
				if (selectedViolation.getId() != null && selectedViolation.getId() > 0) {
					violationService.saveOrUpdateViolations(selectedViolation);
				}
			}
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void loadSubCategories() {

		subCategoriessAsSelectItem = EndirimUtils.convertCategoriesToSelectItemList(
				categoryService.getCategoriesListByParentId(selectedViolation.getCatParentId(), 0, 100));

	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Violations getSelectedViolation() {
		return selectedViolation;
	}

	public void setSelectedViolation(Violations selectedViolation) {
		this.selectedViolation = selectedViolation;
	}

	public List<Violations> getViolationList() {
		return violationList;
	}

	public void setViolationList(List<Violations> violationList) {
		this.violationList = violationList;
	}

	public List<SelectItem> getCategoriessAsSelectItem() {
		return categoriessAsSelectItem;
	}

	public void setCategoriessAsSelectItem(List<SelectItem> categoriessAsSelectItem) {
		this.categoriessAsSelectItem = categoriessAsSelectItem;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<SelectItem> getSubCategoriessAsSelectItem() {
		return subCategoriessAsSelectItem;
	}

	public void setSubCategoriessAsSelectItem(List<SelectItem> subCategoriessAsSelectItem) {
		this.subCategoriessAsSelectItem = subCategoriessAsSelectItem;
	}
}
