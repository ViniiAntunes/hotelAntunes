function lerProduto(){
    $("#camera-container").removeClass('d-none');

	let lastCodes = []; // Armazena as últimas leituras
	let requiredMatches = 5; // Número de leituras idênticas necessárias

	Quagga.init({
		inputStream: {
			name: "Live",
			type: "LiveStream",
			target: $('#camera')[0]
		},
		decoder: {
			readers: ['ean_reader']
		}
	}, function(err) {
		if (err) {
			console.log(err);
			return;
		}
		console.log("Initialization finished. Ready to start");
		Quagga.start();
	});

	Quagga.onDetected(function(data) {
		const currentCode = data.codeResult.code;

		if (lastCodes.length === 0 || lastCodes[lastCodes.length - 1] === currentCode) {
			// Adiciona o código se for o primeiro ou igual ao anterior
			lastCodes.push(currentCode);
		} else {
			// Reinicia a contagem se o código for diferente
			lastCodes = [currentCode];
		}

		if (lastCodes.length >= requiredMatches) {        
			insereProduto(currentCode);
			lastCodes = []; // Reinicia para novas leituras									
		}
	});
}
function exibirDetalhesProduto(produto) {
    // Exibindo descrição e preço do produto
    document.getElementById('descricaoProduto').textContent = produto.descricao;
    document.getElementById('precoProduto').textContent = 'R$ ' + produto.preco.toFixed(2);
    document.getElementById('pedido').classList.remove('d-none');
    document.getElementById('nomeProduto').value = produto.descricao; // Alterando para mostrar descrição
    document.getElementById('produto-lista').classList.add('d-none');
}
function mostrarProdutos() {
    const descricaoProduto = document.getElementById('nomeProduto').value;

    if (descricaoProduto.length < 3) {
        document.getElementById('produto-lista').classList.add('d-none');
        return;
    }

    fetch('/produtos?descricao=' + encodeURIComponent(descricaoProduto)) // Mudando 'nome' para 'descricao'
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(produtos => {
            const container = document.getElementById('produtosContainer');
            container.innerHTML = "";

            if (produtos.length > 0) {
                produtos.forEach(produto => {
                    const produtoElement = document.createElement('div');
                    produtoElement.classList.add('produto-item');
                    produtoElement.textContent = produto.descricao; // Exibindo descrição do produto
                    produtoElement.onclick = function () {
                        exibirDetalhesProduto(produto);
                    };
                    container.appendChild(produtoElement);
                });
                document.getElementById('produto-lista').classList.remove('d-none');
            } else {
                const noResult = document.createElement('div');
                noResult.textContent = 'Nenhum produto encontrado';
                container.appendChild(noResult);
                document.getElementById('produto-lista').classList.remove('d-none');
            }
        })
        .catch(err => console.error('Erro ao buscar produtos:', err));
}