package com.ansa.useraccess.model.transfered;

/**
 * @author Satadru Basu
 *  Note this is a transfer object from the UserRole service.
 *  Its not an import hence the contract of this transfer model object needs to be in sync with 
 *   UserRole - service model provider.
 */
public class UserRole {

	private int id;
	private String roleName;
	private String roleDesc;
	
	public UserRole() {
	}

	public UserRole(int id, String roleName, String roleDesc) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
