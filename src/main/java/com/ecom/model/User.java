package com.ecom.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;

	@Embedded
	private Name name;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	@Column(name = "mobile_number", unique = true, nullable = false)
	private String mobileNumber;
	
	@Column(name = "profile_url")
	private String profileUrl;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "uid"), inverseJoinColumns = @JoinColumn(name = "rid"))
	private Set<UserRole> roles = new HashSet<>();
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "user")
	private List<Address> address = new ArrayList<>();

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", email=" + email + ", password=" + password + ", mobileNumber="
				+ mobileNumber + ", roles=" + roles + "]";
	}
	
	

}
