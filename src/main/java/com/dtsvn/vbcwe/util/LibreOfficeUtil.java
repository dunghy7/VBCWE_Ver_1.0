package com.dtsvn.vbcwe.util;

import java.io.File;

import org.jodconverter.JodConverter;

import com.dtsvn.vbcwe.config.OfficeManagerInstance;

public class LibreOfficeUtil {
	/**
	 * Use JodConverter to convert Office documents to PDF. This conversion is a
	 * synchronous conversion, and the conversion is complete when you return
	 */
	public static boolean convertOffice2PDFSyncIsSuccess(File sourceFile, File targetFile) {
		try {
			OfficeManagerInstance.start();
			JodConverter.convert(sourceFile).to(targetFile).execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Use LibreOffice to convert Office documents to PDF. The conversion is
	 * asynchronous. When returning, the conversion may still be in progress. It is
	 * unknown whether the conversion is abnormal.
	 * 
	 * @param filePath
	 * @param targetFilePath
	 * @return
	 */
	public static int convertOffice2PDFAsync(String filePath, String fileName, String targetFilePath) throws Exception {
		String command;
		int exitStatus;
		String osName = System.getProperty("os.name");
		String outDir = targetFilePath.length() > 0 ? " --outdir " + targetFilePath : "";

		if (osName.contains("Windows")) {
			command = "cmd /c cd /d " + filePath + " && start soffice --headless --invisible --convert-to pdf ./"
					+ fileName + outDir;
		} else {
			command = "libreoffice6.3 --headless --invisible --convert-to pdf:writer_pdf_Export " + filePath + fileName
					+ outDir;
		}

		exitStatus = executeOSCommand(command);
		return exitStatus;
	}

	/**
	 * When calling the console of the operating system and executing the command
	 * command to execute the method, it does not return until the command is
	 * executed. Instead, it returns immediately after execution. The return result
	 * is 0, which can only indicate that the console command of the operating
	 * system is called correctly. However, the results of the execution and whether
	 * there is any abnormality are not reflected here, so a better posture is to
	 * use the synchronous conversion function.
	 */
	private static int executeOSCommand(String command) throws Exception {
		Process process;
		process = Runtime.getRuntime().exec(command); // The conversion takes time. For example, a 3M document takes
														// about 8 seconds, but in actual testing, the next line of code
														// is not executed until the conversion is completed, but the
														// next line of code is executed immediately after the execution
														// instruction is sent.

		int exitStatus = process.waitFor();

		if (exitStatus == 0) {
			exitStatus = process.exitValue();
		}

		process.destroy();
		return exitStatus;
	}
}