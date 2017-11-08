# MyMoney
Aplicação de finanças pessoais desenvolvida para a disciplina de Laboratório de Programação II da Faculdade SENAC Porto Alegre (2017/1ºSemestre)</br>

## INSTRUÇÕES
As instruções abaixo são passos para criação do banco de dados no PostgresSQL e se encontram no arquivo sql.txt, localizado na pasta /mymoney/src/resources/</br>

1) Se tiver o PgAdmin, criar um database com o seguinte nome: mymoneydb </br>
2) Caso queira criar o database via linha de comando SQL utilizar o código abaixo:</br>
CREATE DATABASE mymoneydb</br>
    WITH </br>
    OWNER = postgres</br>
    ENCODING = 'UTF8'</br>
    LC_COLLATE = 'C'</br>
    LC_CTYPE = 'C'</br>
    TABLESPACE = pg_default</br>
    CONNECTION LIMIT = -1;</br>

ATENÇÃO</br>
Obs: A senha padrão utilizada para acessar o servidor foi "123456",
caso sua senha for diferente no postgres, esta deve ser adicionada(alterada)
na variável SENHA na classe BDUtil.java localizada no diretório "/dao/impl_BD"</br>

3) Após criar o database, criar as tabelas e carregar os dados iniciais. Para isso,
basta copiar e colar o texto deste txt na linha de comando SQL do PgAdmin ou NetBeans.</br>

## CRIAÇÃO DE TABELAS
### Tabela categoria
Campos: titulo, tipo</br>

CREATE TABLE categoria (</br>
    titulo varchar(30),</br>
    tipo boolean,</br>
    id serial primary key</br>
);</br>
DELETE FROM categoria;</br>
ALTER SEQUENCE categoria_id_seq RESTART WITH 1;</br>
SELECT * FROM categoria;</br>

### Tabela movimentacao
Campos: valor, data, hora, descricao, tipo, idcategoria</br>

CREATE TABLE movimentacao (</br>
    valor decimal(10,2),</br>
    data date,</br>
    hora time,</br>
    descricao varchar(30),</br>
    tipo boolean,</br>
    idcategoria integer REFERENCES categoria (id),</br>
    id serial primary key</br>
);</br>
DELETE FROM movimentacao;</br>
ALTER SEQUENCE movimentacao_id_seq RESTART WITH 1;</br>
SELECT * FROM movimentacao;</br>

## INSERÇÃO DE ALGUNS DADOS INICIAIS
INSERT INTO categoria (titulo, tipo) VALUES</br>
        ('Salario',true),</br>
        ('Mesada',true),</br>
        ('Extras',true),</br>
        ('Alimentação',false),</br>
        ('Luz',false),</br>
        ('Agua',false),</br>
        ('Condominio',false),</br>
        ('Gasolina',false)</br>
;
