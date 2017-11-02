# Comandos GitBash

## Configurações Gerais Iniciais

### Configurando identificação do desenvolvedor na máquina (apenas uma vez por máquina):
$ git config --global user.name "Nome do Desenvolvedor"

$ git config --global user.email "desenvolvedor@example.com"

### Conexão de Rede no SENAC:
$ git config --global http.proxy 192.168.7.253:8080

### Se na sua casa não tiver proxy:
$ git config --global --unset http.proxy

### Comandos gerais do CLI
$ cd (+ nome da pasta para entrar)

$ cd .. (para sair da pasta)

$ mkdir (+ nome da pasta para criar um diretorio)

$ ls (para ver o que tem na pasta)

$ ls -a (para ver o que tem na pasta incluindo os arquivos git)

# Comandos mais utilizados

### Para clonar o repositório do git na máquina
$ git clone https://github.com/roavellarm/Engenharia-de-Software-III.git 

### Para ver o status da pasta
$ git status

### Para atualizar um repositório local conforme o repositório remoto
$ git pull

### Para adicionar no git local todas as alterações realizadas
$ git add .

### Para gravar no git local as alterações pendentes
$ git commit -m "Digitar aqui sua mensagem de commit entre aspas"

### Para subir ao repositório remoto (github) o commmit realizado
git push -u origin master

### Para criar um novo branch
$ git branch NomeDoMeuBranch

### Para entrar no branch
$ git checkout NomeDoMeuBranch

[apos adicionar novos arquivos ou fazer as alterações, adições e commits:]
### Para voltar ao master
$ git checkout master

### Para incorporar meu branch ao master novamente
$ git merge NomeDoMeuBranch
