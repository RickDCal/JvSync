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
<script type="text/javascript" src="js/script.js"></script>

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
	 	
	 	
	 	<table  align="center" id="grid">
			<tr class="grid">
				<th class="grid tam1"><input type="checkbox" onchange="marcaCheckbox(this.checked)"></th>	
				<th class="grid tam2">Tabela</th>
				<th class="grid tam14">Ultima Atualização</th>	
				<th class="grid tam1"></th>												
			</tr> 	
	 	<%
	 	int i = 0;
	 	for (String key : map.keySet()) {
	 		String descricaoTabela = key;
	 		String data = map.get(key);
	 		String tabela = "";
	 		
	 		switch (descricaoTabela) {
			case "Cabeçalhos de Notas": tabela = "TGFCAB"; break;
			case "Itens de Notas": tabela = "TGFITE"; break;
			case "Tabela de Ruas": tabela = "TFPLGR"; break;
			case "Clientes/Fornecedores": tabela = "TGFPAR"; break;
			case "Cadastro de Produtos": tabela = "TGFPRO"; break;
			case "Tabela de Rotas": tabela = "TGFROT"; break;
			case "Tipos de Operações": tabela = "TGFTOP"; break;
			case "Tipos de Vendas": tabela = "TGFTPV"; break;
			case "Cadastro de Vendedores": tabela = "TGFVEN"; break;
			case "Tabela de Volumes": tabela = "TGFVOA"; break;
			case "Tabela de Bairros": tabela = "TSIBAI"; break;
			case "Tabela de Cidades": tabela = "TSICID"; break;
			case "Tabela de Endereços": tabela = "TSIEND"; break;
			case "Tabela de Regiões": tabela = "TSIREG"; break;
			case "Tabela de Estados": tabela = "TSIUFS"; break;			
			
			case "Tabela de Clientes - Aliar" :  tabela = "myparceiro"; break;
			case "Tabela de Clientes Auxiliar - Aliar":  tabela = "myparceirox"; break;
			case "Tabela de Cabeçalho Pedidos - Aliar":  tabela = "mypedido"; break;
			case "Tabela de Itens de Pedidos - Aliar":  tabela = "mypedidoitem"; break;
			case "Tabela de Produtos - Aliar":  tabela = "myproduto"; break;
			case "Tabela de Vendedores - Aliar" :  tabela = "myvendedor"; break;			
			
			default:break;
			}	 		
	 		
    	%>
	 		<tr class="grid">	 		
	 			<td class="acao">
					<input type="checkbox" id="check_<%=i%>" value="<%=tabela%>">
				</td>
				<td class="grid"><%=descricaoTabela%></td>
				<td id="dataAtualizacao_<%=i%>" class="grid"><%=data%></td>	
				<td class="acao">
					<a ><img id="load_<%=i%>"src="images/loading.gif" style="display : none"></a>
					<a ><img id="loaded_<%=i%>"src="images/loaded.png" style="display : none"></a>
					<a ><img id="fail_<%=i%>"src="images/fail.png" style="display : none"></a>
				</td>														
			</tr> 
			
		<%
		i = i+1;
	 	}
		%>   	   
		      	    	
	 	</table>
	 	<br>
	 	<hr>
	 	<a><img src="images/botao_atualizar.png" onclick="atualizarGrid()"/></a>
	 	<br>
	 	<hr>
	 		 	
	  	</div> 	    	
	
</body>
</html>