package com.violation.web.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtils {

	public static void addMessage(String key, FacesMessage.Severity severty, boolean isMessageKey) {
		if (isMessageKey) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severty, getMessage(key), ""));
		} else
			addMessage(key, severty);

	}
	public static void addMessage(String message, FacesMessage.Severity severty) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severty, message, ""));
	}

	public static void addMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message, ""));
	}

	public static String getMessage(String messageKey) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		return context.getApplication().evaluateExpressionGet(context, "#{msg['" + messageKey + "']}", String.class);
	}

	public static void addMessageByKey(String messageKey, FacesMessage.Severity severity) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessage(messageKey), ""));
	}

	public static void addMessageByKey(String messageKey, FacesMessage.Severity severity, String clientId) {

		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(severity, null, getMessage(messageKey)));
	}

	public static void addMessageByKey(String messageKey) {
		addMessageByKey(messageKey, FacesMessage.SEVERITY_INFO, null);
	}

}
