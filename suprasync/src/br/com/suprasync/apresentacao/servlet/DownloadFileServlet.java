package br.com.suprasync.apresentacao.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.activation.MimetypesFileTypeMap;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.suprasync.apresentacao.facade.UtilFacade;
import br.com.suprasync.apresentacao.facade.sac.SacOcorrenciaFacade;
import br.com.suprasync.persistencia.SacOcorrenciaArquivo;

@WebServlet("/downloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DownloadFileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
//			String idOcorrencia = request.getParameter("idOcorrencia");
//			String codigo = request.getParameter("codigo");

			String idOcorrencia = "71515";
			String codigo = "1";
			SacOcorrenciaFacade sacFacade = new SacOcorrenciaFacade();
			SacOcorrenciaArquivo arquivo = sacFacade.obterAnexosSac(Integer.parseInt(idOcorrencia), Integer.parseInt(codigo), null).get(0);
//			Blob anexo = arquivo.getArquivo();
			byte[] anexo = arquivo.getArquivo();
			
			UtilFacade util = new UtilFacade();
			String filePath = "C:/Sevensys"+arquivo.getNomeArquivo();
			util.salvarArquivoDisco("C:/Sevensys", arquivo.getNomeArquivo(), anexo);
			
			
			
//			FileOutputStream fileOuputStream = new FileOutputStream(filePath); 
//			try { 
//			    fileOuputStream.write(anexo);			   
//			 } finally {
//				 fileOuputStream.close();
//			 }
//			
			
			
			
			File downloadFile = new File(arquivo.getNomeArquivo());
//			File downloadFile = new File(arquivo.getNomeArquivo());
//			FileOutputStream fos = new FileOutputStream(downloadFile);
//
//                InputStream input = anexo;
//                byte[] buffer = new byte[4096];
//                while (input.read(buffer) > 0) {
//                    fos.write(buffer);
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//				
//
//			
//			FileInputStream inStream = new FileInputStream(downloadFile);
//			
//			System.out.println("Mime Type of " + downloadFile.getName() + " is " +
//                    new MimetypesFileTypeMap().getContentType(downloadFile));
//			
//			
//			
//
			String relativePath = getServletContext().getRealPath("");
			System.out.println("relativePath = " + relativePath);

			ServletContext context = getServletContext();

			String mimeType = context.getMimeType(filePath);
			
			if (arquivo.getNomeArquivo().endsWith(".docx") || arquivo.getNomeArquivo().endsWith(".doc")) {
				//mimeType = "application/msword";
				mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			}			
			
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);
			// modifies response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			// forces download
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			// obtains response's output stream

//			
//                
//             
            
			
			
			
			
			OutputStream outStream = response.getOutputStream();
//			InputStream in = arquivo.getBinaryStream();
//			byte[] buf = new byte[1024];
//			int len = 0;
//			while ((len = in.read(buf)) != -1) {
//				outStream.write(buf, 0, len);
//			}
//			in.close();
			
//			byte[] buffer2 = new byte[4096];
//			int bytesRead = -1;
//			while ((bytesRead = inStream.read(buffer2)) != -1) {
//				outStream.write(buffer2, 0, bytesRead);
//			}
//			inStream.close();
//			outStream.close();
			
			//response.getWriter().append("Served at: ").append(request.getContextPath());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}










	


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response);
}

}
