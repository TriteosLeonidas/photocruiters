package photocruiters.models;

public class User {
	
	private String name;
	private String surname;
	private String address;
	private String email;
	private int role;
	private String password;
	private String mobile;
	private String cv;
	private City city;
		
	/**
	 * Constructor
	 *  
	 * @param name
	 * @param surname
	 * @param email
	 * @param username
	 * @param password
	 */
	public User(String name, String surname, String email, int role, String password, String mobile, String cv, String address, City city) {
				
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.mobile = mobile;
		this.cv= cv;
		this.address = address;
		this.city = city;
	}
	
	/* Getters & Setters */
	
	/**
	 * @return the role
	 */
	public int getRole() {
		return role;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [role=" + role + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
	
	
	
} //End of class
