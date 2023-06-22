# API-VOTOS-COOPERATIVA

<!-- 

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

Cadastrar uma nova pauta;
Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
Contabilizar os votos e dar o resultado da votação na pauta.
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.
teste -->



O projeto consiste em uma API que possibilita que os associados de uma cooperativa possam votar em pautas enquanto uma sessâo for aberta.

O projeto utiliza:
- Spring Boot 3.1.0
- Java 17
- PostgreSQL 13.10
- Spring Data JPA
- Hibernate Validator

A API possui as seguintes funcionalidades:

    Associados:
        * Cadastrar associados
        * Mostrar todos os associados

    Pauta: 
        * Adicionar pauta
        * Listar pauta
        * Procurar pauta por ID
    
    Sessão de votação:
        * Criar sessão
        * Mostrar todas as sessões
        * Abrir sessão para votação
        * Permitir que o associado vote
        * Mostrar o resultado da sessão

Temos collection no postman: https://www.postman.com/gold-shadow-813189/workspace/votos-cooperativa-api/collection/17815209-41e512b8-87a5-42a3-aabc-12940d404f6f?action=share&creator=26101354

Temos documentação no ASSCIIDOC:
Para ver a documentação, basta acessar o link:
    http://localhost:8080/api/v1/swagger-ui.html