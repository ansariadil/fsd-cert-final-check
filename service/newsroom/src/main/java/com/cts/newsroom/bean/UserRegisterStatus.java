package com.cts.newsroom.bean;

public class UserRegisterStatus {
	private boolean emailExist;
	private String message;

	public UserRegisterStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegisterStatus(boolean emailExist, String message) {
		super();
		this.emailExist = emailExist;
		this.message = message;
	}

	public boolean isEmailExist() {
		return emailExist;
	}

	public void setEmailExist(boolean emailExist) {
		this.emailExist = emailExist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegisterStatus other = (UserRegisterStatus) obj;
		if (emailExist != other.emailExist)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
