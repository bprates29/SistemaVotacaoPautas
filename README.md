# Guia Geral

###Descrição inicial
Sistema de votação em pautas backend. O sistema permite cadastrar associados (CRUD) e cadastrar 
pautas para serem votadas(CRUD). Após isso é possivel votar em pautas que serão abertas, a pauta
precisa ser aberta para votação, após aberta fica um tempo determinado para votação e depois fecha
automaticamente dependendo do tempo de votação cadastrado na pauta. Para a votação foi utilizado
WebFlux com RabbitMQ, quando o voto é validado ele é jogado em uma fila para salvar no banco de dados.
Após a finalização da votação o resultado tbm é jogado em uma fila do RabbitMQ. Foi realizado testes
unitários apenas da lógica de negócio.

####Tecnologias utilizadas
- Spring Boot + JAVA
- MySQL
- WebFlux
- RabbitMQ
- MapStruct
- Openapi
- Openfeign
- JUnit + Mockito
- entre outras...

####

### Inciando o projeto

####MySQL
 - Para starta o mysql é possivel usar o docker-composer:
``` 
sudo docker-compose up
``` 
As configurações do banco se encontra na raiz do projeto "docker-compose.yml"

####RabbitMQ
Também é necessário rodar o rabbitMQ, caso não tenha instalado basta rodar o comando do docker:
```
docker run -d --hostname my-rabbit --name sample-rabbit -p 15672:15672 -p 5671:5671 -p 5672:5672 rabbitmq:3-management
```

Com essas configurações é possível startar o sistema, para ver os endpoints disponíveis:
`http://localhost:8080/swagger-ui/`

