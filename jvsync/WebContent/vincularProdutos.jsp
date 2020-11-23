<%@page import="br.com.jvsync.apresentacao.facade.GenericFacade"%>
<%@page import="br.com.jvsync.persistencia.TipoMovimentoJiva"%>
<%@page import="br.com.jvsync.persistencia.RelacionamentoProduto"%>
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
		GenericFacade genericFacade = new GenericFacade();
		List<RelacionamentoProduto> rels = genericFacade.obterDeSqlServer(RelacionamentoProduto.class, 0, null);		
		List<TipoMovimentoJiva> tiposMovimento = genericFacade.obterDeSqlServer(TipoMovimentoJiva.class, 0, null);	
						
   		%>	 

<body>	
    <div id="topo">     
    </div>

	<br>	
	<div id = "conteudo">
	
		<div id="titulo">
			Vincular Produtos
	 	</div>	 	
	 	
	 	<br>
		<p class="subtitulo"> Produtos Vinculados</p>
		<br>
	 	
	 	<table id="grid">
			<tbody>
				<tr>
					<th class="grid tam2">#</th>
					<th class="grid tam8">Código Aliar</th>
					<th class="grid tam8">Código Jiva</th>
					<th class="grid tam14">Tipo Movimento (Corresp. Jiva)</th>
					<th class="grid tam0"></th>
				</tr>
				<%
				for (RelacionamentoProduto rel : rels) {
					String descricaoOperacao = rel.getTipoMovimentoJiva() != null ? rel.getTipoMovimentoJiva().getDescricao() : "";				
				%>
				<tr>
					<td class="grid"><%=rel.getId()%></td>
					<td class="grid"><%=rel.getCodigoAliar()%></td>
					<td class="grid"><%=rel.getCodigoJiva()%></td>
					<td class="grid"><%=descricaoOperacao%></td>
					<td class="grid"><a><img src="images/edit20.png" alt="editar"  onclick="editar(<%=rel.getId()%>)"/></a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<br>
		<br>
		<hr>
		<p id="subtituloEdicao" class="subtitulo"> Adicionar Vínculo</p>
	 	
	 	<table id="gridNovoRelacionamento">
	 		<tr>
	 			<th class="grid tam2">#</th>
				<th class="grid tam8">Código Aliar</th>
				<th class="grid tam8">Código Jiva</th>
				<th class="grid tam14">Tipo Movimento (Corresp. Jiva)</th>
				<th class="grid tam0"></th>
			</tr>
			<tr class="grid">
				<td class="grid tam1" id="idRelacionamento"></td>
				<td class="grid tam8" id="codigoAliar" contenteditable='true'></td>
				<td class="grid tam8" id="codigoJiva" contenteditable='true'></td>
				<td class="grid tam14" id="tipoMovimento"contenteditable='true'>
					<select id="selectTipo">
						<option value=null></option>
						<%for (TipoMovimentoJiva tipmov : tiposMovimento) {	%>					
	  					<option value="<%=tipmov.getTipoMovimento()%>"><%=tipmov.getDescricao()%></option>
						<%} %>	
					</select>
					<!-- select class="xxx">
  					<option value="volvo">Volvo</option>
					  <option value="saab">Saab</option>
					  <option value="mercedes">Mercedes</option>
					  <option value="audi">Audi</option>
					</select>
					- -->	
				</td>
				<td class="grid tam0"><a><img src="images/save20.png" alt="salvar"  onclick="salvarRelacionamentoProduto()"/></a></td>
				</tr>	 	
	 	</table>
	  	</div> 	    	

</body>
</html>