package br.com.suprasync.apresentacao.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
			String idOcorrencia = request.getParameter("idOcorrencia");
			String codigo = request.getParameter("codigo");

			if (idOcorrencia != null && codigo != null) {
				SacOcorrenciaFacade sacFacade = new SacOcorrenciaFacade();
				SacOcorrenciaArquivo arquivo = sacFacade.obterAnexosSac(Integer.parseInt(idOcorrencia), Integer.parseInt(codigo), null).get(0);
				byte[] anexo = arquivo.getArquivo();

				UtilFacade util = new UtilFacade();
				String caminhoArquivo = "C:/Sevensys/anexos/"+arquivo.getNomeArquivo();
				util.salvarArquivoDisco("C:/Sevensys/anexos", arquivo.getNomeArquivo(), anexo);

				File downloadFile = new File(caminhoArquivo);
				FileInputStream inStream = new FileInputStream(downloadFile);
				/** if you want to use a relative path to context root:
				String relativePath = getServletContext().getRealPath("");
				System.out.println("relativePath = " + relativePath);**/

				// obtains ServletContext
				ServletContext context = getServletContext();

				// gets MIME type of the file
				String mimeType = context.getMimeType(caminhoArquivo);
				if (mimeType == null) {

					// set to binary type if MIME mapping not found
					mimeType = "application/octet-stream";
				}

				// modifies response
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());

				// forces download
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);

				// obtains response's output stream
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inStream.close();
				outStream.close();	
				util.removerArquivoDisco("C:/Sevensys/anexos", arquivo.getNomeArquivo());
			}			

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
