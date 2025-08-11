let produtoIndex = 0;
function adicionarProduto(nome = '', descricao = '') {
  const lista = document.getElementById('produtos-lista');
  const div = document.createElement('div');
  div.className = 'row align-items-end mb-2 produto-item';
  div.innerHTML = `
    <div class="col-md-6">
      <input type="text" class="form-control" name="doacoes[${produtoIndex}].nome" placeholder="Nome do Produto" value="${nome}" required>
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control" name="doacoes[${produtoIndex}].descricao" placeholder="descricao" value="${descricao}" required>
    </div>
    <div class="col-md-2">
      <button type="button" class="btn btn-danger btn-sm" onclick="removerProduto(this)">Remover</button>
    </div>
  `;
  lista.appendChild(div);
  produtoIndex++;
}

function removerProduto(btn) {
  btn.closest('.produto-item').remove();
}

window.onload = function() {
  adicionarProduto();
};
