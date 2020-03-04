<%@page import="br.com.jvsync.apresentacao.facade.CadastroFacade"%>
<%@page import="java.util.*;"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JvSync</title>
<link type="text/css" rel="stylesheet" href="css/estilo.css" />
<link type="text/css" rel="stylesheet" href="css/estiloGrid.css" />
<link type="text/css" rel="stylesheet" href="css/estiloForm.css" />
<script type="text/javascript" src="js/jsGlobal.js"></script>
<script type="text/javascript" src="js/jsCadClientes.js"></script>

</head>

		<% 		
		CadastroFacade cadastroFacade = new CadastroFacade();
		Map<String, String> map = cadastroFacade.ultimaAtualizacao();				
   		%>	 

<body>	
    <div id="topo">    
       
    </div>
  	 		  		
   		
	</div>	
	<br>	
	<div id = "conteudo">
	
		<div id="titulo">
			Atualizar Cadastros
	 	</div>	 	
	 	<br>
	 	
	 	<br>	 	 	
	 	<br> 	
	 	
	 	
	 	<table  align="center" id="grid">
			<tr class="grid">
				<th class="grid tam2"></th>	
				<th class="grid tam2">Tabela</th>
				<th class="grid tam14">Ultima Atualização</th>	
				<th class="grid tam1"></th>												
			</tr> 	
	 	<%
	 	int i = 0;
	 	for (String key : map.keySet()) {
	 		String tabela = key;
	 		String data = map.get(key);    					
    	%>
	 		<tr class="grid">	 		
	 			<td class="acao">
					<check type="checkbox" >
				</td>
				<td class="grid"><%=tabela%></td>
				<td class="grid"><%=data%></td>	
				<td class="acao">
					<a href="cadastroCliente.jsp?idPessoa=<%= tabela%>&enum=<%=tabela%>&act=2">
					<img id="icone_<%=i%>"src="images/edit_grid.png"></a>
					<a href="cadastroCliente.jsp?idPessoa=<%= data%>&enum=<%=data%>&act=3">
					<img src="images/detalha_grid.png"></a>
				</td>														
			</tr> 
			
		<%
		i = i+1;
	 	}
		%>   	   
		      	    	
	 	</table>
	 	<br>
	 	<hr>
	 	<button type="button" align="center" onclick="loadDoc()">Request data</button>
	 	<a href="cadastroCliente.jsp?act=1"><img src="images/botao_cadastrar.png"/></a>
	 	<br>
	 	<hr>
	 		 	
	  	</div> 	    	
	<div id="rodape">
	<img src="<%="images/" + null %>">
	</div>
</body>
</html>