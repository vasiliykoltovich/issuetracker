package com.epam.issuetracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class AttachmentDownloading extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3296719231580416193L;
	private final  Logger LOGGER=LogManager.getLogger(AttachmentDownloading.class);
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
		
		String properFileName = request.getParameter(Web_Constants.KEY_ATTACHMENT_NAME);
		
		String tempFilename = destinationDirectory + "\\" + request.getParameter(Web_Constants.KEY_ATTACHMENT_ID);
		
		String fileName = tempDirectory + "\\" + properFileName;
		
		File tempFile = new File(tempFilename);
		File file = new File(fileName);

		response.setContentLength((int) tempFile.length());
		response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());

		FileInputStream in = new FileInputStream(tempFile);
		OutputStream out = response.getOutputStream();

		byte[] buf = new byte[Web_Constants.KILO];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}

		in.close();
		out.close();
		LOGGER.info("Downloaded file " + properFileName);
	}
	
}
