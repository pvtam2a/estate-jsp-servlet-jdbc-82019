package com.laptrinhjavaweb.builder;

public class CustomerSearchBuilder {
	// required parameters
	private String name;
	private String tel;
	private String email;
	private String staffId;
	
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public String getEmail() {
		return email;
	}
	public String getStaffId() {
		return staffId;
	}


	private CustomerSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.tel = builder.tel;
		this.email = builder.email;
		this.staffId = builder.staffId;
	}

	// Builder Class
	public static class Builder {

		// required parameters
		private String name;
		private String tel;
		private String email;
		private String staffId;
		
		

		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setTel(String tel) {
			this.tel = tel;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setStaffId(String staffId) {
			this.staffId = staffId;
			return this;
		}

		public CustomerSearchBuilder build() {
			return new CustomerSearchBuilder(this);
		}
	}
}
