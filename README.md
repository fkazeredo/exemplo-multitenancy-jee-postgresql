# MultiTenancy
Teste de aplicação Java EE Multi Tenancy com banco de dados PostgreSQL e servidor Wildfly


#1 – Baixar o projeto no Github e atualizar as dependências do Maven
No eclipse é só clicar com o botão direito em cima do nome do projeto, ir em “maven” e em seguida “update Project”. Certifique-se de que o “Pom.xml” está de acordo com seu ambiente de desenvolvimento, mude o que for necessário. (Estou utilizando o Java 8 por exemplo)

#2 – Rode o script de criação dos bancos e schemas
Estou utilizando o banco de dados Postgresql versão 9.1, o script está no pacote “br.com.fk.sql”.

#3 – Configure o Datasource no Wildfly/JBoss

A aplicação Java EE utilizará um datasource gerido pelo servidor, utilizando transações em JTA. Isso é configurado no arquivo “META-INF/persistence.xml”. Cada servidor tem a sua particularidade nessa configuração, entretanto no Wildfly a configuração fica dentro do arquivo “standalone-full.xml” dentro da pasta “standalone/configuration” do servidor.
Não se esqueça também de configurar o driver de conexão com o banco de dados.  Lembre-se que será o servidor que irá controlar o acesso ao banco.

#4 – Adicione a referência do Wildfly no projeto
Clique no projeto com o botão direito, vá em “build path”, na aba “Library” selecione “Add Library”, escolha a opção “Server Runtime” e selecione o Wildfly.

#5  - Rode o projeto
Segue os links dos serviços: 
- http://localhost:8080/MultiTenancyExample/api/schema1teste
- http://localhost:8080/MultiTenancyExample/api/schema2teste
- http://localhost:8080/MultiTenancyExample/api/transacaoteste
