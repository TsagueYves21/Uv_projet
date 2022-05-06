package com.uv_project.fonctions;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uv_project.entities.Produit;
import com.uv_project.entities.User_Model;
@Service
public class Helpers {

	/*======================================================================
	 *-------->>>>>>>>>| Quelque fonctions importante |<<<<<<<<<<--------------
	 *======================================================================
	 */
	 public void	addUserImage(MultipartFile file){
	    	String path = "C:\\Users\\stague\\Documents\\springBoot\\uv_project\\src\\main\\UserImages";
	 	    String fileName  = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
			File serverFile = new File(path+File.separator+newFileName);
			try {
				FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			} catch (Exception e) {
				System.out.println("faile to upload");
			}
			
		}


	    public void deleteUserImage(User_Model user) {
	    
	    		String path = "C:\\Users\\stague\\Documents\\springBoot\\uv_project\\src\\main\\UserImages\\";
	    		try {
	    			
	    			File file = new File(path+user.getPhoto());
	    			file.delete();
	    			
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    	
	    }
	    public void	addProduitImage(MultipartFile file){
	    	String path = "C:\\Users\\stague\\Documents\\springBoot\\uv_project\\src\\main\\produitImage";
	 	    String fileName  = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
			File serverFile = new File(path+File.separator+newFileName);
			try {
				FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			} catch (Exception e) {
				System.out.println("faile to upload");
			}
			
		}
	    public void deleteproduitImage(Produit produit) {
	    	
	    		String path = "C:\\Users\\stague\\Documents\\springBoot\\uv_project\\src\\main\\produitImage\\";
		    		try {
		    			File file = new File(path+produit.getPhoto());
		    			file.delete();
		    			
		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		
	    	}
	       }


}
