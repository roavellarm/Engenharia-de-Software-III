# Comandos Git
By: Rodrigo Avellar
## Resumo básico:
#### git clone https://github.com/roavellarm/Engenharia-de-Software-III.git 
#### git pull
#### git status
#### git add .
#### git commit -m "Mensagem de commit entre aspas"
#### git push -u origin master


# Configurações Gerais Iniciais

## Configurando identificação do desenvolvedor na máquina (apenas uma vez por máquina):
$ git config --global user.name "Nome do Desenvolvedor"

$ git config --global user.email "desenvolvedor@example.com"

## Conexão de Rede no SENAC:
$ git config --global http.proxy 192.168.7.253:8080

## Se sua rede local não tem proxy:
$ git config --global --unset http.proxy

## Comandos gerais do CLI
$ cd (+ nome do diretório para entrar)

$ cd .. (para sair do diretório)

$ mkdir (+ nome do diretório para criar um diretorio)

$ ls (para listar os arquivos do diretório)

$ ls -a (para listar os arquivos do diretório incluindo os arquivos git)

# Comandos mais utilizados

### Para clonar o repositório do git na máquina
$ git clone https://github.com/roavellarm/Engenharia-de-Software-III.git 

### Para ver o status do diretório
$ git status

### Para atualizar um repositório local conforme o repositório remoto
$ git pull

### Para adicionar no git local todas as alterações realizadas
$ git add .

### Para gravar no git local as alterações pendentes
$ git commit -m "Digitar aqui sua mensagem de commit entre aspas"

### Para subir ao repositório remoto (github) o commmit realizado
$ git push -u origin master

### Para criar um novo branch
$ git branch NomeDoMeuBranch

### Para entrar no branch
$ git checkout -b NomeDoMeuBranch

[apos adicionar novos arquivos ou fazer as alterações, adições e commits:]
### Para voltar ao master
$ git checkout -b master

### Para incorporar meu branch ao master novamente
$ git merge NomeDoMeuBranch
