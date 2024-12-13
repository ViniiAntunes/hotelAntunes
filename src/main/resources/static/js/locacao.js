function buscarQuartosDisponiveis(){
    let checkin = $("#checkin").val();
    let checkout = $("#checkout").val();

    $.ajax({
        url: "/getQuartosDisponiveis",
        method: "POST",
        data: {
            checkin: checkin,
            checkout: checkout
        },
        success: function(response){
            $("#area_quartos").html(response);
        },
        error: function(){
            alert("Deu Ruim!");
        }
    });
}
$("#btnBuscarQuarto").click(buscarQuartosDisponiveis);

function atualizaValores(element){
    let option = $(element).find("option:selected");

    let preco = option.data('preco');
    let capacidade = option.data('capacidade');

    $("#info_quarto").removeClass('d-none');
    $("#reservarQuarto").removeClass('d-none');

    $("#valor_quarto").text("R$ "+parseFloat(preco).toFixed(2));
    $("#capacidade_quarto").text(capacidade);
}

function reservar(){
    let checkin = $("#checkin").val();
    let checkout = $("#checkout").val();
    let quarto = $("#quarto").find("option:selected").val();
    $.ajax({
        url: "/reservar",
        method: "post",
        data: {
            checkin: checkin,
            checkout: checkout,
            quarto: quarto
        },
        success: function(response){
            const Toast = Swal.mixin({
              toast: true,
              position: "top-end",
              showConfirmButton: false,
              timer: 3000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.onmouseenter = Swal.stopTimer;
                toast.onmouseleave = Swal.resumeTimer;
              }
            });
            Toast.fire({
              icon: response.sucesso ? "success" : "error",
              title: response.mensagem
            });
        },
        error: function(){
            alert("Deu Ruim!");
        }
    });
}

$("#reservarQuarto").click(reservar);


function precoTotal(locacao){
 letPrecoPorNoite=parseFloat(locacao.preco);

 let checkinDate = new Date(locacao.checkin);
 let checkoutDate = new Date(locacao.chekout);


 let diasDeLoc = difEmMiliseg / (1000 * 3600 * 24);

 let precoTotal = precoPorNoite * diasDeLocacao;

     // Atualiza o valor do preço total no HTML
     $('#preco_total').text("R$ " + precoTotal.toFixed(2));
}