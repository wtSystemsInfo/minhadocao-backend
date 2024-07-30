Guia de Execução do JAR - Spring Boot

Este documento fornece as instruções necessárias para executar a aplicação Spring Boot a partir do arquivo JAR e configurar o banco de dados.

Pré-requisitos
Antes de executar o JAR, você precisa ter o Java Development Kit (JDK) 17 instalado em sua máquina.

Verificar a instalação do Java
Para verificar se o Java está instalado corretamente, execute o seguinte comando no terminal:

java -version

Você deve ver a versão do Java instalada. Certifique-se de que é a versão 17 ou superior. Caso não esteja instalado, 
faça o download e instale o JDK 17 a partir do site oficial da Oracle ou de outra distribuição confiável.

Configuração do Banco de Dados
A aplicação utiliza o PostgreSQL como banco de dados. Antes de executar a aplicação, você deve configurar e iniciar um banco de dados PostgreSQL.

Informações de Configuração
Certifique-se de que seu banco de dados PostgreSQL está configurado com as seguintes informações:

URL do banco de dados: jdbc:postgresql://localhost:5432/minhadocao
Nome do banco de dados: minhadocao
Usuário do banco de dados: postgres
Senha do banco de dados: 588791
Criando o Banco de Dados
O banco de dados minhadocao será criado automaticamente se não existir, com base na configuração createDatabaseIfNotExist=true. 
No entanto, é uma boa prática verificar se o banco de dados foi criado corretamente.

Configurações no arquivo application.properties
As configurações do banco de dados estão localizadas no arquivo application.properties da aplicação. Aqui estão as configurações relevantes:

spring.datasource.url=jdbc:postgresql://localhost:5432/minhadocao?createDatabaseIfNotExist=true
spring.datasource.username=postgres
spring.datasource.password=588791


Certifique-se de que essas configurações estão corretas para o seu ambiente.

Executando o JAR
Localize o arquivo JAR: Navegue até o diretório onde o arquivo JAR da aplicação está localizado.

Execute o JAR: Utilize o seguinte comando para iniciar a aplicação:

java -jar minhadocao-0.0.1-SNAPSHOT.jar

Acessando a aplicação: Após a aplicação iniciar, você pode acessá-la através do navegador ou de ferramentas de requisição HTTP. 
O endereço padrão da aplicação geralmente é http://localhost:8080, a menos que tenha sido configurado de outra forma no arquivo application.properties.

Parâmetros Adicionais
Você pode passar parâmetros adicionais para o JAR, se necessário. Por exemplo, para definir a porta da aplicação, use:

java -jar minhadocao-0.0.1-SNAPSHOT.jar --server.port=9090



Logs e Depuração
Os logs da aplicação serão exibidos no terminal onde o JAR está sendo executado. 
Para obter mais informações detalhadas de depuração, você pode ajustar o nível de log no arquivo de configuração application.properties.



Encerrando a Aplicação
Para parar a aplicação, simplesmente interrompa o processo no terminal onde o JAR está sendo executado. 
No Linux ou macOS, você pode fazer isso pressionando Ctrl + C. No Windows, o comando pode ser Ctrl + Break.