function lerCodigo(){
 Quagga.init({
        inputStream : {
            name : "Live",
            type : "LiveStream",
            target: document.querySelector('#camera') // Elemento alvo para mostrar o feed da câmera
        },
        decoder : {
            readers : ["code_128_reader"] // Definir o tipo de código a ser lido (Code 128)
        }
    }, function(err) {
        if (err) {
            console.error(err); // Em caso de erro, logar no console
            return;
        }
        console.log("Inicialização concluída. Pronto para começar.");
        Quagga.start(); // Iniciar a leitura do código de barras
    });

    // Evento acionado quando um código de barras é detectado
    Quagga.onDetected(function(data) {
        console.log(data); // Exibir dados no console para debug

        document.querySelector('#buscarItemBtn').style.display = 'inline-block';
        document.querySelector('#buscarItemBtn').addEventListener('click', function() {
                     const codigo = document.querySelector('#resultado').innerText.replace('Código Detectado: ', '').trim();
                     if (codigo) {
                         document.querySelector('#resultado').innerHTML = `Código Detectado: ${data.codeResult.code}`;

                     } else {
                         alert('Nenhum código detectado. Por favor, escaneie um código primeiro.');
                     }
                 });
    });

    }
     lerCodigo();


