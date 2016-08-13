package com.epam.issuetracker;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.beans.Attachment;

public class AttachmentUploading extends AbstractServlet {
	private static final long serialVersionUID = 26675183107196062L;
	

	private final Logger LOGGER = LogManager.getLogger(AttachmentUploading.class);
	private File tempDirectory;
	private File destinationDirectory;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);

		String tempDirectoryPath = (String) getServletContext().getInitParameter(Web_Constants.KEY_TEMP_UPLOAD);
		
		tempDirectory = new File(tempDirectoryPath);
		if (!tempDirectory.isDirectory()) {
			tempDirectory.mkdirs();
		}
		String destinationDirPath = (String) getServletContext().getInitParameter(Web_Constants.KEY_DESTINATION_UPLOAD);
		
		destinationDirectory = new File(getServletContext().getRealPath(destinationDirPath));
		if (!destinationDirectory.isDirectory()) {
			destinationDirectory.mkdirs();
		}
		LOGGER.info("Initialization. \nTemporary attachment uploading directory = " + tempDirectory
				+ ",\nDestination attachment directory = " + destinationDirectory);
	}

	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setSizeThreshold(1 * Web_Constants.KILO * Web_Constants.KILO);
		fileItemFactory.setRepository(tempDirectory);

		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		uploadHandler.setSizeMax(Web_Constants.KILO * Web_Constants.KILO * Web_Constants.HUNDRED);

		try {

			List<FileItem> items = uploadHandler.parseRequest( request);
			Iterator<FileItem> itr = items.iterator();
			Integer attachmentId = null;
			Integer issueId = null;
			String fileName = null;
			String filesize=null;
			File fileTemp=null;
			
			String description=null;
		
			while (itr.hasNext()) {
				
				FileItem item = itr.next();
				
				if (item.isFormField()) {
					if(issueId==null){
					issueId = Integer.valueOf(item.getString());
					}
					description=item.getString();
					
				} else {
					fileTemp=new File(item.getName());
					fileName = fileTemp.getName();
                   long kilobytes=(item.getSize()/1024);
                   filesize=String.valueOf(kilobytes);
                  
					if (issueId != null && fileName != null && filesize!=null) {
						attachmentId = IssueDAOFactory.getDAO()
								.submitAttachment(new Attachment(null, fileName, filesize, description,issueId), issueId);
						File file = new File(destinationDirectory, attachmentId.toString());
						item.write(file);
					}
				}
			}
			
			
			if (attachmentId != null) {
				LOGGER.info("Attachment (name = " + fileName + ", Id = " + attachmentId + ") to Issue " + issueId);
				moveToUrl(Web_Constants.MAIN_CONTROLLER, request, response);
			} else {
				throw new FileUploadException();
			}
		} catch (Exception e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}

	}
	
	
}
