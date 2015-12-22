package com.violation.web.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.violation.model.entity.Users;
import com.violation.model.service.UserService;
import com.violation.web.utils.MessagesUtils;
import com.violation.web.utils.PhotoUtils;

@Scope("session")
@Component
public class UserBean {

	private Users loggedUser = new Users();
	private Users selectedUser = new Users();

	private boolean loggedIn = false;
	@Autowired
	private ApplicationBean applicationBean;
	private UploadedFile uploadFile;

	@Autowired
	private UserService userService;

	public void login(ActionEvent event) {

		Users user = userService.getUsersByUsernamePassword(loggedUser.getUsername(), loggedUser.getPassword());
		if (user != null) {
			loggedIn = true;
			selectedUser = user;
			loggedUser = user;
			MessagesUtils.addMessage("Login Successfully!", FacesMessage.SEVERITY_INFO);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(applicationBean.getUserProfilePageUrl(loggedUser));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			loggedIn = false;
			MessagesUtils.addMessage("Check Username/Pasword!", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void signUp(ActionEvent event) {

		Users user = userService.getUsersByEmail(loggedUser.getEmail());

		if (user != null) {
			MessagesUtils.addMessage("There is another user with this e-mail!", FacesMessage.SEVERITY_ERROR);

		} else {
			user = userService.getUsersByUserName(loggedUser.getUsername());

			if (user != null) {
				MessagesUtils.addMessage("There is another user with this username!", FacesMessage.SEVERITY_ERROR);

			} else {
				loggedUser = userService.saveOrUpdateUser(loggedUser);
				selectedUser = loggedUser;
				loggedIn = true;
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(applicationBean.getUserProfilePageUrl(loggedUser));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String logout() {
		loggedUser = new Users();
		loggedIn = false;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(applicationBean.getLoginPageUrl());
		} catch (IOException e) {

			MessagesUtils.addMessage("Error on Logout!", FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
		return "";
	}

	public String checkForLogin() {

		if (!isLoggedIn()) {
			MessagesUtils.addMessage("Login For Process Operation!", FacesMessage.SEVERITY_ERROR);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(applicationBean.getLoginPageUrl());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public void uploadProfileImage(FileUploadEvent event) {
		uploadFile = event.getFile();
		if (uploadFile != null) {
			String imageUrl = PhotoUtils.uploadProfileImage(uploadFile);
			if (!imageUrl.isEmpty() && !imageUrl.equals("")) {
				loggedUser.setAvatar(imageUrl);
				MessagesUtils.addMessage("Image Uploaded Successfully!", FacesMessage.SEVERITY_INFO);
				userService.saveOrUpdateUser(loggedUser);
				try {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(applicationBean.getUserProfilePageUrl(loggedUser));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public String gotoSignUpPage() {
		loggedUser = new Users();
		loggedIn = false;
		return "";
	}

	public String gotoUserProfilePage() {
		checkForLogin();
		selectedUser = userService.getUsersById(selectedUser.getId());
		return "";
	}

	public String gotoUserProfileEditPage() {
		checkForLogin();
		loggedUser = userService.getUsersById(loggedUser.getId());
		return "";
	}

	public void updateUserInfo(ActionEvent event) {

		loggedUser = userService.saveOrUpdateUser(loggedUser);
		selectedUser = loggedUser;
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(applicationBean.getUserProfilePageUrl(selectedUser));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getLoginPageUrl() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(applicationBean.getLoginPageUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Users getloggedUser() {
		return loggedUser;
	}

	public void setloggedUser(Users loggedUser) {
		this.loggedUser = loggedUser;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Users getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Users selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
