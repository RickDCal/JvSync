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
			
			default:break;
			}	 		
	 		
    	%>
	 		<tr class="grid">	 		
	 			<td class="acao">
					<input type="checkbox" id="check_<%=i%>" value="<%=tabela%>">
				</td>
				<td class="grid"><%=descricaoTabela%></td>
				<td class="grid"><%=data%></td>	
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
	<div id="rodape">
	<img src="<%="images/" + null %>">
	</div>
	
	<script>
	function atualizarGrid() {
		
		var table=document.getElementById('grid');
		var tabelas = [];
		var imagens = [];

		for(var i=0; i<table.rows.length;i++){
			var imagem, check;
			check = document.getElementById('check_'+ i);
			imagem = document.getElementById('load_'+ i);
			imagemLoaded = document.getElementById('loaded_'+ i);	
			imagemFail = document.getElementById('fail_'+ i);
			
			if (check && check.checked && imagem) {
				imagem.style.display = "block";
				atualizarTabela(check, imagem, imagemLoaded, imagemFail);				
			} else {
				imagem.style.display = "none";
				imagemLoaded.style.display = "none";
				imagemFail.style.display = "none";
			}
		}
	}
	
	function atualizarTabela(check, imagem, imagemLoaded, imagemFail) {
		var xhttp = new XMLHttpRequest();
		xhttp.checkBox = check;
		xhttp.imagem = imagem;
		xhttp.imagemLoaded = imagemLoaded;
		xhttp.imagemFail = imagemFail;
		
		  xhttp.onreadystatechange = function(check, imagem, imagemLoaded) {
		    if (this.readyState == 4 && this.status == 200) {
		      this.imagem.style.display = "none";
		      this.imagemLoaded.style.display = "block";
		      this.checkBox.checked = false;
		    } else if (this.readyState == 4 && this.status != 200) {
		      this.imagem.style.display = "none";
			  this.imagemFail.style.display = "block";
			  this.checkBox.checked = false;
		    }
		  };
		  xhttp.open("GET", "/jvsync/rest/database/atualizarDados?tabela=" + check.value, true); // true = assincrono
		  xhttp.send();
		
	}
	
	function marcaCheckbox(checked) {
		var table=document.getElementById('grid');
		
		for(var i=0; i<table.rows.length;i++){
			var check = document.getElementById('check_'+ i);
			check.checked = checked; 
		}
	}
	
	function loadDoc() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("demo").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", "/jvsync/rest/database/atualizarDados?tabela=tgfven", false); // true = assincrono
	  xhttp.send();
	}
	</script>
</body>
</html>