package com.ecom.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecom.dtos.AddressDto;
import com.ecom.dtos.UserDto;
import com.ecom.model.UserRole;
import com.ecom.response.ResponseHandler;
import com.ecom.services.FileServices;
import com.ecom.services.UserServices;
import com.ecom.utills.Constants;
import com.ecom.utills.Messages;

@RestController
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	private UserServices userServices;
	
	@Value("${project.name}")
	private String staticUrl;
	
	@Autowired
	private FileServices fileServices;

	@PostMapping()
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
		HashSet<UserRole> roles = new HashSet<>();
		UserRole userRole = new UserRole(101, "NORMAL_USER");
		roles.add(userRole);
		UserDto user = this.userServices.addUser(userDto, roles);
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> allUsers = userServices.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	@GetMapping(name = "GET_USER_BY_ID", value = "/{id}")
	public ResponseEntity<UserDto> getUserBYId(@PathVariable Integer id) {
		UserDto user = userServices.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateUser(@RequestParam("user") String userString,
			@RequestPart(name = "profile", required = false) Optional<MultipartFile> file) {
		
		try {
			
			UserDto json = userServices.getJson(userString);

			if (!file.isEmpty()) {
				String uploadImage = fileServices.uploadImage(Constants.UPLOAD_DIR, file.get());    
				
				System.out.println(uploadImage);
				
				
				ServletUriComponentsBuilder currentContextPath = ServletUriComponentsBuilder.fromCurrentContextPath();
				System.out.println("currentCotext "+currentContextPath);
				
				String dowloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/user/profile/images/")
						.path(uploadImage).toUriString();
				System.out.println(dowloadUrl);
				
				json.setProfileUrl(dowloadUrl);
			}
			
			UserDto updateUser = userServices.updateUser(json);
			
			return ResponseHandler.responseBuilder(Messages.USER_UPDATED, HttpStatus.OK, updateUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseHandler.responseBuilder(Messages.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR, Messages.SOMETHING_WENT_WRONG);
	}
	
//	@GetMapping(name = "/profile/{imageName}",produces = {MediaType.IMAGE_JPEG_VALUE})
//	public void downloadImage(@PathVariable String imageName, HttpServletResponse httpServletResponse) throws IOException{
//		
//		String path = Constants.UPLOAD_DIR+File.separator+imageName;
//		
//		InputStream resource = fileServices.getResource(path, imageName);
//		
//		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
//		
//		StreamUtils.copy(resource, httpServletResponse.getOutputStream());
//	}
	
	
	@GetMapping(value = "/profile/images/{imageName}", produces = { MediaType.IMAGE_JPEG_VALUE })
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

	
		String path = Constants.UPLOAD_DIR;

		InputStream resource = fileServices.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}


	@DeleteMapping("/{uid}")
	public ResponseEntity<?> deleteUser(@PathVariable("uid") Integer uid) {
		userServices.deleteUser(uid);
		return ResponseEntity.ok(Map.entry("User deleted successfully!", true));
	}

	@PostMapping("/address")
	public ResponseEntity<AddressDto> addUserAddress(@RequestBody AddressDto addressDto) {
		AddressDto addAddress = userServices.addAddress(addressDto);
		return new ResponseEntity<AddressDto>(addressDto, HttpStatus.CREATED);
	}

	@GetMapping("/address/{aid}")
	public ResponseEntity<AddressDto> getUserAddress(@PathVariable("aid") Integer aid) {
		AddressDto address = userServices.getAddress(aid);
		return ResponseEntity.ok(address);
	}

	@DeleteMapping("/address/{aid}")
	public ResponseEntity<?> deleteUserAddress(@PathVariable("aid") Integer aid) {
		userServices.deleteAddress(aid);
		return ResponseEntity.ok(Map.entry("Address deleted Successfully", true));
	}

	@PutMapping("/address")
	public ResponseEntity<AddressDto> updateUserAddress(@RequestBody AddressDto addressDto) {
		AddressDto address = userServices.updateAddress(addressDto);
		return ResponseEntity.ok(address);
	}
	
	
	

//	public static void main(String[] args) {
//		String string = "0000000";
//
//		if (string.matches("                         ")) {
//
//			System.out.println("format varified");
//		} else {
//			System.out.println("not varifiled");
//		}
//
//	}
//
//	ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) {
////       HashSet<Integer> set1= (HashSet<Integer>) Arrays.stream(A).boxed().collect(Collectors.toSet());
////       HashSet<Integer> set2 = (HashSet<Integer>) Arrays.stream(B).boxed().collect(Collectors.toSet());
////       HashSet<Integer> set3 = (HashSet<Integer>) Arrays.stream(C).boxed().collect(Collectors.toSet());
////      
////       
////       set1.retainAll(set2);
//
//		ArrayList<Integer> list = new ArrayList<>();
//
//		int x = 0, y = 0, z = 0;
//		while (x < n1 && y < n2 && z < n3) {
//
//			if (A[x] == B[y] && B[y] == C[z]) {
//				list.add(A[x]);
//			} else if (A[x] < B[y]) {
//				x++;
//			} else if (B[y] < C[z]) {
//				y++;
//			} else {
//				z++;
//			}
//		}
//
//		return list;
//	}

}
