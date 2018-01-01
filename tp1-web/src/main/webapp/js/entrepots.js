function chargeEntrepot(nom) {
    console.log("Asking for " + nom);
    $.ajax("entrepot/?nom=" + encodeURI(nom), {
        dataType: "json",
    })
        .done(function (data) {
            const lines = data.marchandises
                .map(m => `<tr><td>${m.ref}</td><td>${m.nom}</td><td>${m.volumeUnitaire}</td><td>${m.stock}</td></tr>`)
                .join('\n')
            $('#liste-entrepots').html(`<div class="panel panel-default">
<div class="panel-body">
<h3>${data.nom}</h3>
<p>Capacité: ${data.capacite}</p>
<table class="table table-striped">
<thead><tr><th>Ref</th><th>Nom</th><th>Vol.</th><th>Stock</th></tr></thead>
<tbody>
${lines}
</tbody>
</table>
</div>
</div>
`);
        })
        .fail(function () {
            console.log("Query failed for " + nom);
        })
}

document.addEventListener('DOMContentLoaded', function () {
    $('#btn-charge-entrepots').click(function () {
        chargeEntrepot($('#id-entrepot').val());
        console.log("Asked " + $('#id-entrepot').val());
    });
    console.log("Configuration terminée");
}, false);