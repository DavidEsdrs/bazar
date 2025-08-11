CREATE TABLE orgaofiscalizador (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(255)
);

CREATE TABLE orgaodonatario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  endereco VARCHAR(255),
  telefone VARCHAR(30),
  horariofuncionamento VARCHAR(100),
  descricao VARCHAR(255)
);

CREATE TABLE lote (
  id INT AUTO_INCREMENT PRIMARY KEY,
  data_entrega DATE NOT NULL,
  observacao VARCHAR(255),
  orgao_fiscalizador_id INT NOT NULL,
  orgao_donatario_id INT NOT NULL,
  FOREIGN KEY (orgao_fiscalizador_id) REFERENCES orgaofiscalizador(id),
  FOREIGN KEY (orgao_donatario_id) REFERENCES orgaodonatario(id)
);

CREATE TABLE produto (
  codigo INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(255),
  lote_id INT NOT NULL,
  FOREIGN KEY (lote_id) REFERENCES lote(id)
);
