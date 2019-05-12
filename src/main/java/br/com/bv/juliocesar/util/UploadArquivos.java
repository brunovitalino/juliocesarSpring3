package br.com.bv.juliocesar.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

public class UploadArquivos {

	public String uploadAnexo(FileItem file, String nome, String pasta, HttpServletRequest request){
		
		String path = "";
		try {
			// Creating the directory to store file
			String uploadsDir = "/resources/"+ pasta+ "/";
            String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
            
            File dir = new File(realPathtoUploads);
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
		
			nome = new StringHash().randomString(50) + nome;
					
			File serverFile = new File(dir.getAbsolutePath() + File.separator + nome);
			file.write(serverFile);
			System.err.println("Server File Location= " + serverFile.getAbsolutePath());
			path = pasta +"/"+nome;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	
		return path;
	}
	
	
	public Boolean deleteAnexo(String anexo, HttpServletRequest request){
		boolean delete = true;
		try{
			String uploadsDir = "/"+ anexo;
	        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
	        File inFile = new File(realPathtoUploads);
	        delete = inFile.delete();
	        
		}catch (Exception e) {
			throw new RuntimeException();
		}
		
		return delete;
	}
	
}
