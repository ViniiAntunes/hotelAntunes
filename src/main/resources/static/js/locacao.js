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
            alert("Deu Bom!");
        },
        error: function(){
            alert("Deu Ruim!");
        }
    });
}

$("#reservarQuarto").click(reservar);




document.addEventListener('DOMContentLoaded', function () {
    let today = new Date();
    let tomorrow = new Date();
    tomorrow.setDate(today.getDate() + 1);
    today = today.toISOString().split('T')[0];
    tomorrow = tomorrow.toISOString().split('T')[0];

    document.getElementById('checkin').value = today;
    document.getElementById('checkin').setAttribute('min', today);

    document.getElementById('checkout').value = tomorrow;
    document.getElementById('checkout').setAttribute('min', tomorrow);

    document.getElementById('checkin').addEventListener('change', function() {
            let checkinDate = new Date(document.getElementById('checkin').value);
            checkinDate.setDate(checkinDate.getDate() + 1);
            document.getElementById('checkout').setAttribute('min', checkinDate.toISOString().split('T')[0]);
        });
    });
