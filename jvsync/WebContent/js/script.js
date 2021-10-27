function editar(id) {

		
		var table=document.getElementById('gridNovoRelacionamento');
		idRelacionamento = document.getElementById('idRelacionamento'+id).innerText;
		codigoAliar = document.getElementById('codigoAliar'+id).innerText;
		codigoJiva = document.getElementById('codigoJiva'+id).innerText;
		tipoMovimento = document.getElementById('codTipo'+id).innerText;
		
		document.getElementById('idRelacionamento').innerText = idRelacionamento;
		document.getElementById('codigoAliar').innerText = codigoAliar;
		document.getElementById('codigoJiva').innerText = codigoJiva;
		document.getElementById('selectTipo').value = tipoMovimento;	
		
}
	
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
	      var celula = document.getElementById(this.checkBox.id.replace("check", "dataAtualizacao"));
		  if (celula) {
			  celula.innerText = new Date().toLocaleDateString("pt-BR");
		  }
	      
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

function salvarRelacionamentoProduto() {
	
	var table=document.getElementById('gridNovoRelacionamento');
		idRelacionamento = document.getElementById('idRelacionamento').innerText;
		codigoAliar = document.getElementById('codigoAliar').innerText;
		codigoJiva = document.getElementById('codigoJiva').innerText;
		tipoMovimento = document.getElementById('selectTipo').value;
		
	var objeto = {
		"id": idRelacionamento,
		"codigoAliar" : codigoAliar,
		"codigoJiva" : codigoJiva,
		"tipoMovimento" : tipoMovimento
	}		
	
	var xhttp = new XMLHttpRequest();
	
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      alert('Registro Salvo!');
	      window.location.reload(); 
	      
	    } else if (this.readyState == 4 && this.status != 200) {
	      alert('Ocorreu falha ao atualizar o vínculo de produto');		 
	    }
	  };
	  xhttp.open("POST", "/jvsync/rest/database/atualizarRelacionamentoProduto"); // true = assincrono
	  xhttp.send(JSON.stringify(objeto));
}


